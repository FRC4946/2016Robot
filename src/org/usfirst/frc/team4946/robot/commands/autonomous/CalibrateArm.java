package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrateArm extends Command {

	public CalibrateArm() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.armSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!Robot.armSubsystem.getArmIsUpright()) {
			Robot.armSubsystem.setArmSpeed(-0.5);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
		//return Robot.armSubsystem.getArmIsUpright();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
