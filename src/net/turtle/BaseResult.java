package net.turtle;

public class BaseResult implements IResult {
	
	private final boolean succ;
	private final String msg;
	
	public BaseResult(boolean succ, String msg) {
		this.succ = succ;
		this.msg = msg;
	}
	
	@Override
	public boolean isSuccessful() {
		return succ;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
}
