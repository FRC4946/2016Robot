package org.usfirst.frc.team4946.robot.commands.drivetrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MaintainOrientation extends Command {

	public MaintainOrientation() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrainSubsystem.resetGyro();
		Robot.driveTrainSubsystem.setSetpoint(0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Gyro", Robot.driveTrainSubsystem.getGyro());
		
		
		Robot.driveTrainSubsystem.drive(0.0, Robot.driveTrainSubsystem.getTurnPIDOutput());
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
