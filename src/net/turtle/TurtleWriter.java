package net.turtle;

import java.util.ArrayList;

public class TurtleWriter extends Turtle {
	
	private ArrayList<String> commands = new ArrayList();
	
	public TurtleWriter() {
		super();
	}
	
	public TurtleWriter(BlockPos pos, EnumRot rot) {
		super(pos, rot);
	}
	
	@Override
	public void forward() {
		super.forward();
		addCommand("forward");
	}
	
	@Override
	public void back() {
		super.back();
		addCommand("back");
	}
	
	@Override
	public void up() {
		super.up();
		addCommand("up");
	}
	
	@Override
	public void down() {
		super.down();
		addCommand("down");
	}
	
	@Override
	public void turnLeft() {
		super.turnLeft();
		addCommand("left");
	}
	
	@Override
	public void turnRight() {
		super.turnRight();
		addCommand("right");
	}
	
	@Override
	public void turnAround() {
		super.turnAround();
		addCommand("right");
		addCommand("right");
	}
	
	@Override
	public void select(int selectedSlot) {
		super.select(selectedSlot);
		addCommand("select " + selectedSlot);
	}
	
	@Override
	public void place(String blockName) {
		super.place(blockName);
		addCommand("place " + blockName);
	}
	
	@Override
	public void placeDown(String blockName) {
		super.placeDown(blockName);
		addCommand("placed " + blockName);
	}
	
	@Override
	public void placeUp(String blockName) {
		super.placeUp(blockName);
		addCommand("placeu " + blockName);
	}
	
	public void addCommand(String command) {
		commands.add(command);
	}
	
	public ArrayList<String> getCommands() {
		return commands;
	}
}
