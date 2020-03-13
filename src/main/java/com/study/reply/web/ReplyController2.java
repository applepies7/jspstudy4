package com.study.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.common.exception.BizAccessFailException;
import com.study.common.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.reply.service.IReplyService;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

//@Controller
public class ReplyController2 {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IReplyService replyService;

	// map: result data,count,msg
	// 또는 위 정보를 담는 vo생성

	@RequestMapping(value = "/reply/replyList" ,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> replyList(ReplySearchVO searchVO) throws Exception {
		logger.debug("searchVO = {}", searchVO);

		List<ReplyVO> list = replyService.getReplyList(searchVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("data", list);
		map.put("count", list.size());
		System.out.println(map);

////		ReplyVO vo = new ReplyVO();
//		vo.setReContent("코로나 19 조심조심");
//		list.add(vo);
//		vo = new ReplyVO();
//		vo.setReContent("너도 조심조심 지각 하지 말고....");
//		list.add(vo);

		return map; // reply/

	}

	@RequestMapping(value = "/reply/replyRegist", method = RequestMethod.POST, produces = "application/json; charset=UTF-8" )
	@ResponseBody
	public Map<String, Object> replyRegist(ReplyVO reply, HttpServletRequest req, HttpSession session)
			throws Exception {
		logger.debug("ReplyVO = {}", reply);
		reply.setReIp(req.getRemoteAddr());
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		replyService.registReply(reply);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		//map.put("data", replyService.getReply(reply.getReNo()));
		map.put("msg", "정상 등록 되었습니다.");

		return map;

	}
	
	@RequestMapping(value="/reply/replyRemove")
	@ResponseBody
	public Map<String, Object> replyRemove(ReplyVO reply, HttpServletRequest req, HttpSession session)
			throws Exception {
		logger.debug("ReplyVO = {}", reply);
		
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId(user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
//		replyService.removeReply(reply);
////		map.put("result", true);
//		//map.put("data", replyService.getReply(reply.getReNo()));
//		map.put("msg", "정상 등록 되었습니다.");
//
//		return map;

		try {
			replyService.removeReply(reply);		
			map.put("result", true);
			map.put("msg", "정상 삭제 되었습니다.");
			return map; 
		} catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("msg", "글이 존재하지 않습니다.");
			return map; 			
		} catch (BizAccessFailException e) {
			map.put("result", false);
			map.put("msg", "접근에 실패했습니다.");
			return map; 
		}
		
		
	}
	
}
