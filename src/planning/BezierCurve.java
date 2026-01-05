package planning;

import math.Vector2;

public class BezierCurve {
    public final Vector2 p0, p1, p2, p3;

    public BezierCurve(Vector2 p0, Vector2 p1, Vector2 p2, Vector2 p3) {
        this.p0 = p0; this.p1 = p1; this.p2 = p2; this.p3 = p3;
    }

    // Cubic Bezier point at parameter t in [0,1]
    public Vector2 at(double t) {
        double u = 1 - t;
        double b0 = u*u*u;
        double b1 = 3*u*u*t;
        double b2 = 3*u*t*t;
        double b3 = t*t*t;
        return new Vector2(
                b0*p0.x + b1*p1.x + b2*p2.x + b3*p3.x,
                b0*p0.y + b1*p1.y + b2*p2.y + b3*p3.y
        );
    }

    // Tangent / derivative for heading or solver steps
    public Vector2 derivative(double t) {
        double u = 1 - t;
        Vector2 a = new Vector2(p1.x - p0.x, p1.y - p0.y).mul(u*u);
        Vector2 b = new Vector2(p2.x - p1.x, p2.y - p1.y).mul(2*u*t);
        Vector2 c = new Vector2(p3.x - p2.x, p3.y - p2.y).mul(t*t);
        return a.add(b).add(c).mul(3.0);
    }
}
