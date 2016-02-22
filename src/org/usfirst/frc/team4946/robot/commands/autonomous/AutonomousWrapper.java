package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.TurnToFaceGoal;
import org.usfirst.frc.team4946.robot.commands.feederIntake.IntakeRollerForward;
import org.usfirst.frc.team4946.robot.commands.shooter.RollerSpeedWithJoystickNoPID;
import org.usfirst.frc.team4946.robot.commands.shooter.RollerSpeedWithVision;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The wrapper class for all autonomous scripts. This command group will
 * determine which scripts to run and in what order based off of the values
 * provided when constructed ({@code initialPos} and {@code defense}).
 * <p>
 * <ol>
 * <li>Enable shooter wheels, start them accelerating.
 * <li>Traverse the specified defense using an external script (ie.
 * {@link SallyPortScript}).
 * <li>Drive and align the robot to face the tower, based on the specified
 * position.
 * <li>Activate vision tracking to determine the exact RPM required. Allow
 * wheels to begin to adjust.
 * <li>Use the vision tracking to turn the robot to directly face the target.
 * <li>Wait until the shooter has reached the desired speed.
 * <li>Shoot the ball.
 * <li>Done.
 */
public class AutonomousWrapper extends CommandGroup {

	public static final double ROBOT_LENGTH_INCHES = 32.0;
	public static final double DISTANCE_AUTO_ZONE_INCHES = 52.5;
	public static final double DISTANCE_DEFENSE_WIDTH_INCHES = 48.0;

	public AutonomousWrapper(int defense, int initialPos) {

		// Start up the shooter wheels to give them time to accelerate to the
		// desired speed. Set their default speed to a bit below the speed
		// required to shoot from just inside the courtyard. This is because
		// it's easier to accelerate than decelerate (I think???)

		addSequential(new CalibrateArm());

		// Traverse the defense placed in front of the robot
		switch (defense) {
		case Robot.Defenses.PORTCULLIS:
			addSequential(new PortcullisScript());
			break;
		case Robot.Defenses.CHEVAL_DE_FRISE:
			addSequential(new ChevalDeFriseScript());
			break;
		case Robot.Defenses.MOAT:
			addSequential(new MoatScript());
			break;
		case Robot.Defenses.RAMPARTS:
			addSequential(new RampartsScript());
			break;
		case Robot.Defenses.DRAWBRIDGE:
			addSequential(new DrawbridgeScript());
			break;
		case Robot.Defenses.SALLY_PORT:
			addSequential(new SallyPortScript());
			break;
		case Robot.Defenses.ROCK_WALL:
			addSequential(new RockWallScript());
			break;
		case Robot.Defenses.ROUGH_TERRAIN:
			addSequential(new RoughTerrainScript());
			break;
		case Robot.Defenses.LOW_BAR:
			// Intentional fall-through
		default:
			addSequential(new SallyPortScript());
			break;
		}

		// Once we've crossed the defense, turn and drive the required amount
		switch (initialPos) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			break;
		}

		// Once in position, activate the vision tracking and determine exactly
		// what RPM is required. Also, use the camera to determine how much we
		// need to turn to be directly facing the goal

		// addParallel(new RollerSpeedWithVision());
		addParallel(new RollerSpeedWithJoystickNoPID(0.6));
		addSequential(new TurnToFaceGoal());

		// addSequential(new WaitUntilShooterIsAtSpeed());
		addSequential(new IntakeRollerForward(), 2.0);

		// Cleanup. Insure that the drive motors are all off.
		addSequential(new DriveDistance(0.0, 0.0));
	}
}
