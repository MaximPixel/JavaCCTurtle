package net.turtle.turtle;

import net.turtle.ExceptionResult;
import net.turtle.IResult;
import net.turtle.command.Command;
import net.turtle.command.CommandContext;
import net.turtle.command.Commands;
import net.turtle.math.BlockPos;
import net.turtle.math.EnumRot;

import java.util.Arrays;

public interface ITurtle extends IDefine, ICheck {

    TurtleActionResult forward();

    TurtleActionResult back();

    TurtleActionResult up();

    TurtleActionResult down();

    TurtleActionResult turnLeft();

    TurtleActionResult turnRight();

    TurtleActionResult turnAround();

    TurtleActionResult select(int selectedSlot);

    TurtleActionResult place(String blockName);

    TurtleActionResult placeDown(String blockName);

    TurtleActionResult placeUp(String blockName);

    BlockPos getPos();

    EnumRot getRot();

    int getSelectedSlot();

    default IResult executeCommands(Iterable<String> commands) {
        for (String cmd : commands) {
            IResult result = executeCommand(cmd);

            if (!result.isSuccessful()) {
                return result;
            }
        }
        return IResult.FULL_SUCCESSFUL;
    }

    default IResult executeCommand(String cmd) {
        String[] words = cmd.split(" ");

        if (words.length >= 1) {
            String[] args = Arrays.copyOfRange(words, 1, words.length);

            Command baseCommand = Commands.getInstance().getCommandByName(words[0]);

            if (baseCommand == null) {
                return new ExceptionResult("Unknown command " + words[0]);
            }

            CommandContext context = new CommandContext(this, args);

            return baseCommand.execute(context);
        } else {
            return IResult.SKIP;
        }
    }

    IDefine getDefineHelper();

    IMoveHelper getMoveHelper();

    @Override
    default void define(String variable, String value) {
        getDefineHelper().define(variable, value);
    }

    @Override
    default String getDefine(String variable) {
        return getDefineHelper().getDefine(variable);
    }

    @Override
    default boolean hasDefine(String variable) {
        return getDefineHelper().hasDefine(variable);
    }

    @Override
    default String getDefineByValue(String value) {
        return getDefineHelper().getDefineByValue(value);
    }

    @Override
    default boolean hasDefineByValue(String value) {
        return getDefineHelper().hasDefineByValue(value);
    }

    @Override
    default boolean checkBlocks(String block, int amount) {
        return true;
    }
}
