package com.study.reply.vo;


import org.apache.commons.lang3.builder.ToStringBuilder;

import com.study.common.vo.PagingVO;


public class ReplySearchVO extends PagingVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String reCategory; 		/* 분류(FREE, BOARD,PDS,..) */
	private int reParentNo; 		/* 부모 번호 */

	public ReplySearchVO() {
		super();
	}

	public ReplySearchVO(String reCategory, int reParentNo) {
		super();
		this.reCategory = reCategory;
		this.reParentNo = reParentNo;
	}

	public String getReCategory() {
		return reCategory;
	}

	public void setReCategory(String reCategory) {
		this.reCategory = reCategory;
	}

	public int getReParentNo() {
		return reParentNo;
	}

	public void setReParentNo(int reParentNo) {
		this.reParentNo = reParentNo;
	}

	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}
	
}