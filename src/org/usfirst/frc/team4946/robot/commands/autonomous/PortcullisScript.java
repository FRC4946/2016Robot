package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.commands.arm.LowerArm;
import org.usfirst.frc.team4946.robot.commands.arm.RaiseArm;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistanceWithOrientation;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * To be used in autonomous. This command group will drive the robot from the
 * neutral zone to the courtyard, passing through the LOW BAR defense.
 * 
 * 1. Drive forwards 6 feet to traverse from neutral to courtyard. 2. Done.
 */
public class PortcullisScript extends CommandGroup {

	public PortcullisScript() {

		// Lower the arm while driving forwards
		addParallel(new LowerArm(), 1.5);
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES, 0.5));

		// Drive in the rest of the way
		// The arm stays on the ground as the robot moves up the ramp?
		addParallel(new LowerArm(0.1), 1.0);
		addSequential(new DriveDistanceWithOrientation(5, 0.3));

		// Raise the bridge
		addSequential(new RaiseArm(), 1.5);

		// Drive through
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_DEFENSE_WIDTH_INCHES - 5.0, 0.75));
	}
}
