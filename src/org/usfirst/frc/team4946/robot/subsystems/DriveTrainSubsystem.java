package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.DriveTrainCommands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.AnalogGyro;
/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	//add 4 encoders
	
	
	public Victor frontRightMotor = new Victor(RobotMap.FRONT_RIGHT_MOTOR_PORT);
	public  Victor frontLeftMotor = new Victor(RobotMap.FRONT_LEFT_MOTOR_PORT);
	public  Victor backRightMotor = new Victor(RobotMap.BACK_RIGHT_MOTOR_PORT);
	public  Victor backLeftMotor = new Victor(RobotMap.BACK_LEFT_MOTOR_PORT);
	
	
	
	public Encoder encoderRight = new Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A,RobotMap.RIGHT_ENCODER_CHANNEL_B,false, Encoder.EncodingType.k4X);
	public Encoder encoderLeft = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A,RobotMap.LEFT_ENCODER_CHANNEL_B, false, Encoder.EncodingType.k4X);
	
	
	
	
	public AnalogGyro driveTrainGyro = new AnalogGyro(RobotMap.ANALOG_GYRO_PORT);
	
	
	PIDController gyroControl = new PIDController(0.1,0.001,0.0, driveTrainGyro, backLeftMotor);
	
	
	public RobotDrive robotDrive = new RobotDrive(frontRightMotor, frontLeftMotor, backRightMotor , backLeftMotor);
	
	
	//Encoder may pulse 1000 times per revolution
	//Assuming the wheel is 6 inches circumference is 6*pi inches.
	//Each encoder pulse will be (0.006*pi) inches.
	double 	kDistancePerPulse = 0.006*Math.PI;
	
	
	
	
	
	
	
	
	
	
	
	public DriveTrainSubsystem(){
		
			
		driveTrainGyro.setSensitivity(0.007);
		
		
		encoderRight.setDistancePerPulse(1.0);
		encoderRight.setPIDSourceType(PIDSourceType.kRate);
		
		
		
		encoderLeft.setDistancePerPulse(1.0);
		encoderLeft.setPIDSourceType(PIDSourceType.kRate);
		
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	driveTrainGyro.reset();
    	
    	encoderLeft.setDistancePerPulse(kDistancePerPulse);
    	encoderRight.setDistancePerPulse(kDistancePerPulse);
    	
    	setDefaultCommand(new DriveWithJoystickCommand());
    	
    	
    	
    }
    
    
    
    public void drive(double drive, double curve){
    	
    	
    	robotDrive.arcadeDrive(drive, curve);
    	
    }
    
    
    public void drive(double drive, double curve, double throttle){
    	
    	
    	
    	throttle = (throttle + 1.0)/2.0;
    	
    	drive *= 0.5 + (0.5*throttle);
    	
    	curve *= 0.5 + (0.5*throttle);
    	
    	
    	
    	robotDrive.arcadeDrive(drive, curve);
    	
    }
    
    public void driveBackLeftMotor(double speed){
    	backLeftMotor.set(speed);
    	
    }
    
    public void driveBackRightMotor(double speed){
    	backRightMotor.set(speed);
    	
    }
    
    public void driveFrontRightMotor(double speed){
    	frontRightMotor.set(speed);
    	
    }
    
    public void driveFrontLeftMotor(double speed){
    	frontLeftMotor.set(speed);
    	
    }
    
    
    
    
    
}

