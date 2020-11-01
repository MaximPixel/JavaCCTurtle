package net.turtle.turtle;

import java.util.ArrayList;

import net.turtle.Constants;
import net.turtle.math.BlockPos;
import net.turtle.math.EnumRot;

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
		addCommand(Constants.FORWARD_NAMES[0]);
		return super.forward();
	}
	
	@Override
	public TurtleActionResult back() {
		addCommand(Constants.BACK_NAMES[0]);
		return super.back();
	}
	
	@Override
	public TurtleActionResult up() {
		addCommand(Constants.UP_NAMES[0]);
		return super.up();
	}
	
	@Override
	public TurtleActionResult down() {
		addCommand(Constants.DOWN_NAMES[0]);
		return super.down();
	}
	
	@Override
	public TurtleActionResult turnLeft() {
		addCommand(Constants.TURN_LEFT_NAMES[0]);
		return super.turnLeft();
	}
	
	@Override
	public TurtleActionResult turnRight() {
		addCommand(Constants.TURN_RIGHT_NAMES[0]);
		return super.turnRight();
	}
	
	@Override
	public TurtleActionResult turnAround() {
		addCommand(Constants.TURN_AROUND_NAMES[0]);
		return super.turnAround();
	}
	
	@Override
	public TurtleActionResult select(int selectedSlot) {
		addCommand("select " + selectedSlot);
		return super.select(selectedSlot);
	}
	
	@Override
	public TurtleActionResult place(String blockName) {
		addCommand(Constants.PLACE[0] + " " + blockName);
		return super.place(blockName);
	}
	
	@Override
	public TurtleActionResult placeDown(String blockName) {
		addCommand(Constants.PLACED[0] + " " + blockName);
		return super.placeDown(blockName);
	}
	
	@Override
	public TurtleActionResult placeUp(String blockName) {
		addCommand(Constants.PLACEU[0] + " " + blockName);
		return super.placeUp(blockName);
	}
	
	@Override
	public void define(String variable, String value) {
		addCommand(Constants.DEFINE[0] + " " + variable + " " + value);
		super.define(variable, value);
	}
	
	@Override
	public boolean checkBlocks(String block, int amount) {
		addCommand(Constants.CHECK[0] + " " + block + " " + amount);
		return super.checkBlocks(block, amount);
	}
	
	public void addCommand(String command) {
		commands.add(command);
	}
	
	public ArrayList<String> getCommands() {
		return commands;
	}
}
