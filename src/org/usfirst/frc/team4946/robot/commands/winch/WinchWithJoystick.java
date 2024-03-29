package org.usfirst.frc.team4946.robot.commands.winch;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchWithJoystick extends Command {

	public WinchWithJoystick() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.winchSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.winchSubsystem
				.setSpeed(Robot.oi.getOperatorStick().getRawAxis(1));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.winchSubsystem.setSpeed(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
