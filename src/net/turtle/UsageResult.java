package net.turtle;

import net.turtle.command.Command;

public class UsageResult implements IResult {

    private final String msg;

    public UsageResult(Command cmd, String arguments) {
        msg = "Usage: " + cmd.getBaseName() + " " + arguments;
    }

    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
