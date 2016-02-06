package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtendWinch extends Command {


	private Timer m_timer = new Timer();
	
    public ExtendWinch(boolean isExtend) {
        
    	requires(Robot.winchSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	m_timer.start();
    	m_timer.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.winchSubsystem.extendWinch();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	double elapsedTime =  m_timer.get(); 
    	if(elapsedTime<1) {
    		return false;
    	} else {
    		return true;
    	}
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winchSubsystem.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winchSubsystem.stopMotor();
    }
}
