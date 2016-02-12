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
	
	public static final int FRONT_RIGHT_MOTOR_PORT = 0;
	public static final int FRONT_LEFT_MOTOR_PORT = 1;
	public static final int BACK_RIGHT_MOTOR_PORT = 2;
	public static final int BACK_LEFT_MOTOR_PORT = 3;
	
	
	
	public static final int JOYSTICK_PORT = 0;
	
	
	
	public static final int JOYSTICK_DRIVE_PORT = 0;
	public static final int JOYSTICK_CURVE_PORT= 1;
	public static final int JOYSTICK_THROTTLE_PORT = 2;
	
	public static final int JOYSTICK_BUTTON_PORT = 3;
	
	
	public static final int ANALOG_GYRO_PORT = 1;
	public static final int LEFT_ENCODER_CHANNEL_A = 2;
	public static final int LEFT_ENCODER_CHANNEL_B = 3;
	
	public static final int RIGHT_ENCODER_CHANNEL_A = 4;
	public static final int RIGHT_ENCODER_CHANNEL_B = 5;
	
}