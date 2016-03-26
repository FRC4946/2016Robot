package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistanceWithOrientation;
import org.usfirst.frc.team4946.robot.commands.drivetrain.ReturnToZeroAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * To be used in autonomous. This command group will drive the robot from the
 * neutral zone to the courtyard, passing through the RAMPARTS defense.
 * 
 * 1. Drive forwards {@link AutonomousWrapper#DISTANCE_AUTO_ZONE_INCHES} feet to
 * traverse from neutral to the edge of the outer works. 2. Reduce speed to 75%,
 * drive over the defense. 2. Done.
 */
public class RampartsScript extends CommandGroup {

	public RampartsScript() {

		// Save this position as the angle 0
		Robot.driveTrainSubsystem.resetGyro();

		// Drive from the outside of the neutral zone to the edge of the outer
		// works
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES, 1.0));

		// Change speed. Traverse the defense.
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_DEFENSE_WIDTH_INCHES + 60, 1.0));

		addSequential(new ReturnToZeroAngle(), 3);
		
		addSequential(new DriveDistance(50, 0.8));
		
		// DON'T TURN
		addSequential(new Wait(20));
	}
}
