package com.study.member.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vo.PagingVO;

public class MemberSearchVO extends PagingVO {

	private String searchType;
	private String searchWord;
	private String searchJob;

	public MemberSearchVO() {
		super();
	}

	public MemberSearchVO(String searchType, String searchWord, String searchJob) {
		super();
		this.searchType = searchType;
		this.searchWord = searchWord;
		this.searchJob = searchJob;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchJob() {
		return searchJob;
	}

	public void setSearchJob(String searchJob) {
		this.searchJob = searchJob;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
