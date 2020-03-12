package com.study.reply.web;

import java.util.ArrayList;
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

import com.study.reply.service.IReplyService;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

@Controller
public class ReplyController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IReplyService replyService;

	// map: result data,count,msg
	// 또는 위 정보를 담는 vo생성

	@RequestMapping("/reply/replyList")
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

	@RequestMapping(value = "/reply/replyRegist", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> replyRegist(ReplyVO reply, HttpServletRequest req, HttpSession session)
			throws Exception {
		reply.setReIp(req.getRemoteAddr());

		// TODO
//		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		reply.setReMemId("a001");
		replyService.registReply(reply);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		// map.put("data", replyService.getReply(reply.getReNo()));
		map.put("msg", "정상등록 되었습니다.");

		return map;

	}
}
