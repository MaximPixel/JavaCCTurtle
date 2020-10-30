package net.turtle;

public class ExceptionResult implements IResult {
	
	private final String msg;
	
	public ExceptionResult(String msg) {
		this.msg = msg;
	}
	
	@Override
	public boolean isSuccessful() {
		return false;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
}
