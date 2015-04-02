package com.mcode.nescompiler;

import java.io.File;
import java.io.IOException;

import com.mcode.nescompiler.parser.ProgramParser;
import com.mcode.nescompiler.program.ActionAndMetaData;
import com.mcode.nescompiler.program.Program;

/**
 * To write good code, you have to at least begin writing shitty code.
 *
 */
public class NesCompiler {

	public static void main(String[] args) throws IOException {
		String src = args[0];
		String out = args[1];
		
		ProgramParser parser = new ProgramParser(new File(src));
		Program prg = new Program();
		
		ActionAndMetaData action = parser.nextAction();
		
		while(action != null) {
			prg.addAction(action);
			action = parser.nextAction();
		}
		
		long startTime = System.currentTimeMillis();
		int bytesWritten = prg.compile(new File(out));
		System.out.println("Compiled [" + out + "] in " + (System.currentTimeMillis() - startTime) + " ms!");
		System.out.println("Bytes Written: " + bytesWritten);
	}

}
