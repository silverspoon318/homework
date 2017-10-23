package com.swkim.urlshortener.exception;

@SuppressWarnings("serial")
public class ShortenerException extends Exception {
	private int errorCode;
	private String errorMessage;
	
	public ShortenerException() {
		super();
	}
	
	public ShortenerException(int errorCode){
		this.setErrorCode(errorCode);
	}
	
	public ShortenerException setErrorCode(int errorCode){
		this.errorCode = errorCode;
		return this;
	}
	
	public int getErrorCode(){
		return errorCode;
	}
	
	public ShortenerException setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
