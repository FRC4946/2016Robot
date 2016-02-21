package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.Robot;

public class Vision2 {

	public static final int ERROR_NO_VEL = -1;
	public static final int ERROR_NO_RPM = -2;
	public static final int ERROR_NO_DISTANCE = -3;
	public static final int ERROR_NO_ANGLE = -4;

	public static double getVel() {
		return Robot.networkTable.getNumber("FINAL_VELOCITY", ERROR_NO_VEL);
	}

	public static double getRPM() {
		return Robot.networkTable.getNumber("FINAL_RPM", ERROR_NO_RPM);
	}

	public static double getDistInches() {
		return Robot.networkTable.getNumber("FINAL_DISTANCE_INCHES",
				ERROR_NO_DISTANCE);
	}

	public static double getDistMeters() {
		double dist = Robot.networkTable.getNumber("FINAL_DISTANCE_INCHES",
				ERROR_NO_DISTANCE);
		if (dist != ERROR_NO_DISTANCE) {
			return dist * 0.0254;
		}
		return ERROR_NO_DISTANCE;
	}

	public static double getAngle() {
		return Robot.networkTable.getNumber("FINAL_ANGLE_TO_GOAL", ERROR_NO_ANGLE);
	}

}
