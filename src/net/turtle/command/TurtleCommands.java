package net.turtle.command;

import java.util.HashMap;

import net.turtle.Constants;

public final class TurtleCommands {
	
	public static final BaseTurtleCommand FORWARD = new BaseTurtleCommand(context -> context.getTurtle().forward(), Constants.FORWARD_NAMES);
	public static final BaseTurtleCommand BACK = new BaseTurtleCommand(context -> context.getTurtle().back(), Constants.BACK_NAMES);
	public static final BaseTurtleCommand UP = new BaseTurtleCommand(context -> context.getTurtle().up(), Constants.UP_NAMES);
	public static final BaseTurtleCommand DOWN = new BaseTurtleCommand(context -> context.getTurtle().down(), Constants.DOWN_NAMES);
	
	public static final BaseTurtleCommand TURN_LEFT = new BaseTurtleCommand(context -> context.getTurtle().turnLeft(), Constants.TURN_LEFT_NAMES);
	public static final BaseTurtleCommand TURN_RIGHT = new BaseTurtleCommand(context -> context.getTurtle().turnRight(), Constants.TURN_RIGHT_NAMES);
	public static final BaseTurtleCommand TURN_AROUND = new BaseTurtleCommand(context -> context.getTurtle().turnAround(), Constants.TURN_AROUND_NAMES);
	
	public static final TurtleCommandTurnAt TURN_AT = new TurtleCommandTurnAt(Constants.TURN_AT_NAMES);
	
	private static TurtleCommands instance = new TurtleCommands();
	
	public static TurtleCommands getInstance() {
		return instance;
	}
	
	public final HashMap<String, Command> commandsByName;
	
	private TurtleCommands() {
		commandsByName = new HashMap();
	}
}
