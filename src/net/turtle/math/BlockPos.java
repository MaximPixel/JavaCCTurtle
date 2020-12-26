package net.turtle.math;

import java.util.Objects;

public class BlockPos {

    public static final BlockPos ZERO = new BlockPos(0, 0, 0);

    private final int x, y, z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public BlockPos add(int x, int y, int z) {
        return new BlockPos(getX() + x, getY() + y, getZ() + z);
    }

    public BlockPos offset(EnumRot rot) {
        return add(rot.getTransformX(), 0, rot.getTransformY());
    }

    public BlockPos offset(EnumRot rot, int n) {
        return add(rot.getTransformX() * n, 0, rot.getTransformY() * n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockPos blockPos = (BlockPos) o;
        return x == blockPos.x && y == blockPos.y && z == blockPos.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "BlockPos{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
