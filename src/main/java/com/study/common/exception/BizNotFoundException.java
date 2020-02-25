package com.study.common.exception;

public class BizNotFoundException extends BizException {
	
	
	private static final long serialVersionUID = 8836050195284100996L;

	public BizNotFoundException() {
		super();
		
	}

	public BizNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BizNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

		
	public BizNotFoundException(String message) {
		super(message);
		
	}

	public BizNotFoundException(Throwable cause) {
		super(cause);
		
	}



	

}
