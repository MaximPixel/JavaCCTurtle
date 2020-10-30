package net.turtle.turtle;

import java.util.Arrays;

import net.turtle.ExceptionResult;
import net.turtle.BlockPos;
import net.turtle.EnumRot;
import net.turtle.IResult;
import net.turtle.command.Command;
import net.turtle.command.CommandContext;
import net.turtle.command.Commands;

public interface ITurtle extends IDefine, ICheck {
	
	TurtleActionResult forward();
	
	TurtleActionResult back();
	
	TurtleActionResult up();
	
	TurtleActionResult down();
	
	TurtleActionResult turnLeft();
	
	TurtleActionResult turnRight();
	
	TurtleActionResult turnAround();
	
	TurtleActionResult turnAt(EnumRot rot);
	
	TurtleActionResult turnForMove(int x, int y);
	
	TurtleActionResult moveAt(int x, int y);
	
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
				System.out.println(result.getMessage());
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
	
	@Override
	default void define(String variable, String value) {
		getDefineHelper().define(variable, value);
	}

	@Override
	default String getDefinedValue(String variable) {
		return getDefineHelper().getDefinedValue(variable);
	}

	@Override
	default boolean hasDefinedValue(String variable) {
		return getDefineHelper().hasDefinedValue(variable);
	}
	
	@Override
	default boolean hasBlocks(String block, int amount) {
		return true;
	}
}
