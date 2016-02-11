package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.Robot;

/**
 *
 */
public class Vision {
	public final static int ERROR_UNKNOWN = -1;
	// public final static int ERROR_NO_CORNERS = -2;
	// public final static int ERROR_INVALID_EDGES = -3;
	public final static int ERROR_NO_TARGET = -4;
	public final static int ERROR_NO_VELOCITY = -5;

	private final static double k_wheelDia = 0.1016; // Shooter wheel diameter

	private static class NewtonMethod {

		// double g = 9.81; // Gravity. m/s^2
		// double vt = 14.0791674308; // Terminal Velocity
		// double vt2 = 198.222955545; // Terminal Velocity Squared
		private static final double vt4_g = 4006.56063066; // Terminal
															// Velocity^4
															// divided by
															// Gravity
		private static final double vt2_g = 20.2123947736; // Terminal Velocity
															// Squared divided
															// by Gravity

		private static final double hRobot = 0.288544;
		private static final double hTarget = 2.4384;

		public static final double tolerance = 0.000001;
		public static final double initialV0 = 8;

		/**
		 * Height as a function of initial velocity
		 * 
		 * @param v0
		 *            the initial velocity
		 * @param distance
		 *            is the distance from the target
		 * @returns the height as a function of v0
		 */
		private static double height(double v0, double distance) {

			double term1 = (-2 * vt4_g * Math.exp(distance / vt2_g) + 2 * vt4_g)
					/ v0;
			double term2 = (vt2_g * Math.sqrt(3) / 2 * v0) + vt4_g;
			double term4 = (-2 * Math.exp(distance / vt2_g) + 2) / v0;
			double term3 = 1 - Math.exp(term4);
			double height = hRobot - hTarget;

			return term1 + (term2 * term3) + height;
		}

		/**
		 * Derivative of height(v0)
		 * 
		 * @param v0
		 *            the initial velocity
		 * @param distance
		 *            is the distance from the target
		 * @returns the slope of the function
		 */
		private static double heightPrime(double v0, double distance) {
			double delta = 0.01;

			double term1 = (height(v0 + delta, distance) - height(v0, distance))
					/ delta;
			return term1;
		}

		/**
		 * Function to iterate
		 */
		private static double f(double v0, double distance) {

			double term1 = v0
					- (height(v0, distance) / heightPrime(v0, distance));

			return term1;
		}

		/**
		 * Approximates v0 based on the distance.
		 * 
		 * @param tolerance
		 *            the minimum allowable tolerance of imprecision of the
		 *            value calculated by the newton's method
		 * @param v0
		 *            the initial guess of v0
		 * @param distance
		 *            the distance of the robot from the target
		 * @returns the approximation of v0
		 */
		public static double calculateVelocity(double tolerance, double v0,
				double distance) {

			// Set a max count for the number of iterations of newton's method
			double max_count = 200;

			for (double i = 1; (Math.abs(height(v0, distance)) > tolerance)
					&& (i < max_count); i++) {
				v0 = f(v0, distance);
			}

			if (Math.abs(height(v0, distance)) <= tolerance) {
				// System.out.println("Zero found at x="+v0);
				return v0;
			} else {
				// System.out.println("Failed to find a zero");
				return -1;
			}
		}

		/**
		 * Approximates v0 based on the distance.
		 * 
		 * @param distance
		 *            the distance of the robot from the target
		 * @returns the approximation of v0
		 */
		public static double calculateVelocity(double distance) {
			return calculateVelocity(tolerance, initialV0, distance);
		}

	}

	// /**
	// * Checks whether or not the camera has successfully found 4 corners
	// *
	// * @return whether corners were found or not
	// */
	// public boolean hasCorners() {
	// return Robot.networkTable.getBoolean("HAS_CORNERS", false);
	// }
	//
	// /**
	// * Checks whether or not the corners found are too close to the edge of
	// the
	// * viewport.
	// *
	// * @return whether corners were valid
	// */
	// public boolean hasValidEdges() {
	// return !Robot.networkTable.getBoolean("EDGE_PROXIMITY", true);
	// }

	/**
	 * Checks whether or not the camera has successfully found the target
	 * 
	 * @return whether the target were found or not
	 */
	public static boolean foundTarget() {
		return Robot.networkTable.getNumber("TL_DETECTED", Vision.ERROR_NO_TARGET ) >= 0;
	}

