package net.turtle.turtle;

public interface IDefine {
	
	void define(String variable, String value);
	
	String getDefinedValue(String variable);
	
	boolean hasDefinedValue(String variable);
}
