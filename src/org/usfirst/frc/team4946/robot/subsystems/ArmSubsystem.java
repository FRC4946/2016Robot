package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmSubsystem extends Subsystem {
    
    private CANTalon m_armMotor = new CANTalon (RobotMap.k_ARM_CANTalon);

    public void initDefaultCommand() {
      
        
    }
    
    public void extendArm(){
    	
    	m_armMotor.set(0.5);
    	
    }
    
    public void retractArm(){
    	
    	m_armMotor.set(-0.5);
    	
    }
    
    public void stopMotor(){
    	m_armMotor.set(0.0);
    }
    
}


