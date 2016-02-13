package org.usfirst.frc.team4946.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public static final int JOYSTICK_DRIVE_PORT = 0;
	public static final int JOYSTICK_OPERATOR_PORT = 1;

	// New naming convention: <Interface (eg. CAN, PWM, DIO, etc.)><Device type
	// (eg. Talon, Encoder, etc)><Channel Description>
	public static final int CAN_TALON_SHOOTER_LEFT = 0;
	public static final int CAN_TALON_SHOOTER_RIGHT = 1;
	public static final int CAN_TALON_ARM = 2;
	public static final int CAN_TALON_WINCH = 3;
	public static final int PWM_VICTOR_SP_FRONT_RIGHT_DRIVE = 0;
	public static final int PWM_VICTOR_SP_FRONT_LEFT_DRIVE = 1+4;
	public static final int PWM_VICTOR_SP_REAR_RIGHT_DRIVE = 2+4;
	public static final int PWM_VICTOR_SP_REAR_LEFT_DRIVE = 3+4;
	public static final int PWM_VICTOR_SP_INTAKE = 3;
	public static final int PWM_VICTOR_SP_FEEDER_LEFT = 2;
	public static final int PWM_VICTOR_SP_FEEDER_RIGHT = 1;

	public static final int ANALOG_GYRO = 1;
	public static final int ANALOG_POT = 2;
	public static final int LEFT_ENCODER_CHANNEL_A = 2;
	public static final int LEFT_ENCODER_CHANNEL_B = 3;

	public static final int RIGHT_ENCODER_CHANNEL_A = 4;
	public static final int RIGHT_ENCODER_CHANNEL_B = 5;

}
