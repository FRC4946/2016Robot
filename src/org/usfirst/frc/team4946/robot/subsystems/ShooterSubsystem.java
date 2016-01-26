package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    //Initialize the two motors used to shoot the ball
	CANTalon m_LeftShooterTalon = new CANTalon(RobotMap.k_PWN_SHOOTER_CANTALON_LEFT);
    CANTalon m_RightShooterTalon = new CANTalon(RobotMap.k_PWN_SHOOTER_CANTALON_RIGHT);
	
	// Initialize your subsystem here
    private static double kP = 0.1; 
    private static double kI = 0.0;
    private static double kF = 0.0;
    
   	double gravity = 9.8; //(m / s^2)
	double ballMass = 0.294835; //(kg)
	double ballCrossectionalArea = Math.PI*0.127*0.127; //(pi * radius in metres squared) 
	double airDensity = 1.255; //Approximate with a constant (kg/m^3)
	double shootingAngle = 60.0; //Degrees
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
    
    public ShooterSubsystem() {
        
		m_LeftShooterTalon.setPID(kP, kI, 0, kF, 0, 12, 0);
		m_LeftShooterTalon.setSetpoint(6543);

    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double calcSpeedRPM(){
    	
    	return 0.0;
    	
    }
    
    public double calcSpeedVel(){
    	
    	
    	return 0.0;
    	
    }
}
