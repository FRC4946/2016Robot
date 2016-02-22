package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollerSpeedWithJoystickNoPID extends Command {

	double m_speed = 0.0;
	boolean m_usesSetSpeed = false;

	public RollerSpeedWithJoystickNoPID(double speed) {
		requires(Robot.shooterSubsystem);
		m_speed = speed;
		m_usesSetSpeed = true;
	}

	public RollerSpeedWithJoystickNoPID() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooterSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (m_usesSetSpeed) {
			Robot.shooterSubsystem.setVelocityNoPID(m_speed);
		} else {
			double speed = Robot.oi.getOperatorStick().getRawAxis(3);
			speed = (-speed + 1.0) / 2.0;
			Robot.shooterSubsystem.setVelocityNoPID(speed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
