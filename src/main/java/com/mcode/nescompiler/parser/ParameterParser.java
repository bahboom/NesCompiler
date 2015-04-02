package com.mcode.nescompiler.parser;

import java.util.ArrayList;
import java.util.List;

import com.mcode.nescompiler.program.Parameter;


public class ParameterParser {
	public static List<Parameter> parse(String parametersString) {
		List<Parameter> parameters = new ArrayList<Parameter>();
		if(parametersString == null) {
			return parameters;
		}
		String[] parametersStrList = parametersString.trim().split(",");
		for(String paramStr : parametersStrList) {
			parameters.add(new Parameter(paramStr.trim()));
		}
		return parameters;
	}
	
}
