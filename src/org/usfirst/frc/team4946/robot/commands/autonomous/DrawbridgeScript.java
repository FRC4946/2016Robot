package org.usfirst.frc.team4946.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * DOESNT WORK
 */
public class DrawbridgeScript extends CommandGroup {

	public DrawbridgeScript() {
		addSequential(new Wait(20));
	}
}
