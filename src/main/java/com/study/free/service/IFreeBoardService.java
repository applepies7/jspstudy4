package com.study.free.service;

import java.util.List;

import com.study.common.exception.BizException;
import com.study.free.vo.FreeBoardVO;
import com.study.free.vo.FreeSearchVO;

public interface IFreeBoardService {

	
	public List<FreeBoardVO> getBoardList(FreeSearchVO searchVO) throws BizException;

	public FreeBoardVO getBoard(int boNum) throws BizException;

	public void registBoard(FreeBoardVO board) throws BizException;

	public void modifyBoard(FreeBoardVO board) throws BizException;

	public void removeBoard(FreeBoardVO board) throws BizException;

	public void increaseHit(int boNum) throws BizException;
}
