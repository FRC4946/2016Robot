package org.usfirst.frc.team4946.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * DOES NOTHING
 */
public class SallyPortScript extends CommandGroup {

	public SallyPortScript() {

		addSequential(new Wait(20));
	}
}
