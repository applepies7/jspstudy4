package com.study.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.common.exception.BizAccessFailException;
import com.study.common.exception.BizException;
import com.study.common.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.reply.dao.IReplyDao;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl  implements IReplyService{
	
	
	@Autowired
	private IReplyDao replyDao ;
	
	@Override
	public List<ReplyVO> getReplyList(ReplySearchVO searchVO) throws BizException {
		int rowCount = replyDao.getReplyCount(searchVO);
		searchVO.setTotalRecordCount(rowCount);
		searchVO.setting();
		return replyDao.getReplyList(searchVO);
	}

	@Override
	public void registReply(ReplyVO reply) throws BizException {
		replyDao.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws BizException {
		ReplyVO vo = replyDao.getReply(reply.getReNo());
		if(vo == null) {
			throw new BizNotFoundException("글번호[" + reply.getReNo() + "]을 조회하지 못했습니다." ); 
		}
		if(!vo.getReMemId().equals(reply.getReMemId())) {
			throw new BizAccessFailException("해당 글의 작성자가 아닙니다." );
		}
		replyDao.updateReply(reply);
	}

	@Override
	public void removeReply(ReplyVO reply) throws BizException {
		
		ReplyVO vo = replyDao.getReply(reply.getReNo());
		if(vo == null) {
			throw new BizNotFoundException("글번호[" + reply.getReNo() + "]을 조회하지 못했습니다." ); 
		}
		if(!vo.getReMemId().equals(reply.getReMemId())) {
			throw new BizAccessFailException("해당 글의 작성자가 아닙니다." );
		}
		replyDao.deleteReply(reply);
	}

	public void getReply(ReplyVO reply) {
		ReplyVO vo = replyDao.getReply(reply.getReNo());
		if(vo == null) {
			throw new BizNotFoundException("글번호[" + reply.getReNo() + "]을 조회하지 못했습니다." ); 
		}
	}

}
