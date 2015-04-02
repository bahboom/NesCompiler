package com.mcode.nescompiler.program.commands;

import com.mcode.nescompiler.program.Action;

public interface Command extends Action {
	/** 
	 * Try to calculate number of bytes without calling Parameter.getValue();
	 * @return
	 */
	int calculateNumBytes();
	byte[] getBytes();
}
