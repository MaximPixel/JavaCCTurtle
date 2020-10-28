package net.turtle;

public interface ITurtle {
	
	void forward();
	
	void back();
	
	void up();
	
	void down();
	
	void turnLeft();
	
	void turnRight();
	
	void turnAround();
	
	void turnAt(EnumRot rot);
	
	void turnForMove(int x, int y);
	
	void moveAt(int x, int y);
	
	void select(int selectedSlot);
	
	void place(String blockName);
	
	void placeDown(String blockName);
	
	void placeUp(String blockName);
	
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
