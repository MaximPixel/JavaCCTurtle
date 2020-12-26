package net.turtle.command;

import net.turtle.ExceptionResult;
import net.turtle.IResult;
import net.turtle.UsageResult;

public class CheckCommand extends Command {

    public CheckCommand(String[] names) {
        super(names[0], names);
    }

    @Override
    public IResult execute(ICommandContext context) {
        String[] args = context.getArguments();
        if (args.length >= 1) {
            String blockName = args[0];
            int amount;

            if (args.length >= 2) {
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    return new ExceptionResult(e.getMessage());
                }
            } else {
                amount = 1;
            }

            if (context.getTurtle().checkBlocks(blockName, amount)) {
                return IResult.SKIP;
            } else {
                return new ExceptionResult(String.format("Turtle has no \"%s\" in the amount of %s", blockName, amount));
            }
        }
        return new UsageResult(this, "<block> or <block> <amount>");
    }
}
