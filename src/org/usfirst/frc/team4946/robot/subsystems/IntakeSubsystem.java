package org.usfirst.frc.team4946.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	
	VictorSP intakeRollerVictorSP = new VictorSP(6);

	public void setInRollerSpeed(double RollerSpeed) {
		
	if (RollerSpeed == -1.0) {
		intakeRollerVictorSP.set(-1.0);
		
		}
	else if (RollerSpeed == 1.0) {
		intakeRollerVictorSP.set(1.0);
	}
	else if (RollerSpeed == 0.0) {
		intakeRollerVictorSP.set(0.0);
	}
		
	}
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

