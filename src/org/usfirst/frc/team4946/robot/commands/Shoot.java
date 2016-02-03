package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	
	//Gets the needed initial velocity and RPM
	private boolean setpointReached = false;
	private double fRPM = Robot.shooterPIDSubsystem.calcSpeedRPM();
	private double fVel = Robot.shooterPIDSubsystem.calcSpeedVel();
	//Vars used to turn motors off some seconds after shooting
	Timer motorTimer = new Timer();
	private double curVelL = Robot.shooterPIDSubsystem.getLVel();
	private double curVelR = Robot.shooterPIDSubsystem.getRVel();
	private double time;
	
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterPIDSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motorTimer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Set the setpoint of the ShooterSubsystem
    	Robot.shooterPIDSubsystem.shootBall(fRPM);
    	
    	//Sets the shooting CANTalons to 0 3 seconds after they've reached the setpoint
    	if(curVelL >= fVel & curVelR >= fVel){
    		setpointReached = true;
    		motorTimer.start();
    	}
    	
    	while(setpointReached == true){
    		
    		time = motorTimer.get();
    		
    		if(time > 3.0){
    			Robot.shooterPIDSubsystem.shootBall(0);
    			setpointReached = false;
    		} 
    	}
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
