package com.study.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public interface IMemberDao {
	/*
	 * 회원 조회<br>
	 * 요청한 <b>회원아이디에</b> 해당하는 회원을 조회한다. 
	 * @param memId
	 * @return 회원목록
	 * @throws SQLException
	*/	
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) throws SQLException ;
	
	/*
	 * 회원 조회<br>
	 * 요청한 <b>회원아이디에</b> 해당하는 회원을 조회한다. 
	 * @param memId
	 * @return member
	 * @throws SQLException
	 */
	public MemberVO getMember(String memId) throws SQLException;
	
	/*
	 * 회원 등록<br>
	 * 회원을 등록한다. 
	 * @param member
	 * @return int
	 * @throws SQLException
	 */
	
	public int insertMember(MemberVO member) throws SQLException;
	
	/*
	 * 회원 수정<br>
	 * 비밀번호는 수정되지 않음.. 
	 * @param member
	 * @return int
	 * @throws SQLException
	 */
	
	public int updateMember(MemberVO member) throws SQLException;
	
	/*
	 * 회원 탈퇴<br>
	 * 회원테이블의 mem_del_yn을 Y로 변경
	 * @param member
	 * @return int
	 * @throws SQLException
	 */
	public int deleteMember(MemberVO member) throws SQLException;
	
	public int getMemberCount(MemberSearchVO searchVO) throws SQLException;
}
