package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveWithJoystickCommand;
import org.usfirst.frc.team4946.robot.util.NewVisionAnglePIDSource;
import org.usfirst.frc.team4946.robot.util.SimplePIController;
import org.usfirst.frc.team4946.robot.util.VisionAnglePIDSource;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

	// Motors and RobotDrive
	private VictorSP frontRightMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_FRONT_RIGHT_DRIVE);
	private VictorSP frontLeftMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_FRONT_LEFT_DRIVE);
	private VictorSP backRightMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_REAR_RIGHT_DRIVE);
	private VictorSP backLeftMotor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_REAR_LEFT_DRIVE);
	private RobotDrive robotDrive = new RobotDrive(frontLeftMotor,
			backLeftMotor, frontRightMotor, backRightMotor);

	// Encoders
	private Encoder encoderRight = new Encoder(
			RobotMap.DIO_ENCODER_DRIVETRAIN_RIGHT_A,
			RobotMap.DIO_ENCODER_DRIVETRAIN_RIGHT_B, false,
			Encoder.EncodingType.k4X);
	private Encoder encoderLeft = new Encoder(
			RobotMap.DIO_ENCODER_DRIVETRAIN_LEFT_A,
			RobotMap.DIO_ENCODER_DRIVETRAIN_LEFT_B, false,
			Encoder.EncodingType.k4X);

	// Gyroscope
	private AnalogGyro gyro = new AnalogGyro(RobotMap.ANALOG_GYRO);

	// PID stuff
	private VisionAnglePIDSource turnPidSource = new VisionAnglePIDSource(
			encoderLeft, encoderRight);
	private NewVisionAnglePIDSource newTurnPidSource = new NewVisionAnglePIDSource(
			gyro);
	private SimplePIController turnPid;
//	private PIDController pid;
//	private PIDController pid2;

	/**
	 * CONSTRUCTOR
	 */
	public DriveTrainSubsystem() {
		Robot.networkTable.addTableListener("FINAL_ANGLE_TO_GOAL",
				turnPidSource, false);

		robotDrive.setExpiration(0.5);
		robotDrive.setSafetyEnabled(false);

		double wheelCirc = 8.0 * Math.PI;
		double pulsesPerRevolution = 250.0 * 50.0 / 24.0;
		encoderRight.setDistancePerPulse(wheelCirc / pulsesPerRevolution);
		encoderLeft.setDistancePerPulse(wheelCirc / pulsesPerRevolution);
		encoderRight.setReverseDirection(true);
		encoderLeft.setReverseDirection(true);
		encoderRight.reset();
		encoderLeft.reset();

		gyro.calibrate();
		gyro.setPIDSourceType(PIDSourceType.kDisplacement);
		
//		pid = new PIDController(0.0125, 0.01, 0.0, gyro, frontRightMotor);
//		pid2 = new PIDController(0.0125, 0.01, 0.0, gyro, frontLeftMotor);

//		pid.setContinuous(true);
//		pid.setInputRange(0, 360);
//		pid.setAbsoluteTolerance(3);
//		pid2.setContinuous(true);
//		pid2.setInputRange(0, 360);
//		pid2.setAbsoluteTolerance(3);

		turnPid = new SimplePIController(0.0125, 0.00001, gyro);
//		 turnPid = new SimplePIController(0.025, 0.02, turnPidSource);
		turnPid.setContinuous(true);
		turnPid.setDirection(false);
		turnPid.setInputRange(0, 360);
		turnPid.setOutputRange(-0.8, 0.8);
		turnPid.setTolerence(5);
	}

	public void initDefaultCommand() {
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

		SmartDashboard.putNumber("gyro", gyro.getAngle());

//		SmartDashboard.putNumber("LeftWheelDist", encoderLeft.getDistance());
//		SmartDashboard.putNumber("LeftWheelSpeed", encoderLeft.getRate());
//		SmartDashboard.putNumber("LeftWheelRPM", encoderLeft.getRate()
//				/ (8 * Math.PI) * 60);
//		SmartDashboard.putNumber("RightWheelDist", encoderRight.getDistance());
//		SmartDashboard.putNumber("RightWheelSpeed", encoderRight.getRate());
//		SmartDashboard.putNumber("RightWheelRPM", encoderRight.getRate()
//				/ (8 * Math.PI) * 60);
		
		SmartDashboard.putNumber("Dist", this.getDistance());
	}

	public void resetEncoders() {
		encoderLeft.reset();
		encoderRight.reset();
	}

	public double getDistance() {
		double dist = (encoderLeft.getDistance() + encoderRight.getDistance()) / 2.0;
		return dist;
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void setSetpoint(double setPointAngleDegrees) {
		turnPid.setSetpoint(setPointAngleDegrees);
//		pid.setSetpoint(setPointAngleDegrees);
//		pid2.setSetpoint(setPointAngleDegrees);
	}

	public double getGyro() {
		return gyro.getAngle();
	}

	public double getTurnPIDOutput() {
		return turnPid.getOutput();
	}

	public boolean isPIDOnTarget() {
		return turnPid.onTarget();
	}
	
	public double getError(){
		return turnPid.getError();
	}

}
