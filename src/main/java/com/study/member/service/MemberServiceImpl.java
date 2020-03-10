/**
 * 
 */
package com.study.member.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.common.exception.BizDuplicateException;
import com.study.common.exception.BizException;
import com.study.common.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
@Service
public class MemberServiceImpl  implements IMemberService{
	@Autowired
	private IMemberDao memberDao;
	
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) throws BizException {
		int rowCount;
		try {
			rowCount = memberDao.getMemberCount(searchVO);
			searchVO.setTotalRecordCount(rowCount);
			searchVO.setting();
			return memberDao.getMemberList(searchVO);
		} catch (SQLException e) {
			throw new BizException(e);
		}
	}

	@Override
	public MemberVO getMember(String memId) throws BizException {
		try {
			MemberVO member = memberDao.getMember(memId);
			if(member == null) {
				throw new BizNotFoundException("회원 [" + memId + "]을 조회하지 못했습니다." ); 
			}
			return member;
		} catch (SQLException e) {
			throw new BizException(e);
		}
	}

	@Override
	public void registMember(MemberVO member) throws BizException {
		try {
			memberDao.insertMember(member);
		} catch (SQLException e) {
			if(e.getErrorCode()==1) {  // 중복에러  
				throw new BizDuplicateException("회원 아이디 중복", member.getMemId());
			}
			throw new BizException(e);
		}
	}

	@Override
	public void modifyMember(MemberVO member) throws BizException {
		try {
			MemberVO vo = memberDao.getMember(member.getMemId());
			if(vo == null) {
				throw new BizNotFoundException("회원 [" + member.getMemId() + "]을 조회하지 못했습니다." ); 
			}
			memberDao.updateMember(member);
		} catch (SQLException e) {
			throw new BizException(e);
		}
	}

	@Override
	public void removeMember(MemberVO member) throws BizException {
		try {
			MemberVO vo = memberDao.getMember(member.getMemId());
			if(vo == null) {
				throw new BizNotFoundException("회원 [" + member.getMemId() + "]을 조회하지 못했습니다." ); 
			}
			memberDao.deleteMember(member);
		} catch (SQLException e) {
			throw new BizException(e);
		}
	}

}
