package net.turtle.command;

import net.turtle.IResult;

public interface ICommandExecute {

    IResult execute(ICommandContext context);
}
