# Bézier Curve Tracking + Localization + Move-to-Pose Control

## Overview
Algorithm-focused Java utilities implementing:
- Odometry + IMU pose estimation (x, y, heading) with adaptive heading fusion (“Kalman-inspired” weighting)
- Cubic Bézier curve evaluation and lookahead-based following
- Move-to-pose controller that drives toward a target pose (x, y, θ) with tolerance and ramp-down behavior

## Key Ideas
- **Pose Estimation:** integrate robot-frame odometry deltas into global coordinates using heading, and fuse IMU + odometry heading.
- **Bézier Following:** compute a lookahead point on a cubic Bézier curve and drive toward it while aligning to curve tangent.
- **Control:** proportional control on position and heading; ramp down near target for stability.

## Tech
- Java (no robotics SDK dependencies)

## Notes
This repo intentionally omits hardware integration and platform-specific code to keep the focus on algorithms and software design.
