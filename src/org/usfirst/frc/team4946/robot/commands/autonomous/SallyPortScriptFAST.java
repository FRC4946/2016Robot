package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.arm.LowerArm;
import org.usfirst.frc.team4946.robot.commands.arm.RaiseArm;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * DOES NOTHING
 */
public class SallyPortScriptFAST extends CommandGroup {

	public SallyPortScriptFAST() {


		// Save this position as the angle 0
		Robot.driveTrainSubsystem.resetGyro();
//		
//		// Lower the arm while driving forwards
		addParallel(new LowerArm(), 1.3);
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES+15, 0.5));
//
//		// Drive in the rest of the way
//		// The arm stays on the ground as the robot moves up the ramp?
//		addParallel(new LowerArm(0.1), 1.0);
		addSequential(new DriveDistance(20, 0.5), 1);
//
//		// Raise the bridge
		addSequential(new DriveDistance(39, -0.6, 0.5));
		addParallel(new RaiseArm(), 0.2);
		addSequential(new Turn(1.0), 0.75);
//		addSequential(new RotateToAngle(-180), 1.0);

//		addSequential(new ReturnToZeroAngle(180), 1.5);
		addSequential(new DriveDistance(20, -1.0));
//		addSequential(new Turn(0.7), 0.3);
//		addSequential(new DriveDistance(30, -0.7));
//		addSequential(new Turn(-0.7), 0.4);
		addSequential(new DriveDistance(AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES+50, -0.7));
	}
}
