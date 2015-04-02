package com.mcode.nescompiler.util;

import java.util.ArrayList;
import java.util.List;

public class ByteBuilder {
	private List<Byte> byteList = new ArrayList<Byte>();
	
	public void putByte(byte b) {
		byteList.add(b);
	}
	public void putByte(int b) {
		// TODO: check overflow?
		byteList.add((byte)(0xFF & b));
	}
	public void putWord(int w) {
		putByte(w);
		putByte(w >> 8);
	}
	public void putBytes(byte[] bytes) {
		for(byte b : bytes) {
			putByte(b);
		}
	}
	
	public byte[] getBytes() {
		byte[] bytes = new byte[byteList.size()];
		for(int i = 0; i < byteList.size(); i++) {
			bytes[i] = byteList.get(i);
		}
		return bytes;
	}
}
