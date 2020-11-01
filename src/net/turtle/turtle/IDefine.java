package net.turtle.turtle;

public interface IDefine {
	
	void define(String variable, String value);
	
	String getDefine(String variable);
	
	boolean hasDefine(String variable);
	
	String getDefineByValue(String value);
	
	boolean hasDefineByValue(String value);
}
