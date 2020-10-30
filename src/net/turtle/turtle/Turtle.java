package net.turtle.turtle;

import net.turtle.BlockPos;
import net.turtle.EnumRot;
import net.turtle.IBlocksListener;

public class Turtle implements ITurtle {
	
	private BlockPos pos;
	private EnumRot rot;
	private int selectedSlot;
	
	private IBlocksListener blocksListener;
	
	private DefineHelper defineHelper = new DefineHelper();
	
	public Turtle() {
		this(BlockPos.ZERO, EnumRot.FORWARD);
	}
	
	public Turtle(BlockPos pos, EnumRot rot) {
		this.pos = pos;
		this.rot = rot;
	}
	
	@Override
	public TurtleActionResult forward() {
		pos = pos.offset(rot);
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult back() {
		pos = pos.offset(rot, -1);
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult up() {
		pos = pos.add(0, 1, 0);
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult down() {
		pos = pos.add(0, -1, 0);
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult turnLeft() {
		rot = rot.getLeft();
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult turnRight() {
		rot = rot.getRight();
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult turnAround() {
		rot = rot.getOpposite();
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult turnAt(EnumRot rot) {
		if (getRot().getLeft() == rot) {
			turnLeft();
		} else if (getRot().getRight() == rot) {
			turnRight();
		} else if (getRot().getOpposite() == rot) {
			turnAround();
		}
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult turnForMove(int x, int z) {
		if (x != getPos().getX() || z != getPos().getZ()) {
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
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult moveAt(int x, int z) {
		while (x != getPos().getX() || z != getPos().getZ()) {
			turnForMove(x, z);
			forward();
		}
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult select(int selectedSlot) {
		this.selectedSlot = selectedSlot;
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult place(String blockName) {
		if (blocksListener != null) {
			blocksListener.addBlock(pos.offset(rot));
		}
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult placeDown(String blockName) {
		if (blocksListener != null) {
			blocksListener.addBlock(pos.add(0, -1, 0));
		}
		return new TurtleActionResult(true);
	}

	@Override
	public TurtleActionResult placeUp(String blockName) {
		if (blocksListener != null) {
			blocksListener.addBlock(pos.add(0, 1, 0));
		}
		return new TurtleActionResult(true);
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
	
	public void setBlocksListener(IBlocksListener blocksListener) {
		this.blocksListener = blocksListener;
	}
	
	@Override
	public String toString() {
		return "Turtle [pos=" + pos + ", rot=" + rot + "]";
	}

	@Override
	public DefineHelper getDefineHelper() {
		return defineHelper;
	}
}
