package org.usfirst.frc.team4946.roborealm;

////import javafx.geometry.Point3D;
//
//import javafx.geometry.Point3D;
//
//import org.apache.commons.math3.geometry.euclidean.threed.Line;
//import org.apache.commons.math3.geometry.euclidean.threed.Plane;
//import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
//
//import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DistanceCalcApp {
	//
	// private static final double kPhi_deg = 45;
	// private static final double kPhi = Math.toRadians(kPhi_deg);
	// private static final double kFOVH_deg = 67;
	// private static final double kFOVH = Math.toRadians(kFOVH_deg);
	// private static final double kFOVV_deg = 67 * 0.75;
	// private static final double kFOVV = Math.toRadians(kFOVV_deg);
	// private static final double arbitraryZ = 3.0;
	//
	// private static final int kAngleErrorMax = 30;
	//
	// private static double goalAngle;
	// private static double angleError;
	// private static double goalRatio;
	//
	// public static Point3D[] getTestPoints() {
	//
	// double angle = 0; // Math.random() * 20;
	// System.out.println("Angle:" + angle);
	// angle = Math.toRadians(angle);
	//
	// double yDist = 71.5 + 6.0;
	// System.out.println("Height: " + yDist);
	//
	// double zDist = 89; // Math.random() * 40 + 65.0;
	// System.out.println("Depth: " + zDist);
	//
	// double xDist = 0; // Math.random() * 50;
	// System.out.println("x-Distance: " + xDist);
	//
	// Point3D[] goalPoints = {
	// /* TopLeft */new Point3D(xDist - (10 * cos(angle)),
	// yDist + 6.0, zDist - (10 * sin(angle))),
	// /* TopRight */new Point3D(xDist + (10 * cos(angle)),
	// yDist + 6.0, zDist + (10 * sin(angle))),
	// /* BotLeft */new Point3D(xDist - (10 * cos(angle)),
	// yDist - 6.0, zDist - (10 * sin(angle))),
	// /* BotRight */new Point3D(xDist + (10 * cos(angle)),
	// yDist - 6.0, zDist + (10 * sin(angle))) };
	//
	// for (int i = 0; i < goalPoints.length; i++) {
	// double a = Math.atan(goalPoints[i].getY() / goalPoints[i].getZ())
	// - kPhi;
	// double yShift = a / (kFOVV / 2.0) * 300;
	//
	// a = Math.atan(goalPoints[i].getX()
	// / (Math.sqrt(goalPoints[i].getZ() * goalPoints[i].getZ()
	// + goalPoints[i].getY() * goalPoints[i].getY())));
	// double xShift = a / (kFOVH / 2.0) * 400;
	//
	// goalPoints[i] = new Point3D(xShift, yShift, 300);
	// System.out.println(goalPoints[i].getX() + "\t"
	// + goalPoints[i].getY());
	// }
	//
	// Point3D[] goalPoints2 = { new Point3D(-47, -28, 300),
	// new Point3D(56, -29, 300), new Point3D(-54, -77, 300),
	// new Point3D(60, -77, 300) };
	//
	// return goalPoints;
	// }
	//
	// @SuppressWarnings("unused")
	// public static void main(String[] args) {
	// NetworkTable.setClientMode();
	// NetworkTable.setIPAddress("roboRIO-4946-frc.local");
	// NetworkTable table = NetworkTable.getTable("RoboRealm");
	//
	// while (true) {
	//
	// // Delay for a tenth of a second
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// // Get all of the point data from RoboRealm/the Network Tables
	// Point3D topLeft = new Point3D(table.getNumber("TOP_LEFT_X", 0),
	// table.getNumber("TOP_LEFT_Y", 0), 3);
	// Point3D topRight = new Point3D(table.getNumber("TOP_RIGHT_X", 0),
	// table.getNumber("TOP_RIGHT_Y", 0), 3);
	// Point3D botLeft = new Point3D(table.getNumber("BOT_LEFT_X", 0),
	// table.getNumber("BOT_LEFT_Y", 0), 3);
	// Point3D botRight = new Point3D(table.getNumber("BOT_RIGHT_X", 0),
	// table.getNumber("BOT_RIGHT_Y", 0), 3);
	//
	// // Point3D topLeft = new Point3D(25, 75, 3);
	// // Point3D topRight = new Point3D(75, 75, 3);
	// // Point3D botLeft = new Point3D(25, 25, 3);
	// // Point3D botRight = new Point3D(75, 25, 3);
	//
	// // Translate the points so that the origin (0,0,0) is at the centre
	// // of the screen
	// topLeft = new Point3D(400 - topLeft.getX(), 300 - topLeft.getY(),
	// 300);
	// topRight = new Point3D(400 - topRight.getX(),
	// 300 - topRight.getY(), 300);
	// botLeft = new Point3D(400 - botLeft.getX(), 300 - botLeft.getY(),
	// 300);
	// botRight = new Point3D(400 - botRight.getX(),
	// 300 - botRight.getY(), 300);
	//
	// // Point3D[] pts = getTestPoints();
	//
	// // Point3D topLeft = pts[0];
	// // Point3D topRight = pts[1];
	// // Point3D botLeft = pts[2];
	// // Point3D botRight = pts[3];
	//
	// double horizAvg = (topLeft.getX() + botLeft.getX()
	// + topRight.getX() + botRight.getX()) / 4.0;
	// double percentAngle = (horizAvg/400.0)*(kFOVH_deg/2.0); //TODO:
	// double vertAvg = (topLeft.getY() + botLeft.getY() + topRight.getY() +
	// botRight
	// .getY()) / 4.0;
	//
	// // System.out.println(horizAvg + ", " + vertAvg);
	//
	// Vector3D p1 = new Vector3D(0, 0, 2);
	// Vector3D p2 = new Vector3D(p1.getX(), cos(kPhi) + p1.getY(),
	// sin(kPhi) + p1.getZ());
	//
	// // Calculate the distance and print it
	// double distanceInches = calculateDistance(p1, p2, topLeft,
	// topRight, botRight, botLeft);
	// System.out.println(distanceInches + "\t|\t" + angleError + "\t|\t"
	// + goalAngle + "\t|\t" + goalRatio);
	//
	// double distanceMeters = distanceInches * 0.0254;
	// double vel = CalculateVelocityWithDrag.calcVelocity(distanceMeters);
	// double rpm = (vel/(0.1016 * Math.PI)) * 60;
	//
	// // TODO: Convert inches to meters, v0, RPM. Send values back onto
	// // the network tables
	// table.putNumber("FINAL_DISTANCE_INCHES", distanceInches);
	// table.putNumber("FINAL_ANGLE_TO_GOAL", percentAngle);
	// table.putNumber("FINAL_VELOCITY", vel);
	// table.putNumber("FINAL_RPM", rpm);
	// }
	// }
	//
	// /**
	// * Takes 4 points on the camera and finds the distance to the base of the
	// * goal.
	// *
	// * @param p1
	// * @param p2
	// * @param q1
	// * @param q2
	// * @param q3
	// * @param q4
	// * @return
	// */
	// private static double calculateDistance(Vector3D p1, Vector3D p2,
	// Point3D q1, Point3D q2, Point3D q3, Point3D q4) {
	//
	// Point3D[] points = { q1, q2, q3, q4 };
	//
	// // Take all the points on the camera,
	// // and convert them to 3D vectors exiting from the camera lens.
	// for (int i = 0; i < points.length; i++) {
	//
	// double x = points[i].getX();
	//
	// // Turn x into an angle from the center of vision
	// x *= kFOVH / 800.0;
	// x = arbitraryZ * Math.tan(x);
	//
	// double y = points[i].getY();
	//
	// // Turn y into an angle from the center of vision
	// y *= kFOVV / 600.0;
	// y = arbitraryZ * Math.tan(y);
	//
	// double depth = Math.sqrt(arbitraryZ * arbitraryZ - (x * x + y * y));
	// points[i] = new Point3D(x, y, depth);
	// System.out.println(points[i]);
	//
	// }
	//
	// // All sets of points to check
	// Point3D[][] cornerPoints = { { points[0], points[1], points[2] },
	// { points[1], points[2], points[3] },
	// { points[2], points[3], points[0] },
	// { points[3], points[0], points[1] } };
	//
	// // Create vars to hold the best angle found
	// Plane bestPlane = null;
	// double minError = 100000;
	//
	// // Iterate through all allowed angles of the plane
	// for (double i = -kAngleErrorMax; i <= kAngleErrorMax; i += 0.001) {
	// double alpha = Math.toRadians(i);
	//
	// // Find the third point that defines the plane
	// Vector3D p3 = new Vector3D(cos(alpha) + p1.getX(), -sin(alpha)
	// * sin(kPhi) + p1.getY(), sin(alpha) * cos(kPhi) + p1.getZ());
	//
	// // Find the plane defined by these three points
	// Plane plane = new Plane(p1, p2, p3, 0.00001);
	//
	// double error = 0;
	// for (Point3D[] q : cornerPoints) {
	//
	// // Get the angle of the vectors projected into the plane
	// double angle = getAngle(plane, q[0], q[1], q[2]);
	// double angleError = 90 - angle;
	// error += angleError * angleError;
	// }
	//
	// // If the new angle is closer to 90 degrees, save it as the best
	// // match so far
	// if (error < minError) {
	// minError = error;
	// bestPlane = plane;
	// goalAngle = i;
	// }
	// }
	//
	// angleError = minError;
	//
	// // If we found no possible planes on which to map the vectors, return -1
	// if (bestPlane == null) {
	// return -1;
	// }
	//
	// // Otherwise, get the best points as projected on the plane
	// points = getPointsOnPlane(bestPlane, points);
	//
	// // And calculate the distance between the points, in arbitrary units
	// double width = points[0].distance(points[1]);
	// double height = points[1].distance(points[2]);
	//
	// goalRatio = height / width;
	//
	// double towerHeight = height * (71.5 / 12.0);
	//
	// double x = (points[2].getX() + points[3].getX()) / 2;
	// double y = (points[2].getY() + points[3].getY()) / 2;
	// double z = (points[2].getZ() + points[3].getZ()) / 2;
	//
	// Point3D towerBase = new Point3D(x, y - (cos(kPhi) * towerHeight), z
	// - (sin(kPhi) * towerHeight));
	// Point3D origin = new Point3D(0, 0, 0);
	// double distance = towerBase.distance(origin);
	//
	// double k = (height / 12.0 + width / 20.0) / 2.0;
	//
	// // TODO: Linear eqn to convert arbitrary units to inches or meters
	// return distance / k;
	// }
	//
	// /**
	// * Map the intersections of vectors <code>v1, v2, v3</code> and a given
	// * plane
	// *
	// * @param plane
	// * the plane on which to project the vectors
	// * @param points
	// * The array of vectors to project
	// * @return the intersections points, as a {@code Point3D[]}
	// */
	// public static Point3D[] getPointsOnPlane(Plane plane, Point3D[] points) {
	//
	// // Create a point at the origin, to allow us to easily create lines from
	// // the origin to the vectors.
	// Vector3D origin = new Vector3D(0, 0, 0);
	//
	// for (int i = 0; i < points.length; i++) {
	// // Find the vectors that pass through the given points and (0,0,0)
	// Line line1 = new Line(origin, ptToVec(points[i]), 0.01);
	//
	// // Find where these vectors intersect the plane
	// points[i] = vecToPt(plane.intersection(line1));
	// }
	// return points;
	// }
	//
	// /**
	// * Get the angle of q1,q2,q3 through q2.
	// *
	// * @param plane
	// * the plane on which q1,q2,q3 should be projected
	// * @param q1
	// * An endpoint of the angle to find
	// * @param q2
	// * The vertex of the angle to find
	// * @param q3
	// * An endpoint of the angle to find
	// * @return The angle, in degrees
	// */
	// public static double getAngle(Plane plane, Point3D q1, Point3D q2,
	// Point3D q3) {
	//
	// Point3D[] points = { q1, q2, q3 };
	//
	// // Get the points as they intersect the plane
	// points = getPointsOnPlane(plane, points);
	//
	// // Return the angle
	// return points[1].angle(points[0], points[2]);
	// }
	//
	// /**
	// * Convert an {@link Vector3D} to a {@link Point3D}
	// *
	// * @param vec
	// * the {@code Point3D}
	// * @return the converted {@code Vector3D}
	// */
	// public static Vector3D ptToVec(Point3D point) {
	// return new Vector3D(point.getX(), point.getY(), point.getZ());
	// }
	//
	// /**
	// * Convert an {@link Point3D} to a {@link Vector3D}
	// *
	// * @param vec
	// * the {@code Vector3D}
	// * @return the converted {@code Point3D}
	// */
	// public static Point3D vecToPt(Vector3D vec) {
	// return new Point3D(vec.getX(), vec.getY(), vec.getZ());
	// }
	//
	// /**
	// * Cosine of x
	// *
	// * @param x
	// * radians
	// * @see Math#cos(double)
	// */
	// public static double cos(double x) {
	// return Math.cos(x);
	// }
	//
	// /**
	// * Sine of x
	// *
	// * @param x
	// * radians
	// * @see Math#sin(double)
	// */
	// public static double sin(double x) {
	// return Math.sin(x);
	// }
	//
	// /**
	// * Tangent of x
	// *
	// * @param x
	// * radians
	// * @see Math#tan(double)
	// */
	// public static double tan(double x) {
	// return Math.tan(x);
	// }
}
