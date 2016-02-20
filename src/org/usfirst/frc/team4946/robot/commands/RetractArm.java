package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class RetractArm extends Command {

	private Timer m_timer = new Timer();
	
    public RetractArm(boolean isExtend) {
        
    	requires(Robot.armSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	m_timer.start();
    	m_timer.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.armSubsystem.retractArm();
    	
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}