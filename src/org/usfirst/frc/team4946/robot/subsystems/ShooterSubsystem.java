package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    //Initialize the two motors used to shoot the ball
	CANTalon leftShooterTalon = new CANTalon(RobotMap.PWN_SHOOTER_CANTALON_LEFT);
    CANTalon rightShooterTalon = new CANTalon(RobotMap.PWN_SHOOTER_CANTALON_RIGHT);
	
	// Initialize your subsystem here
    double kP = 0.1; 
    double kI = 0.0;
    double kF = 0.0;
    
	double gravity = 9.8; //(m / s^2)
	double ballMass = 0.294835; //(kg)
	double ballCrossectionalArea = Math.PI*0.127*0.127; //(pi * radius in metres squared) 
	double airDensity = 1.255; //Approximate with a constant (kg/m^3)
	double shootingAngle = 60; //Degrees
	double shooterHeight = 0.1778; //(metres)
	double goalHeight = 2.3622; //(metres)
	double goalLength = 0.4064; //(metres)
  	double goalDistanceX; //Comes from Camera, how far we are from the goal
	double goalDistanceY = goalHeight - shooterHeight; //How much higher the goal is than the shooter is
	double dragCoefficient = 0.5; //(physics  u m )
	
	double drag; //approximate with a constant

	double initHorizontalVelocity;
	double initVerticalVelocity;
	double shooterSpeed;
	double minDistance;
	double maxDistance;
	
	double outputVoltage;

	boolean canShoot;  
	
	public ShooterSubsystem(){
		
	}
    
    public void shootBall(double fRPM) {
    	
    	/**
    	 * Sets motor speed needed to shoot ball
    	 */
        
    	//Sets the 6 constants used in the PID Controller 
		  leftShooterTalon.setPID(kP, kI, 0, kF, 0, 12, 0);
		  rightShooterTalon.setPID(kP, kI, 0, kF, 0, 12, 0);
		//Sets the RPM of that the motor should be at
		  leftShooterTalon.setSetpoint(-fRPM);
		  rightShooterTalon.setSetpoint(fRPM);

    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public double calcSpeedVel(){
    	
    	/**
    	 * Calculates the initial velocity needed to shoot the ball into the goal
    	 */
    	
    	double d = goalDistanceX;
    	double phi = shootingAngle;
    	double h = goalDistanceY;
    	
    	/*	See dis:
    		https://www.desmos.com/calculator/0x1d4togt0
    	 */
    	
    	double initVel = Math.sqrt((4.905*d*d)/(Math.cos(phi)*Math.cos(phi)*(d*Math.tan(phi)-h)));
    	
    	//4.905 is half of gravity
    	
    	return initVel;
    	
    }
    
    public double calcSpeedRPM(){
    	
    	/**
    	 * Calculates RPM needed to shoot ball into goal
    	 */
    	
    	double d = goalDistanceX;
    	double phi = shootingAngle;
    	double h = goalDistanceY;
    	
    	double initVel = Math.sqrt((4.905*d*d)/(Math.cos(phi)*Math.cos(phi)*(d*Math.tan(phi)-h)));
    	
    	double RPM = initVel*(60.0/0.1016);
    	//60 means 
    	//0.1016 means 
    	
    	return RPM;
    	
    }
}
