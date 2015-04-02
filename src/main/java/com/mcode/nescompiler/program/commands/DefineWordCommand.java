package com.mcode.nescompiler.program.commands;

import java.util.ArrayList;
import java.util.List;

import com.mcode.nescompiler.program.Parameter;
import com.mcode.nescompiler.util.ByteBuilder;

public class DefineWordCommand implements Command {
	private List<Parameter> params = new ArrayList<Parameter>();
	
	@Override
	public void init(List<Parameter> parameters) {
		params.addAll(parameters);
	}
	
	@Override
	public int calculateNumBytes() {
		return params.size() * 2;
	}
	
	@Override
	public byte[] getBytes() {
		ByteBuilder bb = new ByteBuilder();
		for(Parameter param : params) {
			bb.putWord(param.evaluate());
		}
		return bb.getBytes();
	}
}
