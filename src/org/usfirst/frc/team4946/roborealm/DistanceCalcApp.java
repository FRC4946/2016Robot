package org.usfirst.frc.team4946.roborealm;

import javafx.geometry.Point3D;

import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DistanceCalcApp {

	private static final double kPhi_deg = 0;
	private static final double kPhi = Math.toRadians(kPhi_deg);
	private static final int kAngleErrorMax = 30;

	public static void main(String[] args) {
		// NetworkTable.setClientMode();
		// NetworkTable.setIPAddress("roboRIO-4946-frc.local");
		// NetworkTable table = NetworkTable.getTable("RoboRealm");

		while (true) {

			// Delay for a tenth of a second
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Get all of the point data from RoboRealm/the Network Tables
			// Point3D topLeft = new Point3D(table.getNumber("TOP_LEFT_X", 0),
			// table.getNumber("TOP_LEFT_Y", 0), 3);
			// Point3D topRight = new Point3D(table.getNumber("TOP_RIGHT_X", 0),
			// table.getNumber("TOP_RIGHT_Y", 0), 3);
			// Point3D botLeft = new Point3D(table.getNumber("BOT_LEFT_X", 0),
			// table.getNumber("BOT_LEFT_Y", 0), 3);
			// Point3D botRight = new Point3D(table.getNumber("BOT_RIGHT_X", 0),
			// table.getNumber("BOT_RIGHT_Y", 0), 3);

			Point3D topLeft = new Point3D(25, 75, 3);
			Point3D topRight = new Point3D(75, 75, 3);
			Point3D botLeft = new Point3D(25, 25, 3);
			Point3D botRight = new Point3D(75, 25, 3);

			double horizAvg = (topLeft.getX() + botLeft.getX()
					+ topRight.getX() + botRight.getX()) / 4;
			double vertAvg = (topLeft.getY() + botLeft.getY() + topRight.getY() + botRight
					.getY()) / 4;

			Vector3D p1 = new Vector3D(horizAvg, vertAvg, 2);
			Vector3D p2 = new Vector3D(p1.getX(), cos(kPhi) + p1.getY(),
					sin(kPhi) + 2);

			// Calculate the distance and print it
			double distanceInches = calculateDistance(p1, p2, topLeft,
					topRight, botRight);
			System.out.println(distanceInches);

			// TODO: Convert inches to meters, v0, RPM. Send values back onto
			// the network tables
			break;
		}
	}

	private static double calculateDistance(Vector3D p1, Vector3D p2,
			Point3D q1, Point3D q2, Point3D q3) {

		// Create vars to hold the best angle found
		Plane bestPlane = null;
		double bestAngle = 180;

		// Iterate through all allowed angles of the plane
		for (int i = -kAngleErrorMax; i <= kAngleErrorMax; i++) {
			double alpha = Math.toRadians(i);

			// Find the third point that defines the plane
			Vector3D p3 = new Vector3D(cos(alpha) + p1.getX(), -sin(alpha)
					* sin(kPhi) + p1.getY(), sin(alpha) * cos(kPhi) + 2);

			// Find the plane defined by these three points
			Plane plane = new Plane(p1, p2, p3, 0.01);

			// Get the angle of the vectors projected into the plane
			double angle = getAngle(plane, q1, q2, q3);

			// If the new angle is closer to 90 degrees, save it as the best
			// match so far
			if (Math.abs(angle - 90) < Math.abs(bestAngle - 90)) {
				bestAngle = angle;
				bestPlane = plane;
			}
		}

		// If we found no possible planes on which to map the vectors, return -1
		if (bestPlane == null) {
			return -1;
		}

		// Otherwise, get the best points as projected on the plane
		Point3D[] points = { q1, q2, q3 };
		points = getPointsOnPlane(bestPlane, points);

		// And calculate the distance between the points, in arbitrary units
		double width = points[0].distance(points[1]);
		// double height = points[1].distance(points[2]);

		// TODO: Linear eqn to convert arbitrary units to inches or meters
		return width;// * 1234;

	}

	/**
	 * Map the intersections of vectors <code>v1,v2,v3</code> and a given plane
	 * 
	 * @param plane
	 *            the plane on which to project the vectors
	 * @param points
	 *            The array of vectors to project
	 * @return the intersections points, as a {@code Point3D[]}
	 */
	public static Point3D[] getPointsOnPlane(Plane plane, Point3D[] points) {

		// Create a point at the origin, to allow us to easily create lines from
		// the origin to the vectors.
		Vector3D origin = new Vector3D(0, 0, 0);

		for (int i = 0; i < points.length; i++) {
			// Find the vectors that pass through the given points and (0,0,0)
			Line line1 = new Line(origin, ptToVec(points[i]), 0.01);

			// Find where these vectors intersect the plane
			points[i] = vecToPt(plane.intersection(line1));
		}

		return points;
	}

	/**
	 * Get the angle of q1,q2,q3 through q2.
	 * 
	 * @param plane
	 *            the plane on which q1,q2,q3 should be projected
	 * @param q1
	 *            An endpoint of the angle to find
	 * @param q2
	 *            The vertex of the angle to find
	 * @param q3
	 *            An endpoint of the angle to find
	 * @return The angle, in degrees
	 */
	public static double getAngle(Plane plane, Point3D q1, Point3D q2,
			Point3D q3) {

		Point3D[] points = { q1, q2, q3 };

		// Get the points as they intersect the plane
		points = getPointsOnPlane(plane, points);

		// Return the angle
		return points[1].angle(points[0], points[2]);
	}

	/**
	 * Convert an {@link Vector3D} to a {@link Point3D}
	 * 
	 * 
	 * @param vec
	 *            the {@code Point3D}
	 * @return the converted {@code Vector3D}
	 */
	public static Vector3D ptToVec(Point3D point) {
		return new Vector3D(point.getX(), point.getY(), point.getZ());
	}

	/**
	 * Convert an {@link Point3D} to a {@link Vector3D}
	 * 
	 * @param vec
	 *            the {@code Vector3D}
	 * @return the converted {@code Point3D}
	 */
	public static Point3D vecToPt(Vector3D vec) {
		return new Point3D(vec.getX(), vec.getY(), vec.getZ());
	}

	/**
	 * Cosine of x
	 * 
	 * @param x
	 *            radians
	 * @see Math#cos(double)
	 */
	public static double cos(double x) {
		return Math.cos(x);
	}

	/**
	 * Sine of x
	 * 
	 * @param x
	 *            radians
	 * @see Math#sin(double)
	 */
	public static double sin(double x) {
		return Math.sin(x);
	}

}
