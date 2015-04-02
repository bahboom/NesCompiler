package com.mcode.nescompiler.program.directives;

import java.util.List;

import com.mcode.nescompiler.program.AddressTable;
import com.mcode.nescompiler.program.Parameter;

public class CreateLabelDirective implements Directive {
	private String name;
	
	@Override
	public void init(List<Parameter> parameters) {
		name = parameters.get(0).getStringValue();
	}

	@Override
	public void execute() {
		AddressTable.insertAddress(name, AddressTable.currentAddress);
	}

}
