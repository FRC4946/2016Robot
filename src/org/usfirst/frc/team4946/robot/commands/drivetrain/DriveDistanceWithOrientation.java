package org.usfirst.frc.team4946.robot.commands.drivetrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceWithOrientation extends Command {

	private double m_distanceInches;
	private double m_speed;
	
    public DriveDistanceWithOrientation(double distanceInches, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrainSubsystem);
		m_distanceInches = distanceInches;
		m_speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrainSubsystem.resetEncoders();
		Robot.driveTrainSubsystem.resetGyro();
		Robot.driveTrainSubsystem.setSetpoint(Robot.driveTrainSubsystem.getGyro());
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.driveTrainSubsystem.getDistance()) >= (m_distanceInches - (m_speed * 3.0))){
    		m_speed = 0.0;
    	}
    	
    	Robot.driveTrainSubsystem.drive(m_speed, Robot.driveTrainSubsystem.getTurnPIDOutput());
    	
		SmartDashboard.putNumber("Dist",
				Robot.driveTrainSubsystem.getDistance());
		SmartDashboard.putNumber("Gyro", Robot.driveTrainSubsystem.getGyro());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return (m_speed == 0.0) && Robot.driveTrainSubsystem.isPIDOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSubsystem.drive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
