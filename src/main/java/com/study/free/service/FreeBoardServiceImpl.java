package com.study.free.service;

import java.sql.SQLException;
import java.util.List;

import com.study.common.exception.BizDuplicateException;
import com.study.common.exception.BizException;
import com.study.common.exception.BizNotFoundException;
import com.study.common.exception.BizRegistFailException;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;
import com.study.free.vo.FreeSearchVO;

public class FreeBoardServiceImpl implements IFreeBoardService {

	private IFreeBoardDao freeDao = new FreeBoardDaoOracle();

	@Override
	public List<FreeBoardVO> getBoardList(FreeSearchVO searchVO) throws BizException {
		try {
			int rowCount = freeDao.getBoardCount(searchVO);
			searchVO.setTotalRecordCount(rowCount);
			searchVO.setting();
			return freeDao.getBoardList(searchVO);
		} catch (SQLException e) {
			throw new BizException(e);
		}
	}

	@Override
	public FreeBoardVO getBoard(int boNum) throws BizException {
		try {
			FreeBoardVO view = freeDao.getBoard(boNum);
			if (view == null) {
				throw new BizNotFoundException("글번호" + boNum + "을 조회하지 못했습니다.");
			}
			return view;

		} catch (SQLException e) {
			throw new BizException(e);

			
		}

	}

	@Override
	public void registBoard(FreeBoardVO board) throws BizException {
		try {
			int res = freeDao.insertBoard(board);
			if (res < 1) {
				throw new BizRegistFailException();
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				throw new BizDuplicateException(e);
			}
			throw new BizException(e);
		}

	}

	@Override
	public void modifyBoard(FreeBoardVO board) throws BizException {
		try {
			int res = freeDao.updateBoard(board);
			if (res < 1) {
				throw new BizRegistFailException();
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				throw new BizDuplicateException(e);
			}
			throw new BizException(e);
		}
	}

	@Override
	public void removeBoard(FreeBoardVO board) throws BizException {

	}

	@Override
	public void increaseHit(int boNum) throws BizException {
//		try {
//			freeDao.increaseHit(boNum);
//		} catch (SQLException e) {
//			throw new BizException(e);
//		}
	}

}
