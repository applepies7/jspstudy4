package com.study.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.study.common.vo.CodeVO;

public interface ICommonCodeDao {
	
	List<CodeVO> getCodeListByParent(String code) throws SQLException;
	
}