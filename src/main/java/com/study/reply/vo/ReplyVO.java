package com.study.reply.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ReplyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int reNo; 				/* 댓글번호 */
	private String reCategory; 		/* 분류(FREE, BOARD,PDS,..) */
	private int reParentNo; 		/* 부모 번호 */
	private String reMemId; 		/* 작성자ID */
	private String reContent; 		/* 내용 */
	private String reIp; 			/* ip */
	private String reRegDate; 		/* 등록일 */
	private String reModDate; 		//* 수정일 */

	private String reMemName;
	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(this);
	}

	public int getReNo() {
		return reNo;
	}

	public void setReNo(int reNo) {
		this.reNo = reNo;
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

	public String getReMemId() {
		return reMemId;
	}

	public void setReMemId(String reMemId) {
		this.reMemId = reMemId;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public String getReIp() {
		return reIp;
	}

	public void setReIp(String reIp) {
		this.reIp = reIp;
	}

	public String getReRegDate() {
		return reRegDate;
	}

	public void setReRegDate(String reRegDate) {
		this.reRegDate = reRegDate;
	}

	public String getReModDate() {
		return reModDate;
	}

	public void setReModDate(String reModDate) {
		this.reModDate = reModDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getReMemName() {
		return reMemName;
	}

	public void setReMemName(String reMemName) {
		this.reMemName = reMemName;
	}

}