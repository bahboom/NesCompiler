package com.mcode.nescompiler.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mcode.nescompiler.exception.InvalidCommandException;
import com.mcode.nescompiler.program.Action;
import com.mcode.nescompiler.program.ActionAndMetaData;
import com.mcode.nescompiler.program.AliasTable;
import com.mcode.nescompiler.program.MetaData;
import com.mcode.nescompiler.program.NoAction;
import com.mcode.nescompiler.program.Parameter;
import com.mcode.nescompiler.program.commands.DefineByteCommand;
import com.mcode.nescompiler.program.commands.DefineByteThenWordCommand;
import com.mcode.nescompiler.program.commands.DefineWordCommand;
import com.mcode.nescompiler.program.commands.FillBytesCommand;
import com.mcode.nescompiler.program.directives.AdjustAddressDirective;
import com.mcode.nescompiler.program.directives.CreateLabelDirective;

public class ProgramParser {
	private Queue<String> programSrcLines = new LinkedList<String>();
	
	private int lineNum = 0;
	public ProgramParser(File source) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(source));
		String line = br.readLine();
		while(line != null) {
			programSrcLines.add(line);
			line = br.readLine();
		}
		br.close();
	}
	
	public ActionAndMetaData nextAction() {
		String origCodeLine = programSrcLines.poll();
		lineNum++;
		String line = origCodeLine;
		line = LineParser.stripComments(line);
		line = LineParser.replaceAlias(line);
		
		if(line == null) {
			return null;
		} else if (line.trim().isEmpty()) {
			return nextAction();
		}
		
		Action action = new NoAction();
		
		String code = LineParser.parseCommandCode(line);
		String parametersStr = LineParser.parseParametersString(line);
		List<Parameter> parameters = ParameterParser.parse(parametersStr);
		
		if(code.startsWith("#")) {
			String alias = code.substring(1);
			AliasTable.setAlias(alias, parametersStr);
			return new ActionAndMetaData(action, new MetaData(origCodeLine, lineNum));
		} else if(code.endsWith(":")) {
			action = new CreateLabelDirective();
			// TODO: temp hack
			parameters.clear();
			parameters.add(new Parameter("\"" + (code.substring(0, code.length() - 1) + "\"")));
			action.init(parameters);
		} else {
			switch(code) {
				case ".da": {
					
				} break;
				
				case ".aa": {
					action = new AdjustAddressDirective();
					action.init(parameters);
				} break;
				
				case "db": {
					action = new DefineByteCommand();
					action.init(parameters);
				} break;
				
				case "dbw": {
					action = new DefineByteThenWordCommand();
					action.init(parameters);
				} break;
				
				case "fb": {
					action = new FillBytesCommand();
					action.init(parameters);
				} break;
				
				case "dw": {
					action = new DefineWordCommand();
					action.init(parameters);
				} break;
				
				default: {
					throw new InvalidCommandException("Unknown command code: " + code);
				}
			}	
		}
		return new ActionAndMetaData(action, new MetaData(origCodeLine, lineNum));
	}
	

}
