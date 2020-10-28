package net.turtle;

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
	
	public static EnumRot getFromId(int id) {
		return getFromIdRaw(id & 3);
	}
	
	private static EnumRot getFromIdRaw(int id) {
		switch(id) {
			case 1: return RIGHT;
			case 2: return BACK;
			case 3: return LEFT;
			default: return FORWARD;
		}
	}
}
