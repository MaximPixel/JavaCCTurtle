package net.turtle.turtle;

import net.turtle.IResult;

public final class TurtleActionResult implements IResult {
	
	private final boolean succ;
	
	public TurtleActionResult(boolean succ) {
		this.succ = succ;
	}
	
	@Override
	public boolean isSuccessful() {
		return succ;
	}

}
