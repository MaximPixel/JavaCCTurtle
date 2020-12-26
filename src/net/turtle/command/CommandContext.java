package net.turtle.command;

import net.turtle.turtle.ITurtle;

public final class CommandContext {

    private final ITurtle turtle;
    private final String[] arguments;

    public CommandContext(ITurtle turtle, String[] arguments) {
        this.turtle = turtle;
        this.arguments = arguments;
    }

    public ITurtle getTurtle() {
        return turtle;
    }

    public String[] getArguments() {
        return arguments;
    }
}
