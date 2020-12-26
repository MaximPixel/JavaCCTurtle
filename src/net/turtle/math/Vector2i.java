package net.turtle.math;

public class Vector2i {

    private final int x, y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2i add(int x, int y) {
        return new Vector2i(getX() + x, getY() + y);
    }

    public Vector2i offset(EnumRot rot) {
        return offset(rot, 1);
    }

    public Vector2i offset(EnumRot rot, int n) {
        return new Vector2i(getX() + rot.getTransformX() * n, getY() + rot.getTransformY() * n);
    }

    @Override
    public String toString() {
        return "Vec2i [" + x + " " + y + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
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
        Vector2i other = (Vector2i) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
