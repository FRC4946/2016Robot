package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.ExtendArm;
import org.usfirst.frc.team4946.robot.commands.ExtendWinch;
import org.usfirst.frc.team4946.robot.commands.JoystickShoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
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
	public Joystick stick = new Joystick(RobotMap.k_LEFT_JOYSTICK);
	private Button armUpButton = new JoystickButton(stick,
			RobotMap.k_ARM_UP_BUTTON);
	private Button armDownButton = new JoystickButton(stick,
			RobotMap.k_ARM_DOWN_BUTTON);
	private Button winchUpButton = new JoystickButton(stick,
			RobotMap.k_WINCH_UP_BUTTON);
	private Button winchDownButton = new JoystickButton(stick,
			RobotMap.k_WINCH_DOWN_BUTTON);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	public OI() {

		armUpButton.whenPressed(new ExtendArm(true));

		armDownButton.whenPressed(new ExtendArm(false));

		winchUpButton.whenPressed(new ExtendWinch(true));

		winchDownButton.whenPressed(new ExtendWinch(false));

		shootButton.whenPressed(new JoystickShoot());

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

	Button shootButton = new JoystickButton(stick, 0);

	public Joystick getDriveStick() {
		return stick;
	}
}
