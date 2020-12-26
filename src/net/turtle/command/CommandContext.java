package net.turtle.command;

import net.turtle.turtle.ITurtle;

public final class CommandContext implements ICommandContext {

    private final ITurtle turtle;
    private final String[] arguments;

    public CommandContext(ITurtle turtle, String[] arguments) {
        this.turtle = turtle;
        this.arguments = arguments;
    }

    @Override
    public ITurtle getTurtle() {
        return turtle;
    }

    @Override
    public String[] getArguments() {
        return arguments;
    }
}
