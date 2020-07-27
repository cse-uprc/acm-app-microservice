package com.acm.service.exceptions;

public class InvalidParamValueException extends Exception {

	private static final long serialVersionUID = -2845627044591951665L;

	public InvalidParamValueException(String message, String param, String file, int line) {
		super(String.format("%s %s \n\tFile Location: %s.acmsql:%s", message, param, file, line));
	}
}
