package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.arm.LowerArm;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.ReturnToZeroAngle;
import org.usfirst.frc.team4946.robot.commands.feederIntake.IntakeRollerForward;
import org.usfirst.frc.team4946.robot.commands.shooter.RollerSpeedWithJoystickNoPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Team188Script extends CommandGroup {
    
    public  Team188Script() {

		// Save this position as the angle 0
		Robot.driveTrainSubsystem.resetGyro();
    	Robot.driveTrainSubsystem.resetEncoders();
    	
		SmartDashboard.putString("Auto:", "Starting 188 Script");
		addSequential(new RollerSpeedWithJoystickNoPID(0.7), 0.01);
		addSequential(new DriveDistance(75, 0.7));
		
		SmartDashboard.putString("Auto:", "Shooting (& lowering arm)!");
		addParallel(new LowerArm(), 1.5);
		addSequential(new IntakeRollerForward(), 2.0);
		addSequential(new RollerSpeedWithJoystickNoPID(0.0), 0.01);

		SmartDashboard.putString("Auto:", "Backing up");
		addSequential(new DriveDistance(55, -0.7));
		
		SmartDashboard.putString("Auto:", "Turning and backing up");
		addSequential(new DriveDistance(40, -0.7, 0.5));
		
		SmartDashboard.putString("Auto:", "Turning");
		addSequential(new ReturnToZeroAngle(-90), 3);
		
		SmartDashboard.putString("Auto:", "Crossing into neutral");
		addSequential(new DriveDistance(210, -0.7));
		
		SmartDashboard.putString("Auto:", "Crossing into courtyard");
		addSequential(new DriveDistance(100, 0.7));
		
		SmartDashboard.putString("Auto:", "Crossing into neutral again");
		addSequential(new DriveDistance(100, -0.7));
		
		SmartDashboard.putString("Auto:", "Done");
		addSequential(new DriveDistance(0.0, 0.0));
    }
}
