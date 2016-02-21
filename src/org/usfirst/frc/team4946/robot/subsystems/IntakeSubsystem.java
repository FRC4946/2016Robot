package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	VictorSP intakeRollerVictorSP = new VictorSP(RobotMap.PWM_VICTOR_SP_INTAKE);

	public void setInRollerSpeed(double rollerSpeed) {
		intakeRollerVictorSP.set(-rollerSpeed);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
