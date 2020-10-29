package net.turtle.command;

import net.turtle.IResult;

public class BaseTurtleCommand extends Command {

	private final ICommandExecute executor;
	
	public BaseTurtleCommand(ICommandExecute executor, String... names) {
		super(names[0], names);
		this.executor = executor;
	}

	@Override
	public IResult execute(CommandContext context) {
		return executor.execute(context);
	}
}
