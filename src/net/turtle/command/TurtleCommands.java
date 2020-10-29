package net.turtle.command;

import java.util.HashMap;

public final class TurtleCommands {
	
	public static final String[] FORWARD_NAMES = new String[] {"forward", "frwd", "forw", "f"};
	public static final String[] BACK_NAMES = new String[] {"back", "bck", "b"};
	public static final String[] UP_NAMES = new String[] {"up", "u"};
	public static final String[] DOWN_NAMES = new String[] {"down", "dwn", "d"};
	public static final String[] TURN_LEFT_NAMES = new String[] {"turnLeft", "left", "lft", "l"};
	public static final String[] TURN_RIGHT_NAMES = new String[] {"turnRight", "right", "rght", "rgh", "r"};
	public static final String[] TURN_AROUND_NAMES = new String[] {"turnAround", "around", "arnd", "a"};
	
	public static final String[] TURN_AT_NAMES = new String[] {"turnAt", "turn", "trn", "tr", "t"};
	
	public static final BaseTurtleCommand FORWARD = new BaseTurtleCommand(context -> context.getTurtle().forward(), FORWARD_NAMES);
	public static final BaseTurtleCommand BACK = new BaseTurtleCommand(context -> context.getTurtle().back(), BACK_NAMES);
	public static final BaseTurtleCommand UP = new BaseTurtleCommand(context -> context.getTurtle().up(), UP_NAMES);
	public static final BaseTurtleCommand DOWN = new BaseTurtleCommand(context -> context.getTurtle().down(), DOWN_NAMES);
	
	public static final BaseTurtleCommand TURN_LEFT = new BaseTurtleCommand(context -> context.getTurtle().turnLeft(), TURN_LEFT_NAMES);
	public static final BaseTurtleCommand TURN_RIGHT = new BaseTurtleCommand(context -> context.getTurtle().turnRight(), TURN_RIGHT_NAMES);
	public static final BaseTurtleCommand TURN_AROUND = new BaseTurtleCommand(context -> context.getTurtle().turnAround(), TURN_AROUND_NAMES);
	
	public static final TurtleCommandTurnAt TURN_AT = new TurtleCommandTurnAt(TURN_AT_NAMES);
	
	public static final HashMap<String, Command> COMMANDS_BY_NAME = new HashMap();
}
