package control;

import math.Pose2d;

public class MoveToPoseController {

    public static class Command {
        public final double vx;     // field frame
        public final double vy;     // field frame
        public final double omega;  // rad/s conceptually
        public final boolean atTarget;

        public Command(double vx, double vy, double omega, boolean atTarget) {
            this.vx = vx; this.vy = vy; this.omega = omega; this.atTarget = atTarget;
        }
    }

    // Proportional controller with tolerance + ramp-down near target
    public static Command compute(Pose2d pose,
                                  Pose2d target,
                                  double kPos,
                                  double kHead,
                                  double posTol,
                                  double headTol) {

        double ex = target.x - pose.x;
        double ey = target.y - pose.y;
        double eHead = Pose2d.wrap(target.heading - pose.heading);

        double dist = Math.hypot(ex, ey);
        boolean at = dist < posTol && Math.abs(eHead) < headTol;

        double scale = clamp(dist / (posTol * 5.0), 0.15, 1.0);

        double vx = kPos * ex * scale;
        double vy = kPos * ey * scale;
        double omega = kHead * eHead * scale;

        return new Command(vx, vy, omega, at);
    }

    private static double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}
