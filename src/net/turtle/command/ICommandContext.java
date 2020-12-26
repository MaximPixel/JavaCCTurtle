package net.turtle.command;

import net.turtle.turtle.ITurtle;
import net.turtle.turtle.TurtleActionResult;

public interface ICommandContext {
    ITurtle getTurtle();

    String[] getArguments();

    default TurtleActionResult moveForward() {
        return getTurtle().forward();
    }

    default TurtleActionResult moveBackward() {
        return getTurtle().back();
    }

    default TurtleActionResult turnRight() {
        return getTurtle().turnRight();
    }

    default TurtleActionResult turnLeft() {
        return getTurtle().turnLeft();
    }

    default TurtleActionResult moveUp() {
        return getTurtle().up();
    }

    default TurtleActionResult moveDown() {
        return getTurtle().down();
    }
}
