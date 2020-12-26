package net.turtle.turtle;

public class MoveHelper implements IMoveHelper {

    private final ITurtle turtle;

    public MoveHelper(ITurtle turtle) {
        this.turtle = turtle;
    }

    @Override
    public ITurtle getTurtle() {
        return turtle;
    }
}
