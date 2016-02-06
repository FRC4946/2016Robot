package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchSubsystem extends Subsystem {
    
    private CANTalon m_winchMotor = new CANTalon (RobotMap.k_WINCH_CANTalon);

    public void initDefaultCommand() {
      
        
    }
    
    public void extendWinch(){
    	
    	m_winchMotor.set(0.5);
    	
    }
    
    public void retractWinch(){
    	
    	m_winchMotor.set(-0.5);
    	
    }
    
    public void stopMotor(){
    	m_winchMotor.set(0.0);
    }
    
}