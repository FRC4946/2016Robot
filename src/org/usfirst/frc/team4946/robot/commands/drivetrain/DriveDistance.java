package org.usfirst.frc.team4946.robot.commands.drivetrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {

	private double m_distanceInches;
	private double m_speed;
	private double m_turn;

	public DriveDistance(double distanceInches, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrainSubsystem);
		m_distanceInches = distanceInches;
		m_speed = speed;
		Robot.driveTrainSubsystem.resetEncoders();
		m_turn = 0;
	}

	public DriveDistance(double distanceInches, double speed, double turn) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrainSubsystem);
		m_distanceInches = distanceInches;
		m_speed = speed;
		m_turn = turn;
		Robot.driveTrainSubsystem.resetEncoders();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrainSubsystem.resetEncoders();
		SmartDashboard.putNumber("Dist",
				Robot.driveTrainSubsystem.getDistance());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrainSubsystem.drive(m_speed, m_turn);
		System.out.println(m_speed);
		SmartDashboard.putNumber("Dist",
				Robot.driveTrainSubsystem.getDistance());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return Math.abs(Robot.driveTrainSubsystem.getDistance()) >= (m_distanceInches - (m_speed * 3.0));
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("done");
		Robot.driveTrainSubsystem.drive(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
