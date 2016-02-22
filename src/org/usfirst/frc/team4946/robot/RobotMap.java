package org.usfirst.frc.team4946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int JOYSTICK_DRIVE_PORT = 0;
	public static final int JOYSTICK_OPERATOR_PORT = 1;

	// New naming convention: <Interface (eg. CAN, PWM, DIO, etc.)><Device type
	// (eg. Talon, Encoder, etc)><Channel Description>
	public static final int CAN_TALON_SHOOTER_LEFT = 0;
	public static final int CAN_TALON_SHOOTER_RIGHT = 1;
	public static final int CAN_TALON_ARM = 2;
	public static final int CAN_TALON_WINCH = 3;

	public static final int PWM_VICTOR_SP_FRONT_RIGHT_DRIVE = 0;
	public static final int PWM_VICTOR_SP_FRONT_LEFT_DRIVE = 2;
	public static final int PWM_VICTOR_SP_REAR_RIGHT_DRIVE = 1;
	public static final int PWM_VICTOR_SP_REAR_LEFT_DRIVE = 3;
	public static final int PWM_VICTOR_SP_INTAKE = 4;
	public static final int PWM_VICTOR_SP_FEEDER_LEFT = 9;
	public static final int PWM_VICTOR_SP_FEEDER_RIGHT = 8;

	public static final int ANALOG_GYRO = 1;
	public static final int ANALOG_POT = 2;

	public static final int DIO_ENCODER_DRIVETRAIN_RIGHT_A = 0;
	public static final int DIO_ENCODER_DRIVETRAIN_RIGHT_B = 1;
	public static final int DIO_ENCODER_DRIVETRAIN_LEFT_A = 2;
	public static final int DIO_ENCODER_DRIVETRAIN_LEFT_B = 3;
	public static final int DIO_COUNTER_SHOOTER_LEFT = 4;
	public static final int DIO_COUNTER_SHOOTER_RIGHT = 5;
	public static final int DIO_LIMIT_SWITCH_ARM_DOWN = 7;
	public static final int DIO_LIMIT_SWITCH_ARM_UP = 8;
	public static final int DIO_LIMIT_SWITCH_FEEDER = 9;

	// For Velocity Math
	public static final double MAX_RPM = 10400;
}
