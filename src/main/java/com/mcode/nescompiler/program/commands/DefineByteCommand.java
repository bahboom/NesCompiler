package com.mcode.nescompiler.program.commands;

import java.util.ArrayList;
import java.util.List;

import com.mcode.nescompiler.program.Parameter;
import com.mcode.nescompiler.util.ByteBuilder;

public class DefineByteCommand implements Command {

	private List<Parameter> parameters = new ArrayList<Parameter>();

	@Override
	public void init(List<Parameter> parameters) {
		this.parameters.addAll(parameters);
	}

	@Override
	public byte[] getBytes() {
		ByteBuilder bb = new ByteBuilder();
		for(Parameter param : parameters) {
			if(param.isString()) {
				bb.putBytes(param.getStringValue().getBytes());
			} else {
				bb.putByte(param.evaluate());
			}
		}

		return bb.getBytes();
	}


	@Override
	public int calculateNumBytes() {
		int byteCount = 0;

		for(Parameter p : parameters) {
			if(p.isString()) {
				byteCount += p.getStringValue().length();
			} else {
				byteCount++;
			}
		}

		return byteCount;
	}

}
