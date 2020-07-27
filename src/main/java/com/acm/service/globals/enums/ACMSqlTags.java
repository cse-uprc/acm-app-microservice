package com.acm.service.globals.enums;

public enum ACMSqlTags {
	NAME("@NAME"), VALUE_ID(":");

	private String annotation;

	ACMSqlTags(String annotation) {
		this.annotation = annotation;
	}

	public String text() {
		return annotation;
	}

	public boolean in(String str) {
		return str.contains(annotation);
	}
}
