package org.usfirst.frc.team4946.robot.util;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class NewVisionAnglePIDSource implements PIDSource, ITableListener {

	private double visionAngle = 0.0;
	private PIDSourceType type = null;
	private Gyro m_gyro = null;

	private boolean hasFreshValue = true;

	public NewVisionAnglePIDSource(Gyro gyro) {
		m_gyro = gyro;
		m_gyro.reset();
	}

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

		if (hasFreshValue == false) {
//			SmartDashboard.putNumber("Thing Angle", 0.0);
			return 0.0;
		}

		double degreesTurned = m_gyro.getAngle();

		visionAngle -= degreesTurned;

		m_gyro.reset();

//		SmartDashboard.putNumber("Thing Angle", visionAngle);
		return visionAngle;
	}

	@Override
	public void valueChanged(ITable source, String key, Object value,
			boolean isNew) {
		hasFreshValue = true;
		m_gyro.reset();

//		SmartDashboard.putNumber("Cam Angle", visionAngle);
		visionAngle = source.getNumber("FINAL_ANGLE_TO_GOAL", 0);
	}

	public void waitForFreshValue() {
		hasFreshValue = false;
		System.out.println("ASDFASDFADSFA");
	}
}
