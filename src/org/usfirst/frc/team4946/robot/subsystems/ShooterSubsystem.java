package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.shooter.RollerSpeedWithJoystickNoPID;
import org.usfirst.frc.team4946.robot.util.RateCounter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

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
	private double kP = 0.1;
	private double kI = 0.0;
	private double kD = 0.0;
	private double kF = 0.0;
	
	public ShooterSubsystem() {

		// // Sets the 6 constants used in the PID Controller
		// m_leftShooterTalon.setPID(kP, kI, 0, kF, 0, 12, 0);
		// m_rightShooterTalon.setPID(kP, kI, 0, kF, 0, 12, 0);

		m_leftPidController = new PIDController(kP, kI, kD, kF, m_leftCounter,
				m_leftShooterTalon);
		m_rightPidController = new PIDController(kP, kI, kD, kF,
				m_rightCounter, m_rightShooterTalon);

		m_leftPidController.setAbsoluteTolerance(25);
		m_rightPidController.setAbsoluteTolerance(25);
		
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
		// m_leftShooterTalon.setSetpoint(-fRPM);
		// m_rightShooterTalon.setSetpoint(fRPM);

		setPIDEnabled(true);

		m_leftPidController.setSetpoint(rpm);
		m_rightPidController.setSetpoint(rpm);
	}

	public void setVelocityNoPID(double joyVel) {
		// In case the vision system breaks down, the driver may use the
		// joystick to control the shooter provided that a button is held down

		// Presently, this is the default command as the encoders and PID aren't
		// set up

		setPIDEnabled(false);

		m_leftShooterTalon.set(-joyVel);
		m_rightShooterTalon.set(joyVel);
	}

	public boolean isAtSpeed() {
		return m_leftPidController.onTarget()
				&& m_rightPidController.onTarget();
	}

}
