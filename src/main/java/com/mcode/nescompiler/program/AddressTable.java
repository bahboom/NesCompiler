package com.mcode.nescompiler.program;

import java.util.HashMap;
import java.util.Map;

public class AddressTable {
	public static int currentAddress = 0;
	private static int adjustOffset = 0;
	
	private static Map<String, Integer> table = new HashMap<String, Integer>();
	
	public static void setAddressAdjustment(int adjustment) {
		adjustOffset = adjustment - currentAddress;
	}
	public static void insertAddress(String label, int address) {
		table.put(label, address);
	}
	public static int getAddress(String label) {
		return table.get(label);
	}
	
	public static int getNesAddress(String label) {
		if(label.equals("$"))
			return currentAddress + adjustOffset;
		
		return getAddress(label) + adjustOffset;
	}
}
