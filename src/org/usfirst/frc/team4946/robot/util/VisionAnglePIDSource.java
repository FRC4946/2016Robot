package org.usfirst.frc.team4946.robot.util;

import org.usfirst.frc.team4946.robot.subsystems.Vision2;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class VisionAnglePIDSource implements PIDSource {

	private PIDSourceType type = null;

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		type = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return type;
	}

	@Override
	public double pidGet() {

		double angle = Vision2.getAngle();
		if (angle != Vision2.getAngle()) {
			return angle;
		}
		return 0;
	}

}
