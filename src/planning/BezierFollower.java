package planning;

import math.Pose2d;
import math.Vector2;

public class BezierFollower {

    public static class Command {
        public final double vx;    // field frame
        public final double vy;    // field frame
        public final double omega; // rad/s conceptually

        public Command(double vx, double vy, double omega) {
            this.vx = vx; this.vy = vy; this.omega = omega;
        }
    }

    // Lookahead follower (simple, SDK-free):
    // - searches forward along the curve near a t hint
    // - chooses a point whose distance is near lookaheadDist
    // - drives toward it while aligning to curve tangent
    public static Command follow(BezierCurve curve,
                                 Pose2d pose,
                                 double tHint,
                                 double lookaheadDist,
                                 double kPos,
                                 double kHead) {

        double bestT = tHint;
        double bestErr = Double.POSITIVE_INFINITY;

        for (int i = 0; i <= 40; i++) {
            double t = clamp(tHint + i * 0.01, 0, 1);
            Vector2 pt = curve.at(t);
            double d = Math.hypot(pt.x - pose.x, pt.y - pose.y);
            double err = Math.abs(d - lookaheadDist);
            if (err < bestErr) {
                bestErr = err;
                bestT = t;
            }
        }

        Vector2 target = curve.at(bestT);
        Vector2 toTarget = new Vector2(target.x - pose.x, target.y - pose.y);

        Vector2 tan = curve.derivative(bestT);
        double desiredHeading = Math.atan2(tan.y, tan.x);
        double headingErr = Pose2d.wrap(desiredHeading - pose.heading);

        double vx = kPos * toTarget.x;
        double vy = kPos * toTarget.y;
        double omega = kHead * headingErr;

        return new Command(vx, vy, omega);
    }

    private static double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}
