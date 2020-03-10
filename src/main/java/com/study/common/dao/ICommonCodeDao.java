package com.study.common.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.common.vo.CodeVO;

@Mapper
public interface ICommonCodeDao {
	
	List<CodeVO> getCodeListByParent(String code) throws SQLException;
	
}