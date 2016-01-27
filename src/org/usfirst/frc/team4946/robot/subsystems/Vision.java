package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {

	final static double k_grav = 9.8; // 9.8 m/s^2
	final static double k_angleDeg = 60; // 60deg
	final static double k_angleRad = Math.toRadians(60);
	final static double k_targetHeight = 2.4384; // The height of the tower
	final static double k_wheelDia = 0.1016; // Shooter wheel diameter

	private AnalogInput m_distanceSensor = new AnalogInput(
			RobotMap.k_ANA_RANGE_FINDER);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	/**
	 * Get the range found from the ultrasonic range finder, in cm
	 * 
	 * @return the distance, in cm
	 */
	public double getUltrasonicDistance() {
		// At 5v, the output is 4.9 millivolts per 2cm
		// So 4.9/2 = 2.45mv per 1 cm
		// 2.45mv = 0.00245v per cm
		return m_distanceSensor.getVoltage() / 0.00245;
	}

	/**
	 * Determine the initial velocity required of the ball to be shot into the
	 * goal from distance <code>x</code>.
	 * 
	 * @param x
	 *            the distance from the goal, in meters
	 * @return the velocity required, in m/s
	 */
	public double getRequiredVelocity(double x) {

		double top = k_grav * x * x; // dx^2
		double a = 2 * Math.cos(k_angleRad) * Math.cos(k_angleRad); // 2*cos^2(rad)
		double b = (x * Math.tan(k_angleRad)) - k_targetHeight; // x*tan(rad)-y

		double vel = Math.sqrt(top / (a * b));

		return vel;
	}

	/**
	 * Determine the RPM required of the shooter wheels in order for the ball to
	 * be shot into the goal from distance <code>x</code>.
	 * 
	 * @param x
	 *            the distance from the goal, in meters
	 * @return the wheel speed required, in RPM
	 */
	public double getRequiredRPM(double x) {
		return 60 / k_wheelDia * getRequiredVelocity(x);
	}

	/**
	 * Convert a velocity to the wheel speed required to fire the ball at that
	 * speed.
	 * 
	 * @param vel
	 *            the required velocity of the projectile
	 * @return the wheel speed required, in RPM
	 */
	public double velToRPM(double vel) {
		return 60 / k_wheelDia * vel;
	}
	
	/**
	 * Convert a wheel speed to the projectile's initial velocity
	 * 
	 * @param RPM
	 *            the wheel speed, in RPM
	 * @return the initial velocity, in m/s
	 */
	public double RPMToVel(double RPM) {
		
		return RPM / (60 / k_wheelDia);
	}
}
