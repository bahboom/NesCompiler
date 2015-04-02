package com.mcode.nescompiler.program;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcode.nescompiler.parser.NumberParser;
import com.mcode.nescompiler.parser.StringParser;

public class Parameter {	
	private static final String ENTITY_REGEX = "\\((.+)\\)|([^\\s]+)";
	private String paramStr = null;
	
	public Parameter(String parameterStr) {
		paramStr = parameterStr;
	}
	
	public boolean isString() {
		return StringParser.isQuoted(paramStr);
	}
	
	public String getStringValue() {
		return StringParser.trimSurround(paramStr);
	}

	public int evaluate() {
		// TODO: Please. This code is shit.
		// But, this fucking works...
		// But this code is still shitty!
		
		Matcher m = Pattern.compile(ENTITY_REGEX).matcher(paramStr);
		Deque<String> stack = new ArrayDeque<String>();
		while(m.find()) {
			String entity = m.group();
			if(entity.equals("+") || entity.equals("-")) {
				stack.push(entity);
			} else if(StringParser.isWithinParenthesis(entity)) {
				Parameter p = new Parameter(StringParser.trimSurround(entity));
				stack.push("" + p.evaluate());
				
				if(stack.size() > 1) {
					String num1 = stack.pop();
					String sign = stack.pop();
					String num2 = stack.pop();
					if(sign.equals("+")) {
						stack.push("" + (NumberParser.parse(num1) + NumberParser.parse(num2)));
					} else if(sign.equals("-")) {
						stack.push("" + (NumberParser.parse(num1) - NumberParser.parse(num2)));
					} else {
						throw new RuntimeException("Syntax error man..");
					}
				}
			} else if(stack.isEmpty()) {
				stack.push(entity);
			} else {
				String sign = stack.pop();
				if(sign.equals("+")) {
					String num = stack.pop();
					stack.push("" + (NumberParser.parse(num) + NumberParser.parse(entity)));
				} else if(sign.equals("-")) {
					String num = stack.pop();
					stack.push("" + (NumberParser.parse(num) - NumberParser.parse(entity)));
				} else {
					throw new RuntimeException("Syntax error man..");
				}
			}
		}
		
		if(stack.size() == 1) {
			return NumberParser.parse(stack.pop());
		} else {
			throw new RuntimeException("WTF");
		}

	}
}
