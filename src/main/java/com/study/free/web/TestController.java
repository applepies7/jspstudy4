package com.study.free.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping(value = { "/hello", "/malja" })
	public ModelAndView applepies() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "hello world");
		mav.setViewName("a"); ///WEB-INF/views/a.jsp 와 같음.
		return mav; 

	}

	@RequestMapping(value = "/hello", params = {"id"} )
	public ModelAndView applepies2(String id) throws Exception {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", new String[] {"이재혁","고용한","백재영"});
		map.put("count", 126);
		
		mav.addAllObjects(map);
		mav.addObject("id", id);
		mav.addObject("msg", "사랑해요 밀키스");
		mav.setViewName("a"); ///WEB-INF/views/a.jsp 와 같음.
		return mav; 
		
	}

}
