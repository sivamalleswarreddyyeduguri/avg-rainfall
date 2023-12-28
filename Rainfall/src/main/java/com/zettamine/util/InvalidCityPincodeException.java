package com.zettamine.util;

public class InvalidCityPincodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCityPincodeException(String invalid) {
		super(invalid);
	}
		
}
