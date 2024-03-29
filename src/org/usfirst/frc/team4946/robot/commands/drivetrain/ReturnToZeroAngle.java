package org.usfirst.frc.team4946.robot.commands.drivetrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReturnToZeroAngle extends Command {

	double m_angle = 0;
	
    public ReturnToZeroAngle() {
        requires(Robot.driveTrainSubsystem);
        m_angle = 0;
    }
    
    public ReturnToZeroAngle(double angle) {
        requires(Robot.driveTrainSubsystem);
        m_angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.driveTrainSubsystem.setSetpoint(m_angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Turn with PID
		Robot.driveTrainSubsystem.drive(0.0,
				Robot.driveTrainSubsystem.getTurnPIDOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
//		return Math.abs( Robot.driveTrainSubsystem.getGyro() ) <= 5;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.driveTrainSubsystem.drive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
