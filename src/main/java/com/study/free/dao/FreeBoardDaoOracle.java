package com.study.free.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.study.free.vo.FreeBoardVO;
import com.study.free.vo.FreeSearchVO;

public class FreeBoardDaoOracle implements IFreeBoardDao {

	@Override
	public List<FreeBoardVO> getBoardList(FreeSearchVO searchVO) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("	select	 *                     ");
			sb.append("	from (select a.*, ROWNUM rnum  ");
			sb.append("			from (                 ");
			sb.append("	select	 a.bo_num, ");
			sb.append("   a.bo_category,  ");
			sb.append("   b.comm_nm as cat_nm, ");
			sb.append("   a.bo_title, ");
			sb.append("   a.bo_writer, ");
			sb.append("   to_char(a.bo_reg_date,'YYYY.MM.DD') as reg_date, ");
			sb.append("   to_char(a.bo_mod_date,'YYYY.MM.DD') as mod_date, ");
			sb.append("   a.bo_hit ");
			sb.append("  FROM free_board a left join comm_code b	 ");
			sb.append("    on( a.bo_category = b.comm_cd)	 ");
			sb.append("  where bo_del_yn = 'N'  ");
			if (StringUtils.isNotBlank(searchVO.getSearchWord())
					&& StringUtils.isNotBlank(searchVO.getSearchType())) {
				switch (searchVO.getSearchType()) {
				case "T":
					sb.append("  and bo_title like '%'|| ? ||'%'  ");
					break;
				case "W":
					sb.append("  and bo_writer like '%'|| ? ||'%'  ");
					break;
				case "C":
					sb.append("  and bo_content like '%'|| ? ||'%'  ");
					break;
				}
			}
			if (StringUtils.isNotBlank(searchVO.getSearchCategory())) {
				sb.append("  and bo_category = ?  ");
			}
			sb.append("  order by bo_num desc  ");
			sb.append("  )a                          ");
			sb.append("  where rownum <= ?           ");
			sb.append("  )b                          ");
			sb.append("  where rnum between ? and ?  ");

			System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;

			if (StringUtils.isNotBlank(searchVO.getSearchWord())
					&& StringUtils.isNotBlank(searchVO.getSearchType())) {
				pstmt.setString(idx++, searchVO.getSearchWord());
			}

			if (StringUtils.isNotBlank(searchVO.getSearchCategory())) {
				pstmt.setString(idx++, searchVO.getSearchCategory());
			}

			pstmt.setInt(idx++, searchVO.getLastRecordIndex());
			pstmt.setInt(idx++, searchVO.getFirstRecordIndex());
			pstmt.setInt(idx++, searchVO.getLastRecordIndex());

