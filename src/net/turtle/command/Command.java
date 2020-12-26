package net.turtle.command;

import net.turtle.IResult;
import net.turtle.math.TurtlePos;
import net.turtle.turtle.Turtle;

import java.util.function.Function;

public abstract class Command {

    public static final MoveCommand MOVE_FORWARD = new MoveCommand("forward", ICommandContext::moveForward, TurtlePos::forward),
            MOVE_BACKWARD = new MoveCommand("back", ICommandContext::moveBackward, TurtlePos::backward),
            TURN_RIGHT = new MoveCommand("right", ICommandContext::turnRight, TurtlePos::turnRight),
            TURN_LEFT = new MoveCommand("left", ICommandContext::turnLeft, TurtlePos::turnLeft),
            MOVE_UP = new MoveCommand("up", ICommandContext::moveUp, TurtlePos::moveUp),
            MOVE_DOWN = new MoveCommand("down", ICommandContext::moveDown, TurtlePos::moveDown);

    public static final MoveCommand[] MOVE_COMMANDS = new MoveCommand[] {
            MOVE_FORWARD, MOVE_BACKWARD, TURN_RIGHT, TURN_LEFT, MOVE_UP, MOVE_DOWN
    };

    private final String baseName;
    private final String[] names;

    public Command(String baseName) {
        this(baseName, new String[0]);
    }

    public Command(String baseName, String[] names) {
        this.baseName = baseName;
        this.names = names;

        for (String name : names) {
            Commands.getInstance().putCommand(name, this);
        }
    }

    public abstract IResult execute(ICommandContext context);

    public final String[] getNames() {
        return names;
    }

    public final String getBaseName() {
        return baseName;
    }

    public static class SimpleCommand extends Command {
        private final Function<ICommandContext, IResult> func;

        public SimpleCommand(String baseName, Function<ICommandContext, IResult> func) {
            super(baseName);
            this.func = func;
        }

        @Override
        public IResult execute(ICommandContext context) {
            return func.apply(context);
        }
    }

    public static class MoveCommand extends SimpleCommand {
        private final Function<TurtlePos, TurtlePos> posFunction;

        public MoveCommand(String baseName, Function<ICommandContext, IResult> func, Function<TurtlePos, TurtlePos> posFunction) {
            super(baseName, func);
            this.posFunction = posFunction;
        }

        public TurtlePos acceptFunction(TurtlePos pos) {
            return posFunction.apply(pos);
        }
    }
}
