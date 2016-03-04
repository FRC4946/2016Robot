package org.usfirst.frc.team4946.robot.commands.drivetrain;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReturnToZeroAngle extends Command {

    public ReturnToZeroAngle() {
        requires(Robot.driveTrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.driveTrainSubsystem.setSetpoint(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Turn with PID
		Robot.driveTrainSubsystem.drive(0.0,
				Robot.driveTrainSubsystem.getTurnPIDOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return Math.abs( Robot.driveTrainSubsystem.getGyro() ) <= 5;
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
