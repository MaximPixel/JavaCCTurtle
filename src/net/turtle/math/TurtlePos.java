package net.turtle.math;

import java.util.Objects;
import java.util.function.Function;

public class TurtlePos {

    public static final TurtlePos ZERO = new TurtlePos(BlockPos.ZERO, EnumRot.FORWARD);

    private final BlockPos pos;
    private final EnumRot rot;

    public TurtlePos(BlockPos pos, EnumRot rot) {
        this.pos = Objects.requireNonNull(pos);
        this.rot = Objects.requireNonNull(rot);
    }

    public TurtlePos acceptPos(Function<BlockPos, BlockPos> posFunction) {
        return new TurtlePos(posFunction.apply(getPos()), getRot());
    }

    public TurtlePos acceptRot(Function<EnumRot, EnumRot> rotFunction) {
        return new TurtlePos(getPos(), rotFunction.apply(getRot()));
    }

    public TurtlePos forward() {
        return acceptPos(pos -> pos.offset(getRot()));
    }

    public TurtlePos backward() {
        return acceptPos(pos -> pos.offset(getRot().getOpposite()));
    }

    public TurtlePos turnRight() {
        return acceptRot(EnumRot::getRight);
    }

    public TurtlePos turnLeft() {
        return acceptRot(EnumRot::getLeft);
    }

    public TurtlePos moveUp() {
        return acceptPos(pos -> pos.add(0, 1, 0));
    }

    public TurtlePos moveDown() {
        return acceptPos(pos -> pos.add(0, -1, 0));
    }

    public BlockPos getPos() {
        return pos;
    }

    public EnumRot getRot() {
        return rot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurtlePos turtlePos = (TurtlePos) o;
        return pos.equals(turtlePos.pos) && rot == turtlePos.rot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, rot);
    }

    @Override
    public String toString() {
        return "TurtlePos{" +
                "pos=" + pos +
                ", rot=" + rot +
                '}';
    }
}
