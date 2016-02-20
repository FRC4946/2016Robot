package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *	
 */
public class DriveWithJoystickCommand extends Command {

	// private Gyro m_gyro;

	public DriveWithJoystickCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.driveTrainSubsystem);
		// m_gyro = Robot.driveTrainSubsystem.driveTrainGyro;

	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Joystick stick = Robot.oi.getDriveStick();

		double drive = -1 * stick.getRawAxis(1);
		double curve = -1 * stick.getRawAxis(0);
		double throttle = stick.getRawAxis(3);
		Robot.driveTrainSubsystem.drive(drive, curve, throttle);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {

		Robot.driveTrainSubsystem.drive(0.0, 0.0, 0.0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

		Robot.driveTrainSubsystem.drive(0.0, 0.0, 0.0);

	}
}
