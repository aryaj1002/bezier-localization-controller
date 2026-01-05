package math;

public class Vector2 {
    public final double x;
    public final double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 o) { return new Vector2(x + o.x, y + o.y); }
    public Vector2 sub(Vector2 o) { return new Vector2(x - o.x, y - o.y); }
    public Vector2 mul(double k) { return new Vector2(x * k, y * k); }

    public double dot(Vector2 o) { return x * o.x + y * o.y; }
    public double norm() { return Math.hypot(x, y); }

    public Vector2 rotate(double radians) {
        double c = Math.cos(radians), s = Math.sin(radians);
        return new Vector2(c * x - s * y, s * x + c * y);
    }
}
