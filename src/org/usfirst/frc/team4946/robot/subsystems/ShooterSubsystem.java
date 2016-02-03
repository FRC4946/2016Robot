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
    private double kP = 0.1; 
    private double kI = 0.0;
    private double kF = 0.0;
	    
    private double gravity = 9.8; //(m / s^2)
    private double ballMass = 0.294835; //(kg)
    private double ballCrossectionalArea = Math.PI*0.127*0.127; //(pi * radius in metres squared) 
    private double airDensity = 1.255; //Approximate with a constant (kg/m^3)
    private double shootingAngle = 60; //Degrees
    private double shooterHeight = 0.1778; //(metres)
    private double goalHeight = 2.3622; //(metres)
    private double goalLength = 0.4064; //(metres)
    private double goalDistanceX; //Comes from Camera, how far we are from the goal
    private double goalDistanceY = goalHeight - shooterHeight; //How much higher the goal is than the shooter is
    private double dragCoefficient = 0.5; //(physics  u m )
    private double phi = Math.toRadians(shootingAngle);
		
    private double drag; //approximate with a constant
	
    private double initHorizontalVelocity;
    private double initVerticalVelocity;
    private double shooterSpeed;
    private double minDistance;
    private double maxDistance;
    private double curVelL;
    private double curVelR;
		
    private double outputVoltage;
	
    private boolean canShoot;  
	
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
    	
    	/*	See dis:
    		https://www.desmos.com/calculator/0x1d4togt0
    	 */
    	
    	double initVel = Math.sqrt((9.8*goalDistanceX*goalDistanceX)/(2*(Math.cos(phi)*Math.cos(phi)*(goalDistanceX*Math.tan(phi)-goalDistanceY))));
    	
    	//4.905 is half of gravity
    	
    	return initVel;
    	
    }
    
    public double calcSpeedRPM(){
    	
    	/**
    	 * Calculates RPM needed to shoot ball into goal
    	 */
    	
    	double initVel = Math.sqrt((9.8*goalDistanceX*goalDistanceX)/((Math.cos(phi)*Math.cos(phi)*(goalDistanceX*Math.tan(phi)-goalDistanceY))));
    	
    	double RPM = initVel*(60.0/0.1016);
    	//60 means 
    	//0.1016 means 
    	
    	return RPM;
    	
    }
    
    public double getLVel(){
    	
    	curVelL = leftShooterTalon.getEncVelocity();
    	return curVelL;
    }
    
    public double getRVel(){
    	
    	curVelR = rightShooterTalon.getEncVelocity();
    	return curVelR;
    }
}
