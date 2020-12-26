package net.turtle.math;

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

    public BlockPos add(BlockPos pos) {
        return new BlockPos(getX() + pos.getX(), getY() + pos.getY(), getZ() + pos.getZ());
    }

    public BlockPos offset(EnumRot rot) {
        return offset(rot, 1);
    }

    public BlockPos offset(EnumRot rot, int n) {
        return new BlockPos(getX() + rot.getTransformX() * n, getY(), getZ() + rot.getTransformY() * n);
    }

    public int getDistanceSq(BlockPos pos) {
        int xx = pos.getX() - getX();
        int yy = pos.getY() - getY();
        int zz = pos.getZ() - getZ();
        return xx * xx + yy * yy + zz * zz;
    }

    public double getDistance(BlockPos pos) {
        return Math.sqrt(getDistanceSq(pos));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BlockPos other = (BlockPos) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (z != other.z)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BlockPos [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
}
