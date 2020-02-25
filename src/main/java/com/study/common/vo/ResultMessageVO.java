package com.study.common.vo;

public class ResultMessageVO {

	private boolean result;
	private String title;
	private String message;
	private String url;
	private String urlTitle;
	// getter/ setter 생성
	// setter 메서드는 void 가 아닌 해당 객체를 리턴하도록 변경한다.
	// Method chaining은 중간결과를 저장하지 않고도 단일 명령문으로 호출이 가능하도록 한다.

	public boolean isResult() {
		return result;
	}
	public String getTitle() {
		return title;
	}
	public String getMessage() {
		return message;
	}
	public String getUrl() {
		return url;
	}
	public String getUrlTitle() {
		return urlTitle;
	}
	
	public ResultMessageVO setResult(boolean result) {
		this.result = result;
		return this;
		
	}
	public ResultMessageVO setTitle(String title) {
		this.title = title;
		return this;
	}
	public ResultMessageVO setMessage(String message) {
		this.message = message;
		return this;
	}
	public ResultMessageVO setUrl(String url) {
		this.url = url;
		return this;
	}
	public ResultMessageVO setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
		return this;
	}

	
		
}
