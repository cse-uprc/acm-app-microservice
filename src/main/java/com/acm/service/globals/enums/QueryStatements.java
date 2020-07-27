package com.acm.service.globals.enums;

import java.util.Arrays;
import java.util.List;

public enum QueryStatements {
	NONE("@NONE", "NONE"), AND("@AND", "AND"), WHERE("@WHERE", "WHERE"), IF("@IF", "IF");

	private String annotation;
	private String type;

	QueryStatements(String annotation, String type) {
		this.annotation = annotation;
		this.type = type;
	}

	public String annotation() {
		return annotation;
	}

	public String text() {
		return type;
	}

	/**
	 * Checks to see if the current enum type is a valid query statement. If it's
	 * not then it will return false
	 * 
	 * @return boolean if type is not equal to NONE
	 */
	public boolean isQueryStatement() {
		return type != "NONE";
	}

	/**
	 * Given a string, it will return the enum that the string contains. If the
	 * string does not contain any of the known annotations it will return NONE
	 * 
	 * @param str - String value to search
	 * @return QueryStatement enum that the string contains (If one exists)
	 */
	public static QueryStatements getEnumFromString(String str) {
		List<QueryStatements> values = Arrays.asList(QueryStatements.values());
		for (QueryStatements value : values) {
			if (str.contains(value.annotation()))
				return value;
		}
		return NONE;
	}
}
