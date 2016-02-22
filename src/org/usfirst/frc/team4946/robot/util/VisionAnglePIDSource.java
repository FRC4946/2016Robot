package org.usfirst.frc.team4946.robot.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class VisionAnglePIDSource implements PIDSource, ITableListener {

	private double visionAngle = 0.0;
	private PIDSourceType type = null;
	private Encoder m_encLeft = null;
	private Encoder m_encRight = null;
	
	private boolean hasFreshValue = true;

	public VisionAnglePIDSource(Encoder encLeft, Encoder encRight) {
		m_encLeft = encLeft;
		m_encRight = encRight;

		m_encLeft.reset();
		m_encRight.reset();
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

		if(hasFreshValue == false){
			SmartDashboard.putNumber("Thing Angle", 0.0);
			return 0.0;
		}
		
		double wheelCirc = 8 * Math.PI;
		double avgWheelRotationInch = (Math.abs(m_encLeft.getDistance()) + Math
				.abs(m_encRight.getDistance())) / 2.0;
		double rotations = avgWheelRotationInch / wheelCirc;

		double degreesPerRotation = 60;//((8.0 / 29.75) * 360.0);
		double degreesTurned = rotations * degreesPerRotation;

		// Turning right: Right increase, left decrease
		if (m_encLeft.getDistance() < 0 && m_encRight.getDistance() > 0) {
			visionAngle -= degreesTurned;
		} else if (m_encLeft.getDistance() > 0 && m_encRight.getDistance() < 0) {
			visionAngle += degreesTurned;
		}

		m_encLeft.reset();
		m_encRight.reset();

		SmartDashboard.putNumber("Thing Angle", visionAngle);
		return visionAngle;
	}

	@Override
	public void valueChanged(ITable source, String key, Object value,
			boolean isNew) {
		hasFreshValue = true;
		m_encLeft.reset();
		m_encRight.reset();
		SmartDashboard.putNumber("Cam Angle", visionAngle);
		visionAngle = source.getNumber("FINAL_ANGLE_TO_GOAL", 0);
	}

	
	public void waitForFreshValue(){
		hasFreshValue = false;
		System.out.println("ASDFASDFADSFA");
	}
}
