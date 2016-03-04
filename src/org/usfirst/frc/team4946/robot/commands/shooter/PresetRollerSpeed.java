package org.usfirst.frc.team4946.robot.commands.shooter;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PresetRollerSpeed extends Command {

	int m_percent = 0;
	
    public PresetRollerSpeed(int percent) {
        // Use requires() here to declare subsystem dependencies
          requires(Robot.shooterSubsystem);
          m_percent = percent;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.shooterSubsystem.setVelocityNoPID(m_percent);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.shooterSubsystem.setVelocityNoPID(m_percent);
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
