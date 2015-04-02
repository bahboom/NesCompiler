package com.mcode.nescompiler.program;

/** 
 * 
 * Data about code
 *
 */
public class MetaData {
	
	private String codeLine;
	private int lineNumber;
	public MetaData(String codeLine, int lineNumber) {
		this.codeLine = codeLine;
		this.lineNumber = lineNumber;
	}
	
	public String getCodeLine() {
		return codeLine;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
}