			rs = pstmt.executeQuery();
			List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
			FreeBoardVO board = null;
			while (rs.next()) {
				board = new FreeBoardVO();
				board.setBoNum(rs.getInt("bo_num"));
				board.setBoCatNm(rs.getString("cat_nm"));
				board.setBoTitle(rs.getString("bo_title"));
				board.setBoWriter(rs.getString("bo_writer"));
				board.setBoRegDate(rs.getString("reg_date"));
				board.setBoModDate(rs.getString("mod_date"));
				board.setBoHit(rs.getInt("bo_hit"));
				list.add(board);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}

		}

	}

	@Override
	public FreeBoardVO getBoard(int boNum) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("	select	 a.bo_num, ");
			sb.append("   a.bo_category,  ");
			sb.append("   b.comm_nm as cat_nm, ");
			sb.append("   a.bo_title, ");
			sb.append("   a.bo_pass, ");
			sb.append("   a.bo_writer, ");
			sb.append("   a.bo_content, ");
			sb.append("   a.bo_ip, ");
			sb.append("   to_char(a.bo_reg_date,'YYYY.MM.DD HH:MM') as reg_date, ");
			sb.append("   to_char(a.bo_mod_date,'YYYY.MM.DD HH:MM') as mod_date, ");
			sb.append("   a.bo_hit ");
			sb.append("  FROM free_board a,  comm_code b	 ");
			sb.append("  where a.bo_category = b.comm_cd	 ");
			sb.append("  and bo_del_yn = 'N'  ");
			sb.append("  and bo_num = ?  ");

			System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setInt(1, boNum);

			rs = pstmt.executeQuery();

			FreeBoardVO board = null;
			if (rs.next()) {
				board = new FreeBoardVO();
				board.setBoNum(rs.getInt("bo_num"));
				board.setBoCategory(rs.getString("bo_category"));
				board.setBoCatNm(rs.getString("cat_nm"));
				board.setBoTitle(rs.getString("bo_title"));
				board.setBoPass(rs.getString("bo_pass"));
				board.setBoWriter(rs.getString("bo_writer"));
				board.setBoRegDate(rs.getString("reg_date"));
				board.setBoModDate(rs.getString("mod_date"));
				board.setBoHit(rs.getInt("bo_hit"));
				board.setBoContent(rs.getString("bo_content"));
				board.setBoIp(rs.getString("bo_ip"));
			}
			return board;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}

		}

	}

	@Override
	public int insertBoard(FreeBoardVO board) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("INSERT INTO free_board ( ");
			sb.append("	      bo_num , bo_title , bo_category ");
			sb.append("	    , bo_writer, bo_pass, bo_content ");
			sb.append("	    , bo_ip, bo_hit, bo_reg_date ");
			sb.append("	    , bo_del_yn ");
			sb.append("	) VALUES ( ");
			sb.append("	     seq_free_board.nextval , ? , ? ");
			sb.append("	   , ? , ? , ? ");
			sb.append("	   , ? , 0 , sysdate ");
			sb.append("	   , 'N' ");
			sb.append("	) ");

			System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, board.getBoTitle());
			pstmt.setString(idx++, board.getBoCategory());
			pstmt.setString(idx++, board.getBoWriter());
			pstmt.setString(idx++, board.getBoPass());
			pstmt.setString(idx++, board.getBoContent());
			pstmt.setString(idx++, board.getBoIp());

			int cnt = pstmt.executeUpdate();

			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}

		}
	}

	@Override
	public int updateBoard(FreeBoardVO board) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("	UPDATE free_board ");
			sb.append(" set 					 ");
			sb.append("    bo_title    = ?   ");
			sb.append("    ,bo_category = ? ");
			//sb.append("    ,bo_writer   = ? ");
			//sb.append("    ,bo_pass     = ? ");
			sb.append("    ,bo_content  = ? ");
			sb.append("    ,bo_mod_date = sysdate ");
			sb.append(" where bo_num = ? ");
			sb.append("  and bo_pass = ? ");

			System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;

			pstmt.setString(idx++, board.getBoTitle());
			pstmt.setString(idx++, board.getBoCategory());
			//pstmt.setString(idx++, board.getBoWriter());
			//pstmt.setString(idx++, board.getBoPass());
			pstmt.setString(idx++, board.getBoContent());
			pstmt.setInt(idx++, board.getBoNum());
			pstmt.setString(idx++, board.getBoPass());

			int cnt = pstmt.executeUpdate();

			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}

		}
	}

	@Override
	public int deleteBoard(FreeBoardVO board) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("	UPDATE free_board ");
			sb.append(" set 					 ");
			sb.append("    bo_del_yn    = 'Y'   ");
			sb.append(" where bo_num = ? ");
			sb.append("  and bo_pass = ? ");

			System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;

			pstmt.setInt(idx++, board.getBoNum());
			pstmt.setString(idx++, board.getBoPass());

			int cnt = pstmt.executeUpdate();

			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}

		}
	}

	@Override
	public int increaseHit(int boNum) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("	UPDATE free_board ");
			sb.append(" set 					 ");
			sb.append("    bo_hit    = bo_hit +1   ");
			sb.append(" where bo_num = ? ");
			System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, boNum);
			int cnt = pstmt.executeUpdate();

			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}

		}
	}

	@Override
	public int getBoardCount(FreeSearchVO searchVO) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("	select	 count(*) ");
			sb.append("   from   free_board ");
			sb.append("  where bo_del_yn = 'N'  ");
//			if (searchVO.getSearchWord() != null && !searchVO.getSearchWord().isEmpty()) {
			if (StringUtils.isNotBlank(searchVO.getSearchWord())
					&& StringUtils.isNotBlank(searchVO.getSearchWord())) {
				switch (searchVO.getSearchType()) {
				case "T":
					sb.append("  and bo_title like '%'|| ? ||'%'  ");
					break;
				case "W":
					sb.append("  and bo_writer like '%'|| ? ||'%'  ");
					break;
				case "C":
					sb.append("  and bo_content like '%'|| ? ||'%'  ");
					break;
				}
			}
			if (StringUtils.isNotBlank(searchVO.getSearchCategory())) {
				sb.append("  and bo_category = ?  ");
			}

			System.out.println(sb.toString().replaceAll("\\s{2,}", ""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;

			if (StringUtils.isNotBlank(searchVO.getSearchWord())
					&& StringUtils.isNotBlank(searchVO.getSearchWord())) {
				pstmt.setString(idx++, searchVO.getSearchWord());
			}

			if (StringUtils.isNotBlank(searchVO.getSearchCategory())) {
				pstmt.setString(idx++, searchVO.getSearchCategory());
			}

			rs = pstmt.executeQuery();
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt(1);
			}

			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}

		}

	}

}
