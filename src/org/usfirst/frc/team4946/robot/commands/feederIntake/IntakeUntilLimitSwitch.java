package org.usfirst.frc.team4946.robot.commands.feederIntake;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeUntilLimitSwitch extends Command {

	public IntakeUntilLimitSwitch() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.feederSubsystem);
		requires(Robot.intakeSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!Robot.feederSubsystem.getLimitSwitchIsHeld()) {
			Robot.intakeSubsystem.setInRollerSpeed(0.4);
			Robot.feederSubsystem.setSpeed(1.0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.feederSubsystem.getLimitSwitchIsHeld();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intakeSubsystem.setInRollerSpeed(0.0);
		Robot.feederSubsystem.setSpeed(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
