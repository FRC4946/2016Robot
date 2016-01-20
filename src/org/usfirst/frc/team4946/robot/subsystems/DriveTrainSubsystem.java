package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	
	
	public  Victor m_frontRightMotor = new Victor(RobotMap.k_frontRightPort);
	public  Victor m_frontLeftMotor = new Victor(RobotMap.k_frontLeftPort);
	public  Victor m_backRightMotor = new Victor(RobotMap.k_backRightPort);
	public  Victor m_backLeftMotor = new Victor(RobotMap.k_backLeftPort);
	
	
	public RobotDrive m_robotDrive = new RobotDrive(m_frontRightMotor, m_frontLeftMotor, m_backRightMotor , m_backLeftMotor);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void drive(double x, double y){
    	
    	m_robotDrive.drive(x, y);
    	
    	
    }
    
    public void driveBackLeftMotor(double speed){
    	m_backLeftMotor.set(speed);
    	
    }
    
    public void driveBackRightMotor(double speed){
    	m_backRightMotor.set(speed);
    	
    }
    
    public void driveFrontRightMotor(double speed){
    	m_frontRightMotor.set(speed);
    	
    }
    
    public void driveFrontLeftMotor(double speed){
    	m_frontLeftMotor.set(speed);
    	
    }
    
    
    
}

