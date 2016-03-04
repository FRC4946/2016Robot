package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.ReturnToZeroAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * To be used in autonomous. This command group will drive the robot from the
 * neutral zone to the courtyard, passing through the ROUGH TERRAIN defense.
 * 
 * 1. Drive forwards {@link AutonomousWrapper#DISTANCE_AUTO_ZONE_INCHES} feet to
 * traverse from neutral to the edge of the outer works. 2. Reduce speed to 75%,
 * drive over the defense. 2. Done.
 */
public class RoughTerrainScript extends CommandGroup {

	public RoughTerrainScript() {

		// // Drive from the outside of the neutral zone to the edge of the
		// outer
		// // works
		// addSequential(new DriveDistance(
		// AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES, 0.5));

		// Save this position as the angle 0
		Robot.driveTrainSubsystem.resetGyro();

		// Reduce speed. Traverse the defense.
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_DEFENSE_WIDTH_INCHES, 0.7));

		addSequential(new ReturnToZeroAngle());
	}
}
