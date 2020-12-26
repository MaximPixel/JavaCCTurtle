package net.turtle.command;

import net.turtle.IResult;
import net.turtle.UsageResult;

public class DefineCommand extends Command {

    public DefineCommand(String[] names) {
        super(names[0], names);
    }

    @Override
    public IResult execute(CommandContext context) {
        String[] args = context.getArguments();

        if (args.length >= 2) {
            context.getTurtle().define(args[0], args[1]);
            return IResult.FULL_SUCCESSFUL;
        } else {
            return new UsageResult(this, "<variable> <value>");
        }
    }
}
