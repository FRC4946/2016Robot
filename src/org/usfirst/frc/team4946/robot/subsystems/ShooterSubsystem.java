package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.shooter.RollerSpeedWithJoystickNoPID;
import org.usfirst.frc.team4946.robot.util.RateCounter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	private double setSpeed = 2500;

	// Initialize the two motors used to shoot the ball
	// Change names
	private CANTalon m_leftShooterTalon = new CANTalon(
			RobotMap.CAN_TALON_SHOOTER_LEFT);
	private CANTalon m_rightShooterTalon = new CANTalon(
			RobotMap.CAN_TALON_SHOOTER_RIGHT);

	private RateCounter m_leftCounter = new RateCounter(
			RobotMap.DIO_COUNTER_SHOOTER_LEFT);
	private RateCounter m_rightCounter = new RateCounter(
			RobotMap.DIO_COUNTER_SHOOTER_RIGHT);

	private PIDController m_leftPidController;
	private PIDController m_rightPidController;

	// Initialize your subsystem here
	// Change to actual values
	private double kP = 1.0;
	private double kI = 0.0;
	private double kD = 0.0;
	private double kF = 0.5;

	public ShooterSubsystem() {

		// // Sets the 6 constants used in the PID Controller
		// m_leftShooterTalon.setExpiration(0.5);
		// m_rightShooterTalon.setExpiration(0.5);

		m_leftPidController = new PIDController(kP, kI, kD, /* kF, */
		m_leftCounter, m_leftShooterTalon);
		m_rightPidController = new PIDController(kP, kI, kD/* , kF */,
				m_rightCounter, m_rightShooterTalon);

		// m_leftPidController.setAbsoluteTolerance(25);
		// m_rightPidController.setAbsoluteTolerance(25);

		m_leftCounter.setPIDSourceType(PIDSourceType.kRate);
		m_rightCounter.setPIDSourceType(PIDSourceType.kRate);

		m_leftPidController.setInputRange(0.0, 7000.0);
		m_rightPidController.setInputRange(0.0, 7000.0);
		m_leftPidController.setOutputRange(-1.0, 1.0);
		m_rightPidController.setOutputRange(-1.0, 1.0);

		LiveWindow.addActuator("Shooter", "Left PID", m_leftPidController);
		LiveWindow.addActuator("Shooter", "Right PID", m_rightPidController);

		LiveWindow.addSensor("Shooter", "LEft", m_leftCounter);
		LiveWindow.addSensor("Shooter", "Right", m_rightCounter);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new RollerSpeedWithJoystickNoPID());
	}

	public void setPIDEnabled(boolean isEnabled) {
		if (isEnabled) {
			m_leftPidController.enable();
			m_rightPidController.enable();
		} else {
			m_leftPidController.disable();
			m_rightPidController.disable();
		}
	}

	public void setVelocityPID(double rpm) {
		// Sets the RPM of that the motor should be at
		m_leftShooterTalon.setSetpoint(-rpm);
		m_rightShooterTalon.setSetpoint(rpm);

		setPIDEnabled(true);

		SmartDashboard.putNumber("Set RPM", rpm);

		m_leftPidController.setSetpoint(rpm);
		m_rightPidController.setSetpoint(rpm);

		SmartDashboard.putNumber("Left Shooter Error",
				m_leftPidController.getError());
		SmartDashboard.putNumber("Right Shooter Error",
				m_rightPidController.getError());

		SmartDashboard.putNumber("Left Shooter RPM", m_leftCounter.getRPM());
		SmartDashboard.putNumber("Right Shooter RPM", m_rightCounter.getRPM());
		SmartDashboard.putNumber("Left Shooter Out", m_leftPidController.get());
		SmartDashboard.putNumber("Right Shooter Out",
				m_rightPidController.get());

	}

	public double leftSpeedBangBang() {
		// double error = setSpeed - ((m_leftCounter.getRPM() +
		// m_rightCounter.getRPM())/2.0);

		// double output = error * 0.001

		double curSpeed = m_leftCounter.getRPM();

		if (curSpeed > setSpeed) {
			return 0.0;
		} else {
			return 0.3 + (setSpeed / 7000) * 0.7;
		}
	}

	public double rightSpeedBangBang() {
		// double error = setSpeed - ((m_leftCounter.getRPM() +
		// m_rightCounter.getRPM())/2.0);

		// double output = error * 0.001

		double curSpeed = m_rightCounter.getRPM();

		if (curSpeed > setSpeed) {
			return 0.0;
		} else {
			return 0.3 + (setSpeed / 7000) * 0.7;
		}
	}

	public void updateSpeedBangBang() {
		m_leftShooterTalon.set(leftSpeedBangBang());
		m_rightShooterTalon.set(rightSpeedBangBang());

		SmartDashboard.putNumber("Left Shooter RPM", m_leftCounter.getRPM());
		SmartDashboard.putNumber("Right Shooter RPM", m_rightCounter.getRPM());
	}

	public void setVelocityNoPID(double joyVel) {
		// In case the vision system breaks down, the driver may use the
		// joystick to control the shooter provided that a button is held down

		// Presently, this is the default command as the encoders and PID aren't
		// set up

		setPIDEnabled(false);

		m_leftShooterTalon.set(-joyVel);
		m_rightShooterTalon.set(joyVel);

		SmartDashboard.putNumber("Left Shooter RPM", m_leftCounter.getRPM());
		SmartDashboard.putNumber("Right Shooter RPM", m_rightCounter.getRPM());

	}

	public double getLeftOutput() {
		return m_leftPidController.get();
	}

	public double getRightOutput() {
		return m_rightPidController.get();
	}

	public boolean isAtSpeed() {
		if (!m_leftPidController.isEnabled()
				|| !m_rightPidController.isEnabled()) {
			return true;
		}

		return m_leftPidController.onTarget()
				&& m_rightPidController.onTarget();
	}

}
