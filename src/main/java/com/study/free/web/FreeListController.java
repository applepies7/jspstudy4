package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.dao.CommonCodeDaoOracle;
import com.study.common.dao.ICommonCodeDao;
import com.study.common.vo.CodeVO;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.free.vo.FreeSearchVO;
import com.study.servlet.IController;

public class FreeListController implements IController {

	private IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
//private IFreeBoardDao freeDao =  new FreeBoardDaoOracle();
	private ICommonCodeDao codeDao = new CommonCodeDaoOracle();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		FreeSearchVO searchVO = new FreeSearchVO();
		// convertMapToObject(req.getParameterMap(), serachVO);

       //String s1 = req.getParameter("searchWord");
       //String s2 = req.getParameter("searchCategory");
       //String s3 = req.getParameter("recordCountPerPage");
       //String s4 = req.getParameter("searchType");
       //searchVO.setSearchWord(s1);
       //searchVO.setSearchType(s4);
       //searchVO.setSearchCategory(s2);
       //if (s3 != null) {
       //	searchVO.setRecordCountPerPage(Integer.parseInt(s3));
        //}
		// javabean set property와 비슷함. 리퀘스트 파라미터를 맵으로 만들고 \
		// 맵을 vo객체에 매칭해줌.
		BeanUtils.populate(searchVO, req.getParameterMap());
      //int rowCount = freeDao.getBoardCount(searchVO);
      //searchVO.setTotalRecordCount(rowCount);
      //searchVO.setting();
		//List<FreeBoardVO> boardList = freeDao.getBoardList(searchVO);

		List<CodeVO> catList = codeDao.getCodeListByParent("BC00");
		List<FreeBoardVO> boardList = freeBoardService.getBoardList(searchVO);

		req.setAttribute("search", searchVO);
		req.setAttribute("boardList", boardList);
		req.setAttribute("catList", catList);

		return "/WEB-INF/views/free/freeList.jsp";

	}

}
