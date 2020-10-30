package net.turtle.turtle;

import java.util.HashMap;

public class DefineHelper implements IDefine {
	
	private final HashMap<String, String> variables = new HashMap();

	@Override
	public void define(String variable, String value) {
		variables.put(variable, value);
	}

	@Override
	public String getDefinedValue(String variable) {
		return variables.get(variable);
	}

	@Override
	public boolean hasDefinedValue(String variable) {
		return variables.containsKey(variable);
	}
}
