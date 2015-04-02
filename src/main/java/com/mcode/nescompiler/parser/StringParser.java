package com.mcode.nescompiler.parser;

public class StringParser {
	public static boolean isQuoted(String str) {
		return str != null && str.startsWith("\"") && str.endsWith("\"");
	}
	
	public static boolean isWithinParenthesis(String str) {
		return str != null && str.startsWith("(") && str.endsWith(")");
	}
	
	public static String trimSurround(String str) {
		return str.substring(1, str.length() - 1);
	}
}
