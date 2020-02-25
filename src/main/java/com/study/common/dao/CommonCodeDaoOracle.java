package com.study.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.study.common.vo.CodeVO;

public class CommonCodeDaoOracle implements ICommonCodeDao {

	@Override
	public List<CodeVO> getCodeListByParent(String code) throws SQLException {
		Connection conn = null; // 커넥션 티켓
		PreparedStatement pstmt = null; // SQL선언문
		ResultSet rs = null; // 질의 결과
		StringBuilder sb = new StringBuilder();

		try {

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("select comm_cd         ");
			sb.append("      ,comm_nm         ");
			sb.append("from comm_code         ");
			sb.append("where comm_parent = ? ");
			sb.append("order by comm_ord asc  ");
			
			System.out.println(sb.toString());
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, code);

			rs = pstmt.executeQuery();
			List<CodeVO> list = new ArrayList<CodeVO>();
			CodeVO reqCode = null;
			while (rs.next()) {
				reqCode = new CodeVO();           
				reqCode.setCommCd(rs.getString("comm_cd"));    
				reqCode.setCommNm(rs.getString("comm_nm"));    
				list.add(reqCode);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (rs != null) try {rs.close();} catch (Exception e) {}
			if (pstmt != null) try { pstmt.close();} catch (Exception e) {}
			if (conn != null) try { conn.close();} catch (Exception e) {}

		}

	}

}
