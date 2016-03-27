package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchSubsystem extends Subsystem {

	public static double EXTEND_SPEED = 0.8;
	public static double RETRACT_SPEED = -0.5;

	
	private VictorSP m_leftWinchMotor = new VictorSP(RobotMap.PWM_VICTOR_SP_WINCH_LEFT);
	private VictorSP m_rightWinchMotor = new VictorSP(RobotMap.PWM_VICTOR_SP_WINCH_RIGHT);

	public void initDefaultCommand() {
	}

	public void setSpeed(double speed) {
		m_leftWinchMotor.set(speed);
		m_rightWinchMotor.set(-speed);
	}

}