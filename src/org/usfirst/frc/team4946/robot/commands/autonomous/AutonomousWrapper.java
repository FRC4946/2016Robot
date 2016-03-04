package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.TurnToFaceGoal;
import org.usfirst.frc.team4946.robot.commands.feederIntake.IntakeRollerForward;
import org.usfirst.frc.team4946.robot.commands.shooter.RollerSpeedWithJoystickNoPID;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	public static final double DISTANCE_AUTO_ZONE_INCHES = 40;
	public static final double DISTANCE_DEFENSE_WIDTH_INCHES = 60.0;

	public AutonomousWrapper(int defense, int initialPos) {

		Robot.driveTrainSubsystem.resetEncoders();

		SmartDashboard.putString("Auto:", "Starting");
		
		addSequential(new RollerSpeedWithJoystickNoPID(0.0), 0.01);

		
		// Start up the shooter wheels to give them time to accelerate to the
		// desired speed. Set their default speed to a bit below the speed
		// required to shoot from just inside the courtyard. This is because
		// it's easier to accelerate than decelerate (I think???)

		// addSequential(new CalibrateArm());

		// Traverse the defense placed in front of the robot
		switch (defense) {
		case 9:
			System.out.println("Do Nothing");
			addSequential(new Wait(20));
			break;
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
			addSequential(new LowBarScript());
			break;
		}

		// Once we've crossed the defense, turn and drive the required amount
		switch (initialPos) {
		case 6:
			addSequential(new Wait(20));
			break;
		case 1:
			addSequential(new DriveDistance(12.0, 0.5));
			addSequential(new RotateToAngle(45));
			addSequential(new DriveDistance(12.0, 0.5));
			break;
		case 2:
			addSequential(new DriveDistance(20.0, 0.5));
			addSequential(new RotateToAngle(30));
			addSequential(new DriveDistance(8.0, 0.5));
			break;
		case 3:
			addSequential(new DriveDistance(20.0, 0.5));
			break;
		case 4:
			addSequential(new DriveDistance(20.0, 0.5));
			break;
		case 5:
			addSequential(new DriveDistance(20.0, 0.5));
			addSequential(new RotateToAngle(-30));
			addSequential(new DriveDistance(8.0, 0.5));
			break;
		default:
			break;
		}

		// Once in position, activate the vision tracking and determine exactly
		// what RPM is required. Also, use the camera to determine how much we
		// need to turn to be directly facing the goal

		// addParallel(new RollerSpeedWithVision());
		addParallel(new RollerSpeedWithJoystickNoPID(0.6));
//		addSequential(new TurnToFaceGoal());

		// addSequential(new WOaitUntilShooterAtSpeed());
		addSequential(new Wait(5.5));
		addSequential(new IntakeRollerForward(), 2.0);

		// Cleanup. Insure that the drive motors are all off.
		addParallel(new RollerSpeedWithJoystickNoPID(0.0), 0.01);
		addSequential(new DriveDistance(0.0, 0.0));
	}
}
