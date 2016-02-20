package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.IntakeRollerBackward;
import org.usfirst.frc.team4946.robot.commands.IntakeRollerForward;
import org.usfirst.frc.team4946.robot.commands.RollerSpeedWithJoystickNoPID;
import org.usfirst.frc.team4946.robot.commands.RollerSpeedWithJoystickPID;
import org.usfirst.frc.team4946.robot.commands.RollerSpeedWithVision;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExfampleCommand());

	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.

	private Joystick driveStick = new Joystick(RobotMap.JOYSTICK_DRIVE_PORT);
	private Joystick taskStick = new Joystick(RobotMap.JOYSTICK_OPERATOR_PORT);

	private Button armUpButton = new JoystickButton(taskStick, 0);
	private Button armDownButton = new JoystickButton(taskStick, 1);
	private Button winchUpButton = new JoystickButton(taskStick, 2);
	private Button winchDownButton = new JoystickButton(taskStick, 3);

	private Button intakeForward = new JoystickButton(taskStick, 1);
	private Button intakeReverse = new JoystickButton(taskStick, 2);

	private Button shootWithJoystickPID = new JoystickButton(driveStick, 5);
	private Button shootWithVision = new JoystickButton(driveStick, 6);
	private Button shootButton = new JoystickButton(driveStick, 0);

	public OI() {
		//
		// armUpButton.whenPressed(new ExtendArm(true));
		// armDownButton.whenPressed(new ExtendArm(false));
		// winchUpButton.whenPressed(new ExtendWinch(true));
		// winchDownButton.whenPressed(new ExtendWinch(false));

		intakeForward.whileHeld(new IntakeRollerForward());
		intakeReverse.whileHeld(new IntakeRollerBackward());
		
		shootWithJoystickPID.whileHeld(new RollerSpeedWithJoystickPID());
		shootWithVision.whileHeld(new RollerSpeedWithVision());

		//shootButton.whenPressed(new RollerSpeedWithJoystick());
	}

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public Joystick getDriveStick() {
		return driveStick;
	}

	public Joystick getOperatorStick() {
		return taskStick;
	}
}