	//
	// /**
	// * Get the four corners of the target and return them in an array of
	// * {@link Point} objects. The {@code Point}s will always be in the order:
	// * <li>Top Left <li>Top Right <li>Bot Left <li>Bot Right
	// *
	// * @return the array of {@code Point}s, or {@code null} if an error
	// occurred
	// */
	// public Point[] getTargetCoords() {
	//
	// // If no points were found or they were invalid, return null
	// if (!hasCorners() || !hasValidEdges()) {
	// return null;
	// }
	//
	// // Try to get all the points from the camera
	// try {
	// Point[] points = {};
	//
	// points[0] = new Point((int) Robot.networkTable.getNumber(
	// "TOP_LEFT_X", 0), (int) Robot.networkTable.getNumber(
	// "TOP_LEFT_Y", 0));
	// points[1] = new Point((int) Robot.networkTable.getNumber(
	// "TOP_RIGHT_X", 0), (int) Robot.networkTable.getNumber(
	// "TOP_RIGHT_Y", 0));
	// points[2] = new Point((int) Robot.networkTable.getNumber(
	// "BOT_LEFT_X", 0), (int) Robot.networkTable.getNumber(
	// "BOT_LEFT_Y", 0));
	// points[3] = new Point((int) Robot.networkTable.getNumber(
	// "BOT_RIGHT_X", 0), (int) Robot.networkTable.getNumber(
	// "BOT_RIGHT_Y", 0));
	//
	// // If we successfully got all the points, return them.
	// return points;
	//
	// // If an error occurred, return null
	// } catch (TableKeyNotDefinedException ex) {
	// return null;
	// }
	// }

	/**
	 * Get the distance away from the target from the network tables
	 * 
	 * @return the distance, in meters, or {@link Vision#ERROR_NO_TARGET} if an
	 *         error occurred
	 */
	public static double getDistance() {
		if (!foundTarget()) {
			return Vision.ERROR_NO_TARGET;
		}

		return Robot.networkTable.getNumber("DISTANCE_FINAL", Vision.ERROR_NO_TARGET);
	}

	/**
	 * Determine the initial velocity required of the ball to be shot into the
	 * goal from the distance specified by {@link Vision#getDistance()}.
	 * 
	 * @return the velocity required, in m/s, or less than 0 in the case of an
	 *         error
	 * @see Vision#ERROR_NO_TARGET
	 * @see Vision#ERROR_NO_VELOCITY
	 * @see Vision#ERROR_UNKNOWN
	 */
	public static double getRequiredVelocity() {

		// Get the required distance from the network tables. If there was an
		// error, return the error.
		double dist = getDistance();
		if (dist < 0) {
			return dist;
		}

		// Otherwise, if there is a valid distance, return the required
		// velocity.
		return getRequiredVelocity(getDistance());
	}

	/**
	 * Determine the initial velocity required of the ball to be shot into the
	 * goal from distance <code>x</code>. This method utilizes the
	 * {@link NewtonMethod} class, which implements a newton's method to
	 * approximate the required initial velocity with a precision of
	 * {@link NewtonMethod#tolerance}
	 * 
	 * @param distance
	 *            the distance from the goal, in meters
	 * @return the velocity required, in m/s, or less than 0 in the case of an
	 *         error
	 * @see NewtonMethod#calculateVelocity(double)
	 * @see Vision#ERROR_NO_TARGET
	 * @see Vision#ERROR_NO_VELOCITY
	 * @see Vision#ERROR_UNKNOWN
	 */
	public static double getRequiredVelocity(double distance) {

		// if (!hasCorners()) {
		// return Vision.ERROR_NO_CORNERS;
		// }
		// if (!hasValidEdges()) {
		// return Vision.ERROR_INVALID_EDGES;
		// }
		if (!foundTarget()) {
			return Vision.ERROR_NO_TARGET;
		}

		double vel = NewtonMethod.calculateVelocity(distance);
		if (vel == -1) {
			return Vision.ERROR_NO_VELOCITY;
		}

		return vel;
	}

	/**
	 * Determine the RPM required of the shooter wheels in order for the ball to
	 * be shot into the goal from distance <code>x</code>.
	 * 
	 * @param distance
	 *            the distance from the goal, in meters
	 * @return the wheel speed required, in RPM, or less than 0 if an error
	 *         occurred
	 * @see Vision#getRequiredVelocity();
	 * @see Vision#ERROR_NO_TARGET
	 * @see Vision#ERROR_NO_VELOCITY
	 * @see Vision#ERROR_UNKNOWN
	 */
	public static double getRequiredRPM(double distance) {

		// Get the velocity. If an error code was returned, continue to pass
		// along the error.
		double vel = getRequiredVelocity(distance);
		if (vel < 0) {
			return vel;
		}

		// Otherwise, convert the velocity to an RPM and return it.
		return velToRPM(vel);
	}

	/**
	 * Convert a velocity to the wheel speed required to fire the ball at that
	 * speed.
	 * 
	 * @param vel
	 *            the required velocity of the projectile
	 * @return the wheel speed required, in RPM
	 */
	public static double velToRPM(double vel) {
		return 60 / k_wheelDia * vel;
	}

	/**
	 * Convert a wheel speed to the projectile's initial velocity
	 * 
	 * @param RPM
	 *            the wheel speed, in RPM
	 * @return the initial velocity, in m/s
	 */
	public static double RPMToVel(double RPM) {
		return RPM / (60 / k_wheelDia);
	}
}
