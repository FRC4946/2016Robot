package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToAngle extends Command {

	public double m_angle = 0.0;
	public double curAngle;
	
    public RotateToAngle(double newAngle) {
        requires(Robot.driveTrainSubsystem);
        m_angle = newAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrainSubsystem.resetGyro();
    	Robot.driveTrainSubsystem.setSetpoint(m_angle);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.drive(0.0, Robot.driveTrainSubsystem.getTurnPIDOutput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Robot.m_driveTrain.m_gyroPIDController.onTarget();
    	return Math.abs(curAngle-m_angle) <= 7.5;
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
