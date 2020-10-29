package net.turtle;

public interface ITurtle {
	
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
	
	default void executeCommands(Iterable<String> commands) {
		for (String cmd : commands) {
			executeCommand(cmd);
		}
	}
	
	void executeCommand(String cmd);
}
