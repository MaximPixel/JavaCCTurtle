package net.turtle.command;

import net.turtle.IResult;

public class BaseCommand extends Command {

    private final ICommandExecute executor;

    public BaseCommand(ICommandExecute executor, String... names) {
        super(names[0], names);
        this.executor = executor;
    }

    @Override
    public IResult execute(CommandContext context) {
        return executor.execute(context);
    }
}
