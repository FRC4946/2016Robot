package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.Shoot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    //Initialize the two motors used to shoot the ball
	//Change names
	CANTalon leftShooterTalon = new CANTalon(RobotMap.CAN_TALON_SHOOTER_LEFT);
    CANTalon rightShooterTalon = new CANTalon(RobotMap.CAN_TALON_SHOOTER_RIGHT);
	
	// Initialize your subsystem here
    //Change to actual values 
    private double kP = 0.1; 
    private double kI = 0.0;
    private double kF = 0.0;
	
	public ShooterSubsystem(){
		
    	//Sets the 6 constants used in the PID Controller 
		  leftShooterTalon.setPID(kP, kI, 0, kF, 0, 12, 0);
		  rightShooterTalon.setPID(kP, kI, 0, kF, 0, 12, 0);
		  
	}
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Shoot());
    }
    
    
    public double getVel(){
    	
    	//Get values from vision ;-;
    	return 0.0;
    }
    

    public void setVelocity(double fRPM) {
  	  
		//Sets the RPM of that the motor should be at
		  leftShooterTalon.setSetpoint(-fRPM);
		  rightShooterTalon.setSetpoint(fRPM);

    }
    
    public void setVelocityJoystick(double joyVel){
    	//In case the vision system breaks down, the driver may use the joystick to control the shooter provided that a button is held down
    	leftShooterTalon.set(-joyVel);
    	rightShooterTalon.set(joyVel);
    }
    
}
