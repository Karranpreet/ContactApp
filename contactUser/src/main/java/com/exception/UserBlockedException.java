package com.exception;

public class UserBlockedException extends Exception {
 
	public UserBlockedException() {
		
	}
    
	public UserBlockedException(String errorDesc) {
		super(errorDesc);
	}
}
