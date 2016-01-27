package org.usfirst.frc.team4946.robot;

import org.usfirst.frc.team4946.robot.commands.autonomous.AutonomousWrapper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	private CommandGroup m_autonomousCommandGroup;
	private SendableChooser m_autoDefense;
	private SendableChooser m_autoPosition;

	/**
	 * A simple class to enumerate each defense to simplify passing information
	 * about each defense
	 * 
	 * @author Matthew Reynolds
	 *
	 */
	public static class Defenses {
		public static final int LOW_BAR = 0;
		public static final int PORTCULLIS = 1;
		public static final int CHEVAL_DE_FRISE = 2;
		public static final int MOAT = 3;
		public static final int RAMPARTS = 4;
		public static final int DRAWBRIDGE = 5;
		public static final int SALLY_PORT = 6;
		public static final int ROCK_WALL = 7;
		public static final int ROUGH_TERRAIN = 8;
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();

		// Create the selector on the SmartDashboard for the defense to traverse
		m_autoDefense = new SendableChooser();
		m_autoDefense.addDefault("Low Bar", Defenses.LOW_BAR);
		m_autoDefense.addObject("Portcullis", Defenses.PORTCULLIS);
		m_autoDefense.addObject("Cheval de Frise", Defenses.CHEVAL_DE_FRISE);
		m_autoDefense.addObject("Moat", Defenses.MOAT);
		m_autoDefense.addObject("Ramparts", Defenses.RAMPARTS);
		m_autoDefense.addObject("Drawbridge", Defenses.DRAWBRIDGE);
		m_autoDefense.addObject("Sally Port", Defenses.SALLY_PORT);
		m_autoDefense.addObject("Rock Wall", Defenses.ROCK_WALL);
		m_autoDefense.addObject("Rough Terrain", Defenses.ROUGH_TERRAIN);

		// Create the selector on the SmartDashboard for the starting position
		m_autoPosition = new SendableChooser();
		m_autoDefense.addDefault("Pos 1 (Low Bar)", 1);
		m_autoDefense.addObject("Pos 2", 2);
		m_autoDefense.addObject("Pos 3", 3);
		m_autoDefense.addObject("Pos 4", 4);
		m_autoDefense.addObject("Pos 5 (Next to Secret Passage)", 5);

		// Place the two selectors on the SmartDashboard
		SmartDashboard.putData("Autonomous - Defense", m_autoDefense);
		SmartDashboard
				.putData("Autonomous - Starting Position", m_autoPosition);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {

		// Get the user-selected position and defense
		int defense = (int) m_autoDefense.getSelected();
		int position = (int) m_autoPosition.getSelected();

		// Create the autonomous command using the user-selected values
		m_autonomousCommandGroup = new AutonomousWrapper(defense, position);

		// Start the autonomous command
		m_autonomousCommandGroup.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running.
		if (m_autonomousCommandGroup != null)
			m_autonomousCommandGroup.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
