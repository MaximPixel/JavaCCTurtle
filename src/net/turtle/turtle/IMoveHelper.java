package net.turtle.turtle;

import net.turtle.IResult;
import net.turtle.math.EnumRot;

public interface IMoveHelper {
	
	ITurtle getTurtle();
	
	default IResult turnAt(EnumRot rot) {
		ITurtle turtle = getTurtle();
		
		if (turtle.getRot().getLeft() == rot) {
			turtle.turnLeft();
		} else if (turtle.getRot().getRight() == rot) {
			turtle.turnRight();
		} else if (turtle.getRot().getOpposite() == rot) {
			turtle.turnAround();
		}
		
		return new TurtleActionResult(true);
	}
	
	default TurtleActionResult turnForMove(int x, int z) {
		ITurtle turtle = getTurtle();
		
		if (x != turtle.getPos().getX() || z != turtle.getPos().getZ()) {
			if (x > turtle.getPos().getX()) {
				turnAt(EnumRot.FORWARD);
			} else if (z > turtle.getPos().getZ()) {
				turnAt(EnumRot.RIGHT);
			} else if (x < turtle.getPos().getX()) {
				turnAt(EnumRot.BACK);
			} else if (z < turtle.getPos().getZ()) {
				turnAt(EnumRot.LEFT);
			}
		}
		
		return new TurtleActionResult(true);
	}
	
	default TurtleActionResult moveHorizontalAt(int x, int z) {
		ITurtle turtle = getTurtle();
		
		while (x != turtle.getPos().getX() || z != turtle.getPos().getZ()) {
			turnForMove(x, z);
			TurtleActionResult result = turtle.forward();
			if (!result.isSuccessful()) {
				return result;
			}
		}
		
		return new TurtleActionResult(true);
	}
	
	default TurtleActionResult moveVerticalAt(int y) {
		ITurtle turtle = getTurtle();
		
		while (y != turtle.getPos().getY()) {
			if (y > turtle.getPos().getY()) {
				turtle.up();
			} else {
				turtle.down();
			}
		}
		
		return new TurtleActionResult(true);
	}
}
