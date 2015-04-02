package com.mcode.nescompiler.program.commands;

import java.util.ArrayList;
import java.util.List;

import com.mcode.nescompiler.program.Parameter;
import com.mcode.nescompiler.util.ByteBuilder;

public class DefineByteThenWordCommand implements Command {
	List<Parameter> parameters = new ArrayList<Parameter>();
	@Override
	public void init(List<Parameter> parameters) {
		// TODO: check for too many parameters.
		this.parameters.addAll(parameters);
	}

	@Override
	public int calculateNumBytes() {
		// 1byte + 1word = 3 bytes
		return 3;
	}

	@Override
	public byte[] getBytes() {
		ByteBuilder bb = new ByteBuilder();
		bb.putByte(parameters.get(0).evaluate());
		bb.putWord(parameters.get(1).evaluate());
		return bb.getBytes();
	}



}
