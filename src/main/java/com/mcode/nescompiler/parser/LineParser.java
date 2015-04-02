package com.mcode.nescompiler.parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcode.nescompiler.program.AliasTable;

/**
 * This class is fuckeduply bad.
 *
 */
public class LineParser {
	private static final String ELEMENTS_REGEX = "(\"[^\"]*\")|(\\S+)";
	private static final String ALIAS_REGEX    = "@([^\\s]+)";
	
	public static String stripComments(String line) {
		if(line == null) {
			return null;
		}
		
		// TODO: Check that semi colon is not within string quotes.
		return line.replaceAll(";.*", "");
	}
	
	public static String replaceAlias(String line) {
		if(line == null)
			return null;
		Matcher m = Pattern.compile(ALIAS_REGEX).matcher(line);
		
		while(m.find()) {
			String alias = m.group(1);
			line = line.replace("@"+alias, AliasTable.getReplacement(alias));
		}
		return line;
	}
	
	public static String parseCommandCode(String line) {
		if(line == null) {
			return null;
		}
		int index = line.trim().indexOf(" ");
		if(index == -1) {
			return line;
		} else {
			return line.trim().substring(0, index);
		}
	}
	
	public static String parseParametersString(String line) {
		if(line == null) {
			return null;
		}
		int index = line.trim().indexOf(" ");
		if(index == -1) {
			return null;
		} else {
			return line.trim().substring(index);
		}
	}
	
	public static Queue<String> parseElements(String line) {
		Queue<String> elements = new LinkedList<String>();
		Matcher m = Pattern.compile(ELEMENTS_REGEX).matcher(line);
		
		while(m.find()) {
			elements.add(m.group());
		}
		return elements;
	}
}
