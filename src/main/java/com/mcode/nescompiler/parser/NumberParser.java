package com.mcode.nescompiler.parser;

import com.mcode.nescompiler.program.AddressTable;

public class NumberParser {
	public static int parse(String numberStr) {
		int sign = 1;
		if(numberStr.startsWith("-") && numberStr.length() > 1) {
			sign = -1;
			numberStr = numberStr.substring(1);
		}
		
		if(numberStr.equals("$")) {
			return AddressTable.currentAddress;
		} else if(numberStr.startsWith("0x")) { // Hex
			numberStr = numberStr.replace("_", "");
			return Integer.parseInt(numberStr.substring(2), 16) * sign;
		} else if(numberStr.startsWith("0b")) { // Binary
			numberStr = numberStr.replace("_", "");
			return Integer.parseInt(numberStr.substring(2), 2) * sign;
		} else if(numberStr.startsWith("0d")) { // Decimal
			numberStr = numberStr.replace("_", "");
			return Integer.parseInt(numberStr.substring(2)) * sign;
		} else if(numberStr.endsWith("*")) {    // Nes Address
			return AddressTable.getNesAddress(numberStr.substring(0, numberStr.length() - 1));
		} else {                                // Decimal
			numberStr = numberStr.replace("_", "");
			return Integer.parseInt(numberStr) * sign;
		}
	} 
}
