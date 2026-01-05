package math;

public class Pose2d {
    public final double x;
    public final double y;
    public final double heading; // radians

    public Pose2d(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = wrap(heading);
    }

    public Pose2d plus(double dx, double dy, double dHeading) {
        return new Pose2d(x + dx, y + dy, heading + dHeading);
    }

    public static double wrap(double a) {
        while (a <= -Math.PI) a += 2 * Math.PI;
        while (a > Math.PI) a -= 2 * Math.PI;
        return a;
    }
}
