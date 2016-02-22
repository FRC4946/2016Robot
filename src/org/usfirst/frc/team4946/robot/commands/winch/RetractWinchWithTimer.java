package org.usfirst.frc.team4946.robot.commands.winch;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetractWinchWithTimer extends Command {

	private Timer m_winchTimer = new Timer();
	private double m_timeoutSeconds;

	public RetractWinchWithTimer(double timeoutSeconds) {
		requires(Robot.winchSubsystem);
		this.m_timeoutSeconds = timeoutSeconds;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_winchTimer.reset();
		m_winchTimer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.winchSubsystem.setMotor(-1.0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return m_winchTimer.get() > m_timeoutSeconds;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.winchSubsystem.stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.winchSubsystem.stopMotor();
	}
}