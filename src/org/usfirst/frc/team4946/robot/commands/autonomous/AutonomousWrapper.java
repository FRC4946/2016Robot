package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.Robot;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team4946.robot.commands.drivetrain.Turn;
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
	public static final double DISTANCE_DEFENSE_WIDTH_INCHES = 80.0;

	public AutonomousWrapper(int defense, int initialPos) {

		Robot.driveTrainSubsystem.resetEncoders();

		addSequential(new RollerSpeedWithJoystickNoPID(0.0), 0.01);

		SmartDashboard.putString("Auto:", "Starting");

		// Start up the shooter wheels to give them time to accelerate to the
		// desired speed. Set their default speed to a bit below the speed
		// required to shoot from just inside the courtyard. This is because
		// it's easier to accelerate than decelerate (I think???)

		// addSequential(new CalibrateArm());

		// Traverse the defense placed in front of the robot
		switch (defense) {
		case Robot.Defenses.NOTHING:
			SmartDashboard.putString("Auto:", "Do Nothing A");
			addSequential(new Wait(20));
			break;
		case Robot.Defenses.PORTCULLIS:
			SmartDashboard.putString("Auto:", "Starting Portcullis script");
			addSequential(new PortcullisScript());
			break;
		case Robot.Defenses.CHEVAL_DE_FRISE:
			SmartDashboard
					.putString("Auto:", "Starting Cheval de Frise script");
			addSequential(new ChevalDeFriseScript());
			break;
		case Robot.Defenses.MOAT:
			SmartDashboard.putString("Auto:", "Starting Moat script");
			addSequential(new MoatScript());
			break;
		case Robot.Defenses.RAMPARTS:
			SmartDashboard.putString("Auto:", "Starting Ramparts script");
			addSequential(new RampartsScript());
			break;
		case Robot.Defenses.DRAWBRIDGE:
			SmartDashboard.putString("Auto:", "Starting Drawbridge script");
			addSequential(new DrawbridgeScript());
			break;
		case Robot.Defenses.SALLY_PORT:
			SmartDashboard.putString("Auto:", "Starting Sally Port script");
			addSequential(new SallyPortScript());
			break;
		case Robot.Defenses.SALLY_PORT_FAST:
			SmartDashboard
					.putString("Auto:", "Starting Fast Sally Port script");
			addSequential(new SallyPortScriptFAST());
			break;
		case Robot.Defenses.ROCK_WALL:
			SmartDashboard.putString("Auto:", "Starting Rock Wall script");
			addSequential(new RockWallScript());
			break;
		case Robot.Defenses.ROUGH_TERRAIN:
			SmartDashboard.putString("Auto:", "Starting Rough Terrain script");
			addSequential(new RoughTerrainScript());
			break;
		case Robot.Defenses.LOW_BAR:
			SmartDashboard.putString("Auto:", "Starting New Low Bar script");
			addSequential(new LowBarScript());
			break;
		case Robot.Defenses.LOW_BAR_OLD:
			// Intentional fall-through

		default:
			SmartDashboard.putString("Auto:", "Starting Old Low Bar script");
			addSequential(new LowBarScriptOLD());
			break;
		}

		// Once we've crossed the defense, turn and drive the required amount
		switch (initialPos) {
		case 1:
			SmartDashboard.putString("Auto:", "Position 1 Script");
			addSequential(new DriveDistance(87.0, 0.7));
			addSequential(new RotateToAngle(60));
			addSequential(new DriveDistance(81.0, 0.5));
			break;
		case 2:
			SmartDashboard.putString("Auto:", "Position 2 Script");
			addSequential(new DriveDistance(117.5, 0.7));
			addSequential(new RotateToAngle(60));
			addSequential(new DriveDistance(22.0, 0.5));
			break;
		case 3:
			SmartDashboard.putString("Auto:", "Position 3 Script");
			addSequential(new DriveDistance(46.5, 0.7));
			addSequential(new RotateToAngle(30));
			addSequential(new DriveDistance(47.5, 0.5));
			break;
		case 4:
			SmartDashboard.putString("Auto:", "Position 4 Script");
			addSequential(new DriveDistance(46.5, 0.7));
			addSequential(new RotateToAngle(-8));
			addSequential(new DriveDistance(42, 0.5));
			break;
		case 5:
			SmartDashboard.putString("Auto:", "Position 5 Script (Not Perp.)");
			addSequential(new DriveDistance(95.0, 0.7));
			addSequential(new RotateToAngle(-35));
			addSequential(new DriveDistance(33.5, 0.5));
			break;
		case 6:
			SmartDashboard.putString("Auto:",
					"Position 5 Script (Perpendicular)");
			addSequential(new DriveDistance(131.0, 0.7));
			addSequential(new RotateToAngle(-60));
			addSequential(new DriveDistance(-4, 0.5));
			break;

		case -1:
			// Intentionial fall-through
		default:
			SmartDashboard.putString("Auto:", "Do Nothing B");
			addSequential(new DriveDistance(20, 0.5));
			addSequential(new Wait(20));
			break;
		}

		// Once in position, activate the vision tracking and determine exactly
		// what RPM is required. Also, use the camera to determine how much we
		// need to turn to be directly facing the goal

		SmartDashboard.putString("Auto:", "Starting shooter wheels...");

		// addParallel(new RollerSpeedWithVision());
		addParallel(new RollerSpeedWithJoystickNoPID(0.7));
		// addSequential(new TurnToFaceGoal());

		// addSequential(new WaitUntilShooterAtSpeed());
		addSequential(new Wait(3.5));

		SmartDashboard.putString("Auto:", "Shooting!");
		addSequential(new IntakeRollerForward(), 2.0);

		// addSequential(new Turn(-0.8), 0.4);
		// addSequential(new DriveDistance(30, 0.7));

		// Cleanup. Insure that the drive motors are all off.
		SmartDashboard.putString("Auto:", "Done");
		// addParallel(new RollerSpeedWithJoystickNoPID(0.0), 0.01);
		addSequential(new DriveDistance(0.0, 0.0));
	}
}
