package org.usfirst.frc.team4946.robot.commands;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystickLimitedSpeedCommand extends Command {

    public DriveWithJoystickLimitedSpeedCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Joystick stick = Robot.oi.getJoystick();
    	
    	
    	
    	double drive = stick.getRawAxis(RobotMap.JOYSTICK_DRIVE_PORT);
    			
    	double curve = stick.getRawAxis(RobotMap.JOYSTICK_CURVE_PORT);
    	
    	
    	drive = drive*0.75;
    	curve = curve*0.75;
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
