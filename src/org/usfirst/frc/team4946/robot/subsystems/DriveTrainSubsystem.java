package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.DriveWithJoystickCommand;
import org.usfirst.frc.team4946.robot.util.SimplePIController;
import org.usfirst.frc.team4946.robot.util.VisionAnglePIDSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// add 4 encoders

	public VictorSP frontRightMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_FRONT_RIGHT_DRIVE);
	public VictorSP frontLeftMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_FRONT_LEFT_DRIVE);
	public VictorSP backRightMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_REAR_RIGHT_DRIVE);
	public VictorSP backLeftMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_REAR_LEFT_DRIVE);

	// public Encoder encoderRight = new
	// Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A,RobotMap.RIGHT_ENCODER_CHANNEL_B,false,
	// Encoder.EncodingType.k4X);
	// public Encoder encoderLeft = new
	// Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A,RobotMap.LEFT_ENCODER_CHANNEL_B,
	// false, Encoder.EncodingType.k4X);

	// public AnalogGyro driveTrainGyro = new AnalogGyro(RobotMap.ANALOG_GYRO);

	// PIDController gyroControl = new PIDController(0.1,0.001,0.0,
	// driveTrainGyro, backLeftMotor);

	public RobotDrive robotDrive = new RobotDrive(frontLeftMotor,
			backLeftMotor, frontRightMotor, backRightMotor);

	private SimplePIController pid = new SimplePIController(0.1, 0.0,
			new VisionAnglePIDSource());

	// Encoder may pulse 1000 times per revolution
	// Assuming the wheel is 6 inches circumference is 6*pi inches.
	// Each encoder pulse will be (0.006*pi) inches.
	double kDistancePerPulse = 0.006 * Math.PI;

	public DriveTrainSubsystem() {

		// driveTrainGyro.setSensitivity(0.007);

		// encoderRight.setDistancePerPulse(1.0);
		// encoderRight.setPIDSourceType(PIDSourceType.kRate);
		//
		//
		//
		// encoderLeft.setDistancePerPulse(1.0);
		// encoderLeft.setPIDSourceType(PIDSourceType.kRate);
		//

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

		// driveTrainGyro.reset();

		// encoderLeft.setDistancePerPulse(kDistancePerPulse);
		// encoderRight.setDistancePerPulse(kDistancePerPulse);

		setDefaultCommand(new DriveWithJoystickCommand());

	}

	public void drive(double drive, double curve) {

		robotDrive.arcadeDrive(drive, curve);

	}

	public void drive(double drive, double curve, double throttle) {

		throttle = (throttle - 1.0) / -2.0;

		drive *= (0.5 + (0.5 * throttle));
		curve *= (0.5 + (0.5 * throttle));

		robotDrive.arcadeDrive(drive, curve);

	}

	
	public double getPIDOutput(){
		return pid.getOutput();
	}
}
