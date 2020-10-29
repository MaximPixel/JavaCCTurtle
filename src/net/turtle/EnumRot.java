package net.turtle;

import java.util.Arrays;
import java.util.function.Supplier;

import net.turtle.command.TurtleCommands;

public enum EnumRot {
	FORWARD	(0, 1, 0, 3, 1, 2, () -> TurtleCommands.FORWARD_NAMES),
	RIGHT	(1, 0, 1, 0, 2, 3, () -> TurtleCommands.TURN_RIGHT_NAMES),
	BACK	(2, -1, 0, 1, 3, 0, () -> TurtleCommands.BACK_NAMES),
	LEFT	(3, 0, -1, 2, 0, 1, () -> TurtleCommands.TURN_LEFT_NAMES);
	
	private final int id, tx, ty, left, right, opposite;
	private final String[] names;
	private final String baseName;
	
	private EnumRot(int id, int tx, int ty, int left, int right, int opposite, Supplier<String[]> names) {
		this.id = id;
		this.tx = tx;
		this.ty = ty;
		this.left = left;
		this.right = right;
		this.opposite = opposite;
		
		this.names = names.get();
		baseName = this.names[0];
	}
	
	public int getId() { return id; }
	public int getTransformX() { return tx; }
	public int getTransformY() { return ty; }
	public EnumRot getLeft() { return getFromIdRaw(left); }
	public EnumRot getRight() { return getFromIdRaw(right); }
	public EnumRot getOpposite() { return getFromIdRaw(opposite); }
	
	public String[] getNames() {
		return names;
	}
	
	public String getBaseName() {
		return baseName;
	}
	
	public static EnumRot getFromId(int id) {
		return getFromIdRaw(id & 3);
	}
	
	private static EnumRot getFromIdRaw(int id) {
		switch (id) {
			case 1: return RIGHT;
			case 2: return BACK;
			case 3: return LEFT;
			default: return FORWARD;
		}
	}
	
	public static EnumRot parseFromString(String str) {
		try {
			int id = Integer.parseInt(str);
			return getFromId(id);
		} catch (NumberFormatException e) {
			if (Arrays.stream(TurtleCommands.FORWARD_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.FORWARD;
			} else if (Arrays.stream(TurtleCommands.TURN_RIGHT_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.RIGHT;
			} else if (Arrays.stream(TurtleCommands.BACK_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.BACK;
			} else if (Arrays.stream(TurtleCommands.TURN_LEFT_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.LEFT;
			}
			throw new RuntimeException(String.format("Parse error with \"%s\"", str));
		}
	}
}
