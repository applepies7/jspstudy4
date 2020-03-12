package com.study.common.exception;

public class BizAccessFailException extends BizException {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BizAccessFailException() {
		super();
		
	}

	public BizAccessFailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BizAccessFailException(String message, Throwable cause) {
		super(message, cause);
		
	}

		
	public BizAccessFailException(String message) {
		super(message);
		
	}

	public BizAccessFailException(Throwable cause) {
		super(cause);
		
	}



	

}
