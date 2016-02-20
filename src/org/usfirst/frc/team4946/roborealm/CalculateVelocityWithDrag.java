package org.usfirst.frc.team4946.roborealm;

public class CalculateVelocityWithDrag {

	public static final double MAX_RPM = 10400;

	// Using the max rpm and a diameter of 4.5"
	public static final double MAX_VEL = 62.24123365292099;

	public static final int ERROR_NAN = -1;
	public static final int ERROR_TOO_CLOSE = -2;
	public static final int ERROR_TOO_FAR = -3;

	// private static double roundTo(int digits, double n) {
	// double m = Math.pow(10, digits);
	// return Math.round(m * n) / m;
	// }

	/**
	 * Calculates velocity given a distance from the target
	 * 
	 * @param x
	 *            in meters
	 * @return
	 */
	public static double calcVelocity(double x) {
		if (x < 1.2685)
			return ERROR_TOO_CLOSE;
		else if (x > 20)
			return ERROR_TOO_FAR;
		else
			return newtonsMethod(findInitialEstimate(x), x);
	}

	/**
	 * Finds an integer initial velocity for accurate use of Newton's Method.
	 * 
	 * @param x
	 *            The distance from the target.
	 * @return The closest integer to the root on the positive side (an
	 *         overshoot)
	 */
	private static double findInitialEstimate(double x) {

		// Until the v0 barely undershoots the goal
		for (double v0 = Math.ceil(MAX_VEL); v0 - 0.5 >= 0; v0 -= 0.5) {
			double y = f(v0, x);

			// When the error is finally negative
			if (y < 0) {
				return v0;
			}
		}

		// If nothing works, 9 is good enough
		return 9;
	}

	private static double newtonsMethod(double v0, double x) {

		double tolerance = 1e-9;

		// Iterate Newton's Method until it is within the tolerance
		while (Math.abs(trajectoryError(v0, x)) > tolerance) {
			v0 = iteration(v0, x, tolerance);
		}

		return v0;
	}

	/**
	 * An iteration of Newton's method.
	 * 
	 * @param v0
	 *            The approximation of the root.
	 * @param x
	 *            The distance from the goal.
	 * @param tolerance
	 * @return
	 */
	private static double iteration(double v0, double x, double tolerance) {
		return v0 - f(v0, x) / fprime(v0, x);
	}

	/**
	 * The function that gives the vertical error given an initial velocity and
	 * a position.
	 * 
	 * @param v0
	 *            The initial velocity.
	 * @param x
	 *            The position.
	 * @return The horizontal offset.
	 */
	private static double trajectoryError(double v0, double x) {
		double phi = Math.toRadians(60.0);
		double k = 0.04947459275363921;
		double g = 9.807;
		double a = Math.expm1(k * x) * Math.sqrt(g / k) / Math.cos(phi);
		double h = 2.4384 - 0.288544;

		return Math.log(v0 * Math.sin(phi) * Math.sqrt(k / g)
				* Math.sin(a / v0) + Math.cos(a / v0))
				/ k - h;
	}

	/**
	 * A function whose root is the v0 required for an error of 0.
	 * 
	 * @param v0
	 *            The initial velocity.
	 * @param x
	 *            The distance on the x-axis.
	 * @return +ve if the ball will overshoot the goal, -ve if the ball
	 *         undershoots.
	 */
	private static double f(double v0, double x) {
		double phi = Math.toRadians(60.0);
		double k = 0.04947459275363921;
		double g = 9.807;
		double a = Math.expm1(k * x) * Math.sqrt(g / k) / Math.cos(phi);

		/*
		 * Height of target subtract height of shooter aka the relative height
		 * of the target.
		 */
		double h = 2.4384 - 0.288544;

		return v0 * Math.sin(phi) * Math.sqrt(k / g) * Math.sin(a / v0)
				+ Math.cos(a / v0) - Math.exp(h * k);
	}

	// /**
	// * The approximation for the derivative of f.
	// *
	// * @param v0
	// * The initial velocity.
	// * @param x
	// * The distance.
	// * @param h
	// * The delta for computing the derivative numerically.
	// * @return
	// */
	// private static double fprime(double v0, double x, double h) {
	// return (f(v0 + h, x) - f(v0, x)) / h;
	// }

	/**
	 * The derivative with respect to velocity, solved analytically with
	 * Wolfram|Alpha
	 * http://www.wolframalpha.com/input/?i=derivative+x+*+b+*+sin(
	 * a%2Fx)+%2B+cos(a%2Fx)
	 * 
	 * @param v0
	 *            The velocity.
	 * @param x
	 *            The distance.
	 * @return The slope at the point.
	 */
	private static double fprime(double v0, double x) {
		double phi = Math.toRadians(60.0);
		double k = 0.04947459275363921;
		double g = 9.807;
		double a = Math.expm1(k * x) * Math.sqrt(g / k) / Math.cos(phi);
		double b = Math.sin(phi) * Math.sqrt(k / g);
		return (a / (v0 * v0) + b) * Math.sin(a / v0) - a * b
				* Math.cos(a / v0) / v0;
	}
}