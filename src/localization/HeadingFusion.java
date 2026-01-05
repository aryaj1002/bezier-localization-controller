package localization;

import math.Pose2d;

public class HeadingFusion {

    // Adaptive weighting ("Kalman-inspired"):
    // if gyro and odom disagree, adjust their influence.
    public static double fusedHeading(double currentHeading,
                                      double gyroHeading,
                                      double odomHeading) {

        double gyroDiff = Pose2d.wrap(gyroHeading - currentHeading);
        double odomDiff = Pose2d.wrap(odomHeading - currentHeading);
        double headingDiff = Pose2d.wrap(odomHeading - gyroHeading);

        double factorGyro = Math.pow(1.15, -Math.abs(headingDiff)) / 2.0;
        double factorOdom = 1.0 - factorGyro;

        double delta = factorGyro * gyroDiff + factorOdom * odomDiff;
        return Pose2d.wrap(currentHeading + delta);
    }
}
