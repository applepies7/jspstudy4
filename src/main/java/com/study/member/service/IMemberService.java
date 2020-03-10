
package com.study.member.service;

import java.sql.SQLException;
import java.util.List;

import com.study.common.exception.BizDuplicateException;
import com.study.common.exception.BizException;
import com.study.common.exception.BizNotFoundException;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;


/**
 * @author sunja
 *
 */
public interface IMemberService {


	/**
	 * @param searchVO
	 * @return List<MemberVO> 
	 * @throws BizException
	 */
	
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) throws BizException;
	
	/**
	 * 요청한 <b>회원아이디</b>에 해당하는 회원을 조회한다. 
	 *         해당 회원이 존재하지 않는 경우 BizNotFoundException
	 * @param memId
	 * @return MemberVO
	 * @throws BizException
	 */
	public MemberVO getMember(String memId) throws BizException, BizNotFoundException;
	
	/**
	 * 회원등록 <br>
	 * 중복이 발생하면 BizDuplicateException
	 * @param member
	 * @throws BizException 
	 */
	public void registMember(MemberVO member) throws BizException,BizDuplicateException;
	
	/**
	 * 회원정보 수정 <br>
	 * 비밀번호는 변경되지 않음 
	 * 회원존재 하지 않으면 BizNotFoundException
	 * @param member
	 * @throws BizException 
	 *   
	 */
	public void modifyMember(MemberVO member) throws BizException , BizNotFoundException;
	
	/**
	 * 회원탈퇴 <br>
	 * 회원테이블의 mem_del_yn을 "Y" 로 변경  
	 * @param member
	 * @throws BizException
	 */
	public void removeMember(MemberVO member) throws BizException;
	
}
