package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.arm.LowerArm;
import org.usfirst.frc.team4946.robot.commands.arm.RaiseArm;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.ReturnToZeroAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * DOES NOTHING
 */
public class SallyPortScript extends CommandGroup {

	public SallyPortScript() {


		// Save this position as the angle 0
		Robot.driveTrainSubsystem.resetGyro();
//		
//		// Lower the arm while driving forwards
		addParallel(new LowerArm(), 1.3);
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES+15, 0.5));

		// Drive in the rest of the way
		// The arm stays on the ground as the robot moves up the ramp?
		addParallel(new LowerArm(0.1), 1.0);
		addSequential(new DriveDistance(20, 0.5), 1);

		// Raise the bridge
		addSequential(new DriveDistance(39, -0.6, 0.5));
		addParallel(new RaiseArm(), 0.2);
		addSequential(new DriveDistance(0.0, -1.0), 0.1);
//		addSequential(new RotateToAngle(-20), 1.0);
		addSequential(new RaiseArm(), 0.5);
		
		// Drive through
		addParallel(new RaiseArm(), 0.5);
		addSequential(new DriveDistance(30, 0.6, 0.5));
		addSequential(new RotateToAngle(-90));

		
		addSequential(new ReturnToZeroAngle(180), 3);
		addSequential(new DriveDistance(AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES+60, 0.8));
	}
}
