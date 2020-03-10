package com.study.common.service;

import java.util.List;

import com.study.common.exception.BizException;
import com.study.common.vo.CodeVO;

public interface ICommonCodeService {
	
	
	/** 공통코드를 조회하여 리턴 <br> 
	 *  부모(그룹키)에 해당하는 하위 코드목록 리턴  
	 * @param code
	 * @return List<CodeVO>
	 * @throws BizException
	 */
	List<CodeVO> getCodeListByParent(String code) throws BizException;
	
}
