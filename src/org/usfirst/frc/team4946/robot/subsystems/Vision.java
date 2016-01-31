package org.usfirst.frc.team4946.robot.subsystems;

import java.awt.Point;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 */
public class Vision extends Subsystem {

	public final static double k_grav = 9.8; // 9.8 m/s^2
	public final static double k_angleDeg = 60; // 60deg
	public final static double k_angleRad = Math.toRadians(60);
	public final static double k_targetHeight = 2.4384; // The height of the
														// tower
	public final static double k_wheelDia = 0.1016; // Shooter wheel diameter

	private AnalogInput m_distanceSensor = new AnalogInput(
			RobotMap.k_ANA_RANGE_FINDER);
	
	/**
	 * Checks whether or not the camera has successfully found 4 corners
	 * 
	 * @return whether corners were found or not
	 */
	public boolean hasCorners() {
		return Robot.networkTable.getBoolean("HAS_CORNERS", false);
	}

	/**
	 * Checks whether or not the corners found are too close to the edge of the
	 * viewport.
	 * 
	 * @return whether corners were valid
	 */
	public boolean hasValidEdges() {
		return !Robot.networkTable.getBoolean("EDGE_PROXIMITY", true);
	}

	/**
	 * Get the four corners of the target and return them in an array of
	 * {@link Point} objects. The {@code Point}s will always be in the order:
	 * <li>Top Left <li>Top Right <li>Bot Left <li>Bot Right
	 * 
	 * @return the array of {@code Point}s, or {@code null} if an error occurred
	 */
	public Point[] getTargetCoords() {

		// If no points were found or they were invalid, return null
		if (!hasCorners() || !hasValidEdges()) {
			return null;
		}

		// Try to get all the points from the camera
		try {
			Point[] points = {};

			points[0] = new Point((int) Robot.networkTable.getNumber(
					"TOP_LEFT_X", 0), (int) Robot.networkTable.getNumber(
					"TOP_LEFT_Y", 0));
			points[1] = new Point((int) Robot.networkTable.getNumber(
					"TOP_RIGHT_X", 0), (int) Robot.networkTable.getNumber(
					"TOP_RIGHT_Y", 0));
			points[2] = new Point((int) Robot.networkTable.getNumber(
					"BOT_LEFT_X", 0), (int) Robot.networkTable.getNumber(
					"BOT_LEFT_Y", 0));
			points[3] = new Point((int) Robot.networkTable.getNumber(
					"BOT_RIGHT_X", 0), (int) Robot.networkTable.getNumber(
					"BOT_RIGHT_Y", 0));

			// If we successfully got all the points, return them.
			return points;

			// If an error occurred, return null
		} catch (TableKeyNotDefinedException ex) {
			return null;
		}
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

		return Math.sqrt(top / (a * b));
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

	@Override
	public void initDefaultCommand() {
	}
}
