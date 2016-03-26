package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.commands.arm.RaiseArm;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SallyPortPreset extends CommandGroup {

	public SallyPortPreset() {
		
		// Raise the bridge
		addSequential(new DriveDistance(39, -0.6, 0.5));
		addParallel(new RaiseArm(), 0.2);
		addSequential(new Turn(1.0), 0.75);
		// addSequential(new RotateToAngle(-180), 1.0);

		// addSequential(new ReturnToZeroAngle(180), 1.5);
		addSequential(new DriveDistance(20, -1.0));
	}
}
