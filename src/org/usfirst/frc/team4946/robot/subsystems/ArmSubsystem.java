package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.arm.ArmControlWithJoystick;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmSubsystem extends Subsystem {

	private CANTalon m_armMotor = new CANTalon(RobotMap.CAN_TALON_ARM);
	private DigitalInput m_limitDown = new DigitalInput(
			RobotMap.DIO_LIMIT_SWITCH_ARM_DOWN);
	private DigitalInput m_limitUp = new DigitalInput(
			RobotMap.DIO_LIMIT_SWITCH_ARM_UP);
	private Potentiometer m_pot = new AnalogPotentiometer(RobotMap.ANALOG_POT,
			320, 0);
	private PIDController m_armPID = new PIDController(0.0, 0.0, 0.0, m_pot,
			m_armMotor);

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
		// m_armPID.setSetpoint(34);
	}

	public double getArmPos() {
		return m_pot.get();
	}

	public void setArmSpeed(double speed) {
		if (!m_limitDown.get() && speed > 0) {
			speed = -0.01;
		} else if (!m_limitUp.get() && speed < 0) {
			speed = 0.01;
		}

		m_armMotor.set(speed);

		SmartDashboard.putNumber("Arm Speed", speed);
		SmartDashboard.putBoolean("Arm Is Down", !m_limitDown.get());
		SmartDashboard.putBoolean("Arm Is Up", !m_limitUp.get());
	}

	public boolean getArmIsUpright() {
		return !m_limitUp.get();
	}

	public boolean getArmIsDown() {
		return !m_limitDown.get();
	}
}
