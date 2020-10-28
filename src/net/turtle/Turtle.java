package net.turtle;

public class Turtle implements ITurtle {
	
	private BlockPos pos;
	private EnumRot rot;
	private int selectedSlot;
	
	private IBlocksListener blocksListener;
	
	public Turtle() {
		this(BlockPos.ZERO, EnumRot.FORWARD);
	}
	
	public Turtle(BlockPos pos, EnumRot rot) {
		this.pos = pos;
		this.rot = rot;
	}
	
	@Override
	public void forward() {
		pos = pos.offset(rot);
	}

	@Override
	public void back() {
		pos = pos.offset(rot, -1);
	}

	@Override
	public void up() {
		pos = pos.add(0, 1, 0);
	}

	@Override
	public void down() {
		pos = pos.add(0, -1, 0);
	}

	@Override
	public void turnLeft() {
		rot = rot.getLeft();
	}

	@Override
	public void turnRight() {
		rot = rot.getRight();
	}

	@Override
	public void turnAround() {
		rot = rot.getOpposite();
	}

	@Override
	public void turnAt(EnumRot rot) {
		if (getRot().getLeft() == rot) {
			turnLeft();
		} else if (getRot().getRight() == rot) {
			turnRight();
		} else if (getRot().getOpposite() == rot) {
			turnAround();
		}
	}

	@Override
	public void turnForMove(int x, int z) {
		if (x == getPos().getX() && z == getPos().getZ()) {
			return;
		}
		if (x > getPos().getX()) {
			turnAt(EnumRot.FORWARD);
		} else if (z > getPos().getZ()) {
			turnAt(EnumRot.RIGHT);
		} else if (x < getPos().getX()) {
			turnAt(EnumRot.BACK);
		} else if (z < getPos().getZ()) {
			turnAt(EnumRot.LEFT);
		}
	}

	@Override
	public void moveAt(int x, int z) {
		while (x != getPos().getX() || z != getPos().getZ()) {
			turnForMove(x, z);
			forward();
		}
	}

	@Override
	public void select(int selectedSlot) {
		this.selectedSlot = selectedSlot;
	}

	@Override
	public void place(String blockName) {
		if (blocksListener != null) {
			blocksListener.addBlock(pos.offset(rot));
		}
	}

	@Override
	public void placeDown(String blockName) {
		if (blocksListener != null) {
			blocksListener.addBlock(pos.add(0, -1, 0));
		}
	}

	@Override
	public void placeUp(String blockName) {
		if (blocksListener != null) {
			blocksListener.addBlock(pos.add(0, 1, 0));
		}
	}

	@Override
	public BlockPos getPos() {
		return pos;
	}

	@Override
	public EnumRot getRot() {
		return rot;
	}

	@Override
	public int getSelectedSlot() {
		return selectedSlot;
	}
	
	@Override
	public void executeCommand(String cmd) {
		String[] args = cmd.split(" ");
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("forward")) {
				forward();
			} else if (args[0].equalsIgnoreCase("back")) {
				back();
			} else if (args[0].equalsIgnoreCase("down")) {
				down();
			} else if (args[0].equalsIgnoreCase("up")) {
				up();
			} else if (args[0].equalsIgnoreCase("left")) {
				turnLeft();
			} else if (args[0].equalsIgnoreCase("right")) {
				turnRight();
			}
			
			if (args.length >= 2) {
				if (args[0].equalsIgnoreCase("place")) {
					place(args[1]);
				} else if (args[0].equalsIgnoreCase("placed")) {
					placeDown(args[1]);
				} else if (args[0].equalsIgnoreCase("placeu")) {
					placeUp(args[1]);
				}
			}
		}
	}
	
	public void setBlocksListener(IBlocksListener blocksListener) {
		this.blocksListener = blocksListener;
	}
	
	@Override
	public String toString() {
		return "Turtle [pos=" + pos + ", rot=" + rot + "]";
	}
}
