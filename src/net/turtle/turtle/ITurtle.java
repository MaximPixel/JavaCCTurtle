package net.turtle.turtle;

import java.util.Arrays;

import net.turtle.BaseResult;
import net.turtle.BlockPos;
import net.turtle.EnumRot;
import net.turtle.IResult;
import net.turtle.command.Command;
import net.turtle.command.CommandContext;
import net.turtle.command.TurtleCommands;

public interface ITurtle extends IDefine {
	
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
			System.out.println(cmd + " " + result.getMessage());
			
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
			
			Command baseCommand = TurtleCommands.getInstance().getCommandByName(words[0]);
			
			if (baseCommand == null) {
				return new BaseResult(false, "Unknown command " + words[0]);
			}
			
			CommandContext context = new CommandContext(this, args);
			
			return baseCommand.execute(context);
		} else {
			return IResult.SKIP;
		}
	}
	
	DefineHelper getDefineHelper();
	
	@Override
	default void define(String variable, String value) {
		getDefineHelper().define(variable, value);
	}

	@Override
	default String getValue(String variable) {
		return getDefineHelper().getValue(variable);
	}
}
