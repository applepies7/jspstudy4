package com.study.member.web;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.common.exception.BizDuplicateException;
import com.study.common.exception.BizRegistFailException;
import com.study.common.service.ICommonCodeService;
import com.study.common.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private ICommonCodeService codeService;
	@Autowired
	private IMemberService memberService;

	@RequestMapping("/memberList.wow")
	public String memberList(@ModelAttribute("searchVO") MemberSearchVO searchVO, ModelMap model) throws Exception {
		
		
		List<CodeVO> a = codeService.getCodeListByParent("JB00");
		List<CodeVO> b = codeService.getCodeListByParent("HB00");
		List<MemberVO> list = memberService.getMemberList(searchVO);
		model.addAttribute("search", searchVO);
		model.addAttribute("list", list);
		model.addAttribute("jobs", a);
		model.addAttribute("likes", b);

		System.out.println(searchVO);

		return "/member/memberList";

	}

	@RequestMapping(value = "/memberView.wow", method = RequestMethod.GET, params = "memId")
	public String memberView(String memId, ModelMap model ) {
		
		MemberVO mem = memberService.getMember(memId);
		model.addAttribute("mem", mem);

		return "/member/memberView.jsp?memId=" + memId;
	}
	
	@RequestMapping("/memberForm.wow")
	public String memberForm(ModelMap model) {
		List<CodeVO> a = codeService.getCodeListByParent("JB00");
		List<CodeVO> b = codeService.getCodeListByParent("HB00");

		model.addAttribute("jobs", a);
		model.addAttribute("likes", b);
		return "/member/memberForm";
	}
	
	@RequestMapping(value = "/memberRegist.wow", method = RequestMethod.POST)
	public String memberRegist(MemberVO member, ModelMap model) {
		try {
			memberService.registMember(member);
			return "/member/memberView.jsp?memId=" + member.getMemId();
		} catch (BizDuplicateException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("등록 실패").setMessage("등록 실패 했음.... 중복아이디입니다.. ")
					.setUrl("/free/freeList.wow").setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
		} catch (BizRegistFailException e) {
			ResultMessageVO message = new ResultMessageVO();
			message.setResult(false).setTitle("등록 실패").setMessage("글 등록 을 실패 했음....").setUrl("/free/freeList.wow")
					.setUrlTitle("목록으로");
			model.addAttribute("resultMessage", message);
			return "common/message";
			}

		
	}
	
//	public String memberEdit() {
//	}
//	public String memberModyfy() {
//	}
//	public String memberDelete() {
//	}
	
}
