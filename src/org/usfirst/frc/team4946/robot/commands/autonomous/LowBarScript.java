package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.commands.arm.LowerArm;
import org.usfirst.frc.team4946.robot.commands.arm.RaiseArm;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * To be used in autonomous. This command group will drive the robot from the
 * neutral zone to the courtyard, passing through the LOW BAR defense.
 * 
 * 1. Drive forwards 6 feet to traverse from neutral to courtyard. 2. Done.
 */
public class LowBarScript extends CommandGroup {

	public LowBarScript() {
		// Lower the arm
		addSequential(new LowerArm(), 1.5);
		
		// Drive forwards
		addSequential(new DriveDistance(AutonomousWrapper.DISTANCE_AUTO_ZONE_INCHES, 0.7));
		addSequential(new DriveDistance(
				AutonomousWrapper.DISTANCE_DEFENSE_WIDTH_INCHES+10, 0.7));
		
		// Raise the arm
		addSequential(new RaiseArm(), 1.5);
	}
}
