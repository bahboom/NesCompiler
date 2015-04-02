package com.mcode.nescompiler.program;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.mcode.nescompiler.exception.ByteCalculationException;
import com.mcode.nescompiler.program.commands.Command;
import com.mcode.nescompiler.program.directives.Directive;

public class Program {
	
	private List<ActionAndMetaData> actionList = new ArrayList<ActionAndMetaData>();
	
	public void addAction(ActionAndMetaData action) {
		actionList.add(action);
	}
	
	/**
	 * 
	 * @param output
	 * @return number of bytes written
	 * @throws IOException
	 */
	public int compile(File output) throws IOException {
		int bytesWritten = 0;
		OutputStream os = new BufferedOutputStream(new FileOutputStream(output));
		
		// First pass. Go through the directives first. Setup the AddressTable.
		AddressTable.currentAddress = 0;
		for(ActionAndMetaData actionAndMD : actionList) {
			Action action = actionAndMD.getAction();
			MetaData metadata = actionAndMD.getMetaData();
			
			try {
				if(action instanceof Command) {
					Command cmd = (Command) action;
					AddressTable.currentAddress += cmd.calculateNumBytes();
				} else if (action instanceof Directive) {
					Directive dir = (Directive) action;
					dir.execute();
				}
			} catch (Exception e) {
				System.out.println("Error at line " + metadata.getLineNumber());
				System.out.println(">> " + metadata.getCodeLine());
				
				os.close();
				System.exit(1);
			}
		}
		
		AddressTable.currentAddress = 0;
		for(ActionAndMetaData actionAndMD : actionList) {
			Action action = actionAndMD.getAction();
			MetaData metadata = actionAndMD.getMetaData();
			
			try {
				if(action instanceof Command) {
					Command cmd = (Command) action;
					byte[] bytes = cmd.getBytes();
					if(bytes.length != cmd.calculateNumBytes()) {
						throw new ByteCalculationException("Bytes calucated does not equal actual bytes. \nCalcuated: " + cmd.calculateNumBytes() + " Actual: " + bytes.length + ". \n" + action.toString());
					}
					os.write(bytes, 0, bytes.length);
					bytesWritten += bytes.length;
					
					AddressTable.currentAddress += bytes.length;
				}
			} catch (Exception e) {
				System.out.println("Error at line " + metadata.getLineNumber());
				System.out.println(">> " + metadata.getCodeLine());
				
				os.close();
				System.exit(1);
			}
		}
		
		os.close();
		return bytesWritten;
	}
}
