package org.usfirst.frc.team4946.robot.commands.drivetrain;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.subsystems.Vision2;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToFaceGoal extends Command {

	private double m_lastAngle;
//	private boolean hasFreshVal = false;

	public TurnToFaceGoal() {
		requires(Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_lastAngle = Vision2.getAngle();
		Robot.driveTrainSubsystem.resetGyro();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (m_lastAngle != (m_lastAngle = Vision2.getAngle())) {
			Robot.driveTrainSubsystem.setSetpoint(m_lastAngle);
			Robot.driveTrainSubsystem.resetGyro();
			SmartDashboard.putNumber("Setpoint", m_lastAngle);
		}

		if (Vision2.getDistInches() == -1.0) {
			return;
		}


		double turn = Robot.driveTrainSubsystem.getTurnPIDOutput();

		SmartDashboard.putNumber("output", turn);
		SmartDashboard.putNumber("gyro", Robot.driveTrainSubsystem.getGyro());
		SmartDashboard.putNumber("error", Robot.driveTrainSubsystem.getError());

		turn = Math.copySign(Math.abs(turn) + 0.4, turn);

		Robot.driveTrainSubsystem.drive(0.0, turn);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.driveTrainSubsystem.isPIDOnTarget();
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