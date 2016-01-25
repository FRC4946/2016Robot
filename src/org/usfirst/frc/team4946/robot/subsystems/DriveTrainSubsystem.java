package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	
	
	public Victor m_frontRightMotor = new Victor(RobotMap.k_frontRightMotorPort);
	public Victor m_frontLeftMotor = new Victor(RobotMap.k_frontLeftMotorPort);
	public Victor m_backRightMotor = new Victor(RobotMap.k_backRightMotorPort);
	public Victor m_backLeftMotor = new Victor(RobotMap.k_backLeftMotorPort);
	
	public Victor m_strafeMotor = new Victor(RobotMap.k_strafeMotorPort);
	
	public RobotDrive m_robotDrive = new RobotDrive(m_frontRightMotor, m_frontLeftMotor, m_backRightMotor , m_backLeftMotor);
	
	public Encoder m_encoder = new Encoder(RobotMap.k_encoder1, RobotMap.k_encoder2);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void drive(double x, double y){
    	
    	m_robotDrive.arcadeDrive(x, y);
    	
    	
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
    
    public void strafe(double speed){
    	m_strafeMotor.set(speed);
    }
    
}

