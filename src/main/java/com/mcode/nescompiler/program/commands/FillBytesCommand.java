package com.mcode.nescompiler.program.commands;

import java.util.ArrayList;
import java.util.List;

import com.mcode.nescompiler.program.Parameter;
import com.mcode.nescompiler.util.ByteBuilder;

public class FillBytesCommand implements Command {

	private List<Parameter> params = new ArrayList<Parameter>();
	
	@Override
	public void init(List<Parameter> parameters) {
		params.addAll(parameters);
	}
	
	@Override
	public int calculateNumBytes() {
		return getBytes().length;
	}
	
	@Override
	public byte[] getBytes() {
		ByteBuilder bb = new ByteBuilder();
		int count = params.get(0).evaluate();
		for(int i = 0; i < count; i++) {
			bb.putByte(params.get(1).evaluate());
		}
		return bb.getBytes();
	}





}
