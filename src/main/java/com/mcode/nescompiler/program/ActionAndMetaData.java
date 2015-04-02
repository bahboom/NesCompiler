package com.mcode.nescompiler.program;

public class ActionAndMetaData {
	private Action action;
	private MetaData metadata;
	
	public ActionAndMetaData(Action action, MetaData metadata) {
		this.action = action;
		this.metadata = metadata;
	}
	public Action getAction() {
		return action;
	}
	
	public MetaData getMetaData() {
		return metadata;
	}
}
