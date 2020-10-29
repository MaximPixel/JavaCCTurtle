package net.turtle.command;

import net.turtle.IResult;

public class CommandResult implements IResult {
	
	private final boolean succ;
	private final String msg;
	
	private CommandResult(boolean succ, String msg) {
		this.succ = succ;
		this.msg = msg;
	}
	
	private CommandResult(boolean succ) {
		this(succ, "");
	}
	
	public static CommandResult error(String msg) {
		return message(false, msg);
	}
	
	public static CommandResult message(boolean succ, String msg) {
		return new CommandResult(succ, msg);
	}
	
	public static CommandResult successful() {
		return new CommandResult(true);
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
