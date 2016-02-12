package org.usfirst.frc.team4946.robot.DriveTrainCommands;

import org.usfirst.frc.team4946.robot.OI;
import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.RobotMap;
import org.usfirst.frc.team4946.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveWithJoystickCommand extends Command {

	
	private Gyro m_gyro;
	
    public DriveWithJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.driveTrainSubsystem);
    	
    	m_gyro = Robot.driveTrainSubsystem.driveTrainGyro;
    	
    		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Joystick stick = Robot.oi.getJoystick();
    	
    	
    	
    	double drive = stick.getRawAxis(RobotMap.JOYSTICK_DRIVE_PORT);
    			
    	double curve = stick.getRawAxis(RobotMap.JOYSTICK_CURVE_PORT);
    			
    	double throttle = stick.getRawAxis(RobotMap.JOYSTICK_THROTTLE_PORT);
    	
    	
    	Robot.driveTrainSubsystem.drive(drive, curve, throttle);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.driveTrainSubsystem.drive(0.0, 0.0, 0.0);
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	Robot.driveTrainSubsystem.drive(0.0, 0.0, 0.0);
    	
    }
}
