package net.turtle;

public interface IResult {
	
	boolean isSuccessful();
	
	default String getMessage() {
		return "";
	}
}
