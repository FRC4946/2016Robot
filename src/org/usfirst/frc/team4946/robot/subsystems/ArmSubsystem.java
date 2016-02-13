package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.ArmControlWithJoystick;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class ArmSubsystem extends Subsystem {

	private CANTalon m_armMotor = new CANTalon(RobotMap.CAN_TALON_ARM);
	private Potentiometer m_pot = new AnalogPotentiometer(RobotMap.ANALOG_POT, 320, 0);
	private PIDController m_armPID = new PIDController(0.0, 0.0, 0.0, m_pot, m_armMotor);

	public ArmSubsystem() {
		m_armPID.setContinuous(false);
	}

	public void setPIDEnabled(boolean isEnabled) {
		
		if (isEnabled == true) {
			m_armPID.enable();
		} else {
			m_armPID.disable();
		}
		
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArmControlWithJoystick());
	}

	public void setArmPoint() {
		//m_armPID.setSetpoint(34);
	}

	public double getArmPos() {
		return m_pot.get();
	}

	public void setArmSpeed(double speed) {
		m_armMotor.set(speed);
	}

	public void extendArm() {

		m_armMotor.set(0.75);

	}

	public void retractArm() {

		m_armMotor.set(-0.75);

	}

	public void stopMotor() {
		m_armMotor.set(0.0);
	}

}
