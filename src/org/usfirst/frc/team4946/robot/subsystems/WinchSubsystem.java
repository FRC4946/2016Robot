package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchSubsystem extends Subsystem {

	private CANTalon m_winchMotor = new CANTalon(RobotMap.CAN_TALON_WINCH);

	public void initDefaultCommand() {
	}

	public void setMotor(double speed) {
		m_winchMotor.set(speed);
	}

	public void stopMotor() {
		m_winchMotor.set(0.0);
	}

}