package org.usfirst.frc.team4946.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * To be used in autonomous. This command group will drive the robot from the
 * neutral zone to the courtyard, passing through the LOW BAR defense.
 * 
 * 1. Drive forwards 6 feet to traverse from neutral to courtyard.
 * 2. Done.
 */
public class ChevalDeFriseScript extends CommandGroup {

	public ChevalDeFriseScript() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
