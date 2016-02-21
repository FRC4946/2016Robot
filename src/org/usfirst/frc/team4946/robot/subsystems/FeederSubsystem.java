package org.usfirst.frc.team4946.robot.subsystems;

import org.usfirst.frc.team4946.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FeederSubsystem extends Subsystem {

	private VictorSP feederLeftVictor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_FEEDER_LEFT);
	private VictorSP feederRightVictor = new VictorSP(
			RobotMap.PWM_VICTOR_SP_FEEDER_RIGHT);
	private DigitalInput limitSwitch = new DigitalInput(
			RobotMap.DIO_LIMIT_SWITCH_FEEDER);

	public void setSpeed(double rollerSpeed) {
		feederLeftVictor.set(-rollerSpeed);
		feederRightVictor.set(rollerSpeed);
	}

	public boolean getLimitSwitchIsHeld() {
		return !limitSwitch.get();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
