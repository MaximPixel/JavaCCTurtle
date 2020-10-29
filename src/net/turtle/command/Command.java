package net.turtle.command;

import net.turtle.IResult;

public abstract class Command {
	
	private final String baseName;
	private final String[] names;
	
	public Command(String baseName, String[] names) {
		this.baseName = baseName;
		this.names = names;
		
		for (String name : names) {
			if (TurtleCommands.COMMANDS_BY_NAME.containsKey(name)) {
				throw new RuntimeException("Name collision!");
			} else {
				TurtleCommands.COMMANDS_BY_NAME.put(name, this);
			}
		}
	}
	
	public abstract IResult execute(CommandContext context);
	
	public final String[] getNames() {
		return names;
	}
	
	public final String getBaseName() {
		return baseName;
	}
}
