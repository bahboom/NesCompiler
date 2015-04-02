package com.mcode.nescompiler.program;

import java.util.List;

public interface Action {	
	/**
	 * Psuedo Constructor
	 * Callback method.
	 * @param parameters
	 */
	void init(List<Parameter> parameters);
}
