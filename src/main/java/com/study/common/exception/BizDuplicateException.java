package com.study.common.exception;

public class BizDuplicateException extends BizException {
	
	private static final long serialVersionUID = 8161410942229781784L;
	private String key;
	
	
	public BizDuplicateException() {
		super();
		
	}

	public BizDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BizDuplicateException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BizDuplicateException(String message, String key) {
		super(message+", key:" + key);
		this.key = key;
		
	}

	public BizDuplicateException(String message) {
		super(message);
		
	}

	public BizDuplicateException(Throwable cause) {
		super(cause);
		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	

}
