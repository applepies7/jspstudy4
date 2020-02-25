package com.study.util;

import java.util.regex.Pattern;

public class DebugTest {

	public int sum(int max) {
		int s = 0;
		for (int i = 0; i < max; i++) {
			s = i;
		}
		return s;
	}

	public static void main(String[] args) {

		int currentPageNo = 1; // 현재 페이지 번호
		int recordCountPerPage = 0; // 한 페이지당 게시되는 게시물 건
		int pageSize = 0 ; // 페이지 리스트에 게시되는 페이지 건수
		int totalRecordCount = 3072; // 전체 게시물 건 수
		
		int totalPageCount = 0 ; // 페이지 개수 //totalPageCount = ((totalRecordCount-1)/recordCountPerPage) + 1
		int firstPageNoOnPageList = 0; // 페이지 리스트의 첫 페이지 번호 //firstPageNoOnPageList =((currentPageNo-1)/pageSize)*pageSize + 1
		int lastPageNoOnPageList = 0; // 페이지 리스트의 마지막 페이지 번호 //lastPageNoOnPageList = firstPageNoOnPageList+pageSize-1
										// if(lastPageNoOnPageList>totalRecordCount){lastPageNoOnPageList=totalPageCount}
		int firstRecordIndex = 0; // SQL의 조건절 시작 rownum //firstRecordIndex = ((currentPageNo - 1) * recordCountPerPage) + 1
		int lastRecordIndex = 0; // SQL의 조건절 마지막 rownum //lastRecordIndex = currentPageNo * recordCountPerPage
		int totalRowCount = 0; //총 글 수
		
		
		
		
		firstRecordIndex = ((currentPageNo - 1) * recordCountPerPage) + 1;
		lastRecordIndex = currentPageNo * recordCountPerPage;  //
		totalPageCount = ((totalRecordCount -1)/recordCountPerPage)+1; //총페이지수 
		
		
		System.out.println();
		
		
		
		
		
		
		
		// DebugTest test = new DebugTest();
//		int r =  test.sum(100);
//		System.out.println(r);

		// 34번 글 읽었을때
//		
//	String free = "77|34|2|90";
//	///7번글이 쿠키에 있나?
//	int num = 34;
//	Pattern.compile("\\b" +num +"\\b").matcher(free).find();
//
//	
//	System.out.println(free.matches("\\b27\\b"));
//	System.out.println(Pattern.compile("\\b" +num +"\\b").matcher(free).find()
//			);

	}

}
