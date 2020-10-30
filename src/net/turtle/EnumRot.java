package net.turtle;

import java.util.Arrays;

public enum EnumRot {
	FORWARD	(0, 1, 0, 3, 1, 2),
	RIGHT	(1, 0, 1, 0, 2, 3),
	BACK	(2, -1, 0, 1, 3, 0),
	LEFT	(3, 0, -1, 2, 0, 1);
	
	private final int id, tx, ty, left, right, opposite;
	
	private EnumRot(int id, int tx, int ty, int left, int right, int opposite) {
		this.id = id;
		this.tx = tx;
		this.ty = ty;
		this.left = left;
		this.right = right;
		this.opposite = opposite;
	}
	
	public int getId() { return id; }
	public int getTransformX() { return tx; }
	public int getTransformY() { return ty; }
	public EnumRot getLeft() { return getFromIdRaw(left); }
	public EnumRot getRight() { return getFromIdRaw(right); }
	public EnumRot getOpposite() { return getFromIdRaw(opposite); }
	
	public String[] getNames() {
		switch (this) {
		case FORWARD: return Constants.FORWARD_NAMES;
		case RIGHT: return Constants.TURN_RIGHT_NAMES;
		case BACK: return Constants.BACK_NAMES;
		case LEFT: return Constants.TURN_LEFT_NAMES;
		}
		throw new RuntimeException("What?");
	}
	
	public String getBaseName() {
		return getNames()[0];
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
			if (Arrays.stream(Constants.FORWARD_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.FORWARD;
			} else if (Arrays.stream(Constants.TURN_RIGHT_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.RIGHT;
			} else if (Arrays.stream(Constants.BACK_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.BACK;
			} else if (Arrays.stream(Constants.TURN_LEFT_NAMES).anyMatch(s -> s.equalsIgnoreCase(str))) {
				return EnumRot.LEFT;
			}
			throw new RuntimeException(String.format("Parse error with \"%s\"", str));
		}
	}
}
