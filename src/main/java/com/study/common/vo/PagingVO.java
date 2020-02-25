package com.study.common.vo;

/**
 * @author pc15
 *
 */
public class PagingVO {

	private int currentPageNo; // 현재 페이지 번호
	private int recordCountPerPage; // 한 페이지당 게시되는 게시물 건
	private int pageSize; // 페이지 리스트에 게시되는 페이지 건수
	private int totalRecordCount; // 전체 게시물 건 수
  
	private int totalPageCount; // 페이지 개수 //totalPageCount = ((totalRecordCount-1)/recordCountPerPage) + 1
	private int firstPageNoOnPageList; // 페이지 리스트의 첫 페이지 번호 //firstPageNoOnPageList
	 							// =((currentPageNo-1)/pageSize)*pageSize + 1
	private int lastPageNoOnPageList; // 페이지 리스트의 마지막 페이지 번호 //lastPageNoOnPageList = firstPageNoOnPageList+pageSize-1
	private int firstRecordIndex; // SQL의 조건절 시작 rownum //firstRecordIndex = ((currentPageNo - 1) *
	private int lastRecordIndex; // SQL의 조건절 마지막 rownum //lastRecordIndex = currentPageNo * recordCountPerPage

	public void setting() {
		if (currentPageNo < 1)
			currentPageNo = 1;
		if (recordCountPerPage < 1)
			recordCountPerPage = 10;
		if (pageSize < 1) pageSize = 10;
		
		firstRecordIndex = ((currentPageNo - 1) * recordCountPerPage) + 1;
		lastRecordIndex = currentPageNo * recordCountPerPage; //
		totalPageCount = ((totalRecordCount - 1) / recordCountPerPage) + 1; // 총페이지수
		
		firstPageNoOnPageList =((currentPageNo-1)/pageSize)*pageSize + 1;
		lastPageNoOnPageList = firstPageNoOnPageList+pageSize-1;
					
		if(lastPageNoOnPageList>totalPageCount)lastPageNoOnPageList = totalPageCount;

	}

	public PagingVO() {
		super();
	}

	public PagingVO(int currentPageNo, int recordCountPerPage, int pageSize, int totalRecordCount, int totalPageCount,
			int firstPageNoOnPageList, int lastPageNoOnPageList, int firstRecordIndex, int lastRecordIndex) {
		super();
		this.currentPageNo = currentPageNo;
		this.recordCountPerPage = recordCountPerPage;
		this.pageSize = pageSize;
		this.totalRecordCount = totalRecordCount;
		this.totalPageCount = totalPageCount;
		this.firstPageNoOnPageList = firstPageNoOnPageList;
		this.lastPageNoOnPageList = lastPageNoOnPageList;
		this.firstRecordIndex = firstRecordIndex;
		this.lastRecordIndex = lastRecordIndex;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPageNoOnPageList() {
		return firstPageNoOnPageList;
	}

	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}

	public int getLastPageNoOnPageList() {
		return lastPageNoOnPageList;
	}

	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
		this.lastPageNoOnPageList = lastPageNoOnPageList;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	@Override
	public String toString() {
		return "PagingVO [currentPageNo=" + currentPageNo + ", recordCountPerPage=" + recordCountPerPage + ", pageSize="
				+ pageSize + ", totalRecordCount=" + totalRecordCount + ", totalPageCount=" + totalPageCount
				+ ", firstPageNoOnPageList=" + firstPageNoOnPageList + ", lastPageNoOnPageList=" + lastPageNoOnPageList
				+ ", firstRecordIndex=" + firstRecordIndex + ", lastRecordIndex=" + lastRecordIndex + "]";
	}
	
	
	
	
}
