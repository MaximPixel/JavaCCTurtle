package net.turtle.command;

import net.turtle.EnumRot;
import net.turtle.IResult;

public class TurnAtCommand extends Command {

	public TurnAtCommand(String... names) {
		super(names[0], names);
	}

	@Override
	public IResult execute(CommandContext context) {
		String[] args = context.getArguments();
		try {
			if (args.length > 0) {
				EnumRot rot = EnumRot.parseFromString(args[0]);
				return context.getTurtle().turnAt(rot);
			}
		} catch (Exception e) {}
		
		return CommandResult.message(false, String.format("usage: %s <0-3 or forward, back...>", getBaseName()));
	}
}
