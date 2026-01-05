package localization;

import math.Pose2d;
import math.Vector2;

public class OdometryPoseEstimator {
    private Pose2d pose;

    public OdometryPoseEstimator(Pose2d initial) {
        this.pose = initial;
    }

    public Pose2d getPose() { return pose; }

    // localDelta: robot-frame dx,dy from odometry integration
    // odomHeading: heading from odometry
    // gyroHeading: heading from IMU
    public void update(Vector2 localDelta, double odomHeading, double gyroHeading) {
        double newHeading = HeadingFusion.fusedHeading(pose.heading, gyroHeading, odomHeading);

        // rotate robot-frame delta into field frame using avg heading for stability
        double avgHeading = Pose2d.wrap((pose.heading + newHeading) / 2.0);
        Vector2 fieldDelta = localDelta.rotate(avgHeading);

        pose = new Pose2d(pose.x + fieldDelta.x, pose.y + fieldDelta.y, newHeading);
    }
}
