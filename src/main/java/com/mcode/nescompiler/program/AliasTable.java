package com.mcode.nescompiler.program;

import java.util.HashMap;
import java.util.Map;

public class AliasTable {
	private static Map<String, String> aliasMap = new HashMap<String, String>();
	public static void setAlias(String name, String replacement) {
		aliasMap.put(name, replacement);
	}
	
	public static String getReplacement(String alias) {
		return aliasMap.get(alias);
	}
}
