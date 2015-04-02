package com.mcode.nescompiler.program.directives;

import com.mcode.nescompiler.program.Action;

public interface Directive extends Action {
	void execute();
}
