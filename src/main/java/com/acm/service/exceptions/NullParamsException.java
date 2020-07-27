package com.acm.service.exceptions;

public class NullParamsException extends Exception {

	private static final long serialVersionUID = -2845627044591951665L;

	public NullParamsException(String expectedInsertion) {
		super("params was null");
	}
}
