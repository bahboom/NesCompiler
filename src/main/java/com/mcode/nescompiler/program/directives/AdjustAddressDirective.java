package com.mcode.nescompiler.program.directives;

import java.util.ArrayList;
import java.util.List;

import com.mcode.nescompiler.program.AddressTable;
import com.mcode.nescompiler.program.Parameter;

public class AdjustAddressDirective implements Directive {
	private List<Parameter> params = new ArrayList<Parameter>();
	
	@Override
	public void init(List<Parameter> parameters) {
		params.addAll(parameters);
		
	}
	
	@Override
	public void execute() {
		AddressTable.setAddressAdjustment(params.get(0).evaluate());
	}
	



}
