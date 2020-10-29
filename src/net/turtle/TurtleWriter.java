package net.turtle;

import java.util.ArrayList;

import net.turtle.command.TurtleCommands;

public class TurtleWriter extends Turtle {
	
	private ArrayList<String> commands = new ArrayList();
	
	public TurtleWriter() {
		super();
	}
	
	public TurtleWriter(BlockPos pos, EnumRot rot) {
		super(pos, rot);
	}
	
	@Override
	public TurtleActionResult forward() {
		addCommand(TurtleCommands.FORWARD_NAMES[0]);
		return super.forward();
	}
	
	@Override
	public TurtleActionResult back() {
		addCommand(TurtleCommands.BACK_NAMES[0]);
		return super.back();
	}
	
	@Override
	public TurtleActionResult up() {
		addCommand(TurtleCommands.UP_NAMES[0]);
		return super.up();
	}
	
	@Override
	public TurtleActionResult down() {
		addCommand(TurtleCommands.DOWN_NAMES[0]);
		return super.down();
	}
	
	@Override
	public TurtleActionResult turnLeft() {
		addCommand(TurtleCommands.TURN_LEFT_NAMES[0]);
		return super.turnLeft();
	}
	
	@Override
	public TurtleActionResult turnRight() {
		addCommand(TurtleCommands.TURN_RIGHT_NAMES[0]);
		return super.turnRight();
	}
	
	@Override
	public TurtleActionResult turnAround() {
		addCommand(TurtleCommands.TURN_AROUND_NAMES[0]);
		return super.turnAround();
	}
	
	@Override
	public TurtleActionResult select(int selectedSlot) {
		addCommand("select " + selectedSlot);
		return super.select(selectedSlot);
	}
	
	@Override
	public TurtleActionResult place(String blockName) {
		addCommand("place " + blockName);
		return super.place(blockName);
	}
	
	@Override
	public TurtleActionResult placeDown(String blockName) {
		addCommand("placed " + blockName);
		return super.placeDown(blockName);
	}
	
	@Override
	public TurtleActionResult placeUp(String blockName) {
		addCommand("placeu " + blockName);
		return super.placeUp(blockName);
	}
	
	public void addCommand(String command) {
		commands.add(command);
	}
	
	public ArrayList<String> getCommands() {
		return commands;
	}
}
