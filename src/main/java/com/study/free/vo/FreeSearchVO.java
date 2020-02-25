package com.study.free.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vo.PagingVO;

public class FreeSearchVO extends PagingVO {

	private String searchCategory;
	private String searchType;
	private String searchWord;
	
	
	
	public FreeSearchVO() {
		super();
	}



	public FreeSearchVO(String searchCategory, String searchType, String searchWord) {
		super();
		this.searchCategory = searchCategory;
		this.searchType = searchType;
		this.searchWord = searchWord;
	}



	public String getSearchCategory() {
		return searchCategory;
	}



	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
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



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
	}




}
