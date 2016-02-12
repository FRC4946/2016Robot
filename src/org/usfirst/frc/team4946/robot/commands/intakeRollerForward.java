package org.usfirst.frc.team4946.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4946.robot.Robot;
/**
 *
 */
public class intakeRollerForward extends Command {
	
	int forwardCounter = 0;

    public intakeRollerForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_intakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	forwardCounter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_intakeSubsystem.setInRollerSpeed(1.0);
    	forwardCounter ++;
    	   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(forwardCounter == 1) {
    		return true;
    	} else {
        return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
