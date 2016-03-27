package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.SallyPortPreset;
import org.usfirst.frc.team4946.robot.commands.arm.ArmDoNothing;
import org.usfirst.frc.team4946.robot.commands.drivetrain.DriveWithJoystickCommand;
import org.usfirst.frc.team4946.robot.commands.feederIntake.IntakeRollerBackward;
import org.usfirst.frc.team4946.robot.commands.feederIntake.IntakeRollerForward;
import org.usfirst.frc.team4946.robot.commands.feederIntake.IntakeUntilLimitSwitch;
import org.usfirst.frc.team4946.robot.commands.shooter.SetRollerSpeed;
import org.usfirst.frc.team4946.robot.commands.winch.ExtendWinch;
import org.usfirst.frc.team4946.robot.commands.winch.RetractWinch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	private Joystick driveStick = new Joystick(RobotMap.JOYSTICK_DRIVE_PORT);
	private Joystick taskStick = new Joystick(RobotMap.JOYSTICK_OPERATOR_PORT);

	private Button extendWinch = new JoystickButton(taskStick, 8);
	private Button retractWinch = new JoystickButton(taskStick, 9);

	private Button intakeForward = new JoystickButton(taskStick, 2);
	private Button intakeReverse = new JoystickButton(taskStick, 3);
	private Button shootButton = new JoystickButton(taskStick, 1);

	private Button preset1 = new JoystickButton(taskStick, 11);
	private Button preset2 = new JoystickButton(taskStick, 12);
	// private Button preset3 = new JoystickButton(taskStick, 9);
	// private Button preset4 = new JoystickButton(taskStick, 10);

	// private Button shootWithJoystickPID = new JoystickButton(taskStick, 4);
	// private Button shootWithVision = new JoystickButton(taskStick, 6);

	// private Button turnToGoal = new JoystickButton(driveStick, 2);
	private Button sallyPortPreset = new JoystickButton(driveStick, 7);

	// private Button camFront = new JoystickButton(driveStick, 3);
	// private Button camRear = new JoystickButton(driveStick, 4);

	public OI() {

		extendWinch.whileHeld(new ExtendWinch());
		retractWinch.whileHeld(new RetractWinch());

		shootButton.whileHeld(new IntakeRollerForward());
		intakeForward.whileHeld(new IntakeUntilLimitSwitch());
		intakeReverse.whileHeld(new IntakeRollerBackward());

		// turnToGoal.whileHeld(new TurnToFaceGoal());
		sallyPortPreset.whenPressed(new SallyPortPreset());
		sallyPortPreset.whenReleased(new DriveWithJoystickCommand());
		sallyPortPreset.whenReleased(new ArmDoNothing());

		// camFront.whenPressed(new SwitchToFrontCam());
		// camRear.whenPressed(new SwitchToRearCam());

		preset1.whileHeld(new SetRollerSpeed(0.7));
		preset2.whileHeld(new SetRollerSpeed(0.65));

	}

	public Joystick getDriveStick() {
		return driveStick;
	}

	public Joystick getOperatorStick() {
		return taskStick;
	}
}
