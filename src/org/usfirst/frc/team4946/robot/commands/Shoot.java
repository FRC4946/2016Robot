package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_shooterPIDSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//All the variables are here, now we just have to figure out how to use them
    	
    	double gravity = 9.8; //(m / s^2)
    	double ballMass = 0.294835; //(kg)
    	double ballCrossectionalArea = Math.PI*0.127*0.127; //(pi * radius in metres squared) 
    	double airDensity = 1.255; //Approximate with a constant (kg/m^3)
    	double shootingAngle = 30; //Degrees
    	double goalDistance; //Comes from Camera 
    	double goalHeight = 0.4064; //(metres)
    	double dragCoefficient = 0.5; //(physics  u m )
    	
    	double drag; //approximate with a constant
    
    	double initHorizontalVelocity;
    	double initVerticalVelocity;
    	double shooterSpeed;
    	double minDistance;
    	double maxDistance;
    	
    	double outputVoltage;

    	boolean canShoot; 
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Set the setpoint of the PIDShooterSubsystem
    	
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
