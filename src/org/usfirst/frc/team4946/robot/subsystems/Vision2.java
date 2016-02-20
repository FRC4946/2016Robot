package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.Robot;

public class Vision2 {

	public static double getVel(){
		return Robot.networkTable.getNumber("FINAL_VELOCITY", -1);
	}
	
	public static double getRPM(){
		return Robot.networkTable.getNumber("FINAL_RPM", -1);
	}
	
	public static double getDistInches(){
		return Robot.networkTable.getNumber("FINAL_DISTANCE_INCHES", -1);
	}
	
	public static double getDistMeters(){
		return Robot.networkTable.getNumber("FINAL_DISTANCE_INCHES", -1)*0.0254;
	}
	
	public static double getAngle(){
		return Robot.networkTable.getNumber("FINAL_ANGLE_TO_GOAL", -1);
	}
	
}
