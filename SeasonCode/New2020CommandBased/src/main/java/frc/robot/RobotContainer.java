/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot;

// Control Devices
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// Commands
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.*;

// Subsystems
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	private final DriveTrainSubsystem drivetrain;
	private final IndexerSubsystem indexer;
	private final IntakeSubsystem intake;
	private final ShooterSubsystem shooter;
	private final PistonLiftSubsystem pistonLift;

	private final Joystick Joy;

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */

	public RobotContainer() {

		drivetrain = new DriveTrainSubsystem();
		indexer = new IndexerSubsystem();
		intake = new IntakeSubsystem();
		shooter = new ShooterSubsystem();
		pistonLift = new PistonLiftSubsystem();

		Joy = new Joystick(0);

		drivetrain.setDefaultCommand(new ArcadeDrive(drivetrain, () -> Joy.getY(), () -> Joy.getZ()));

		configureButtonBindings();

		// Plug in both cameras to access
		// CameraServer.getInstance().startAutomaticCapture();
		// CameraServer.getInstance().startAutomaticCapture();

	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		final JoystickButton trigger = new JoystickButton(Joy, 1);
		final JoystickButton sideButton = new JoystickButton(Joy, 2);
		final JoystickButton topButton3 = new JoystickButton(Joy, 3);
		final JoystickButton topButton4 = new JoystickButton(Joy, 4);
		final JoystickButton topButton5 = new JoystickButton(Joy, 5);
		final JoystickButton topButton6 = new JoystickButton(Joy, 6);

		// TRIGGER -> Indexer and Shooter
		Shooter shooterCommand = new Shooter(shooter);
		trigger.whileHeld(new ParallelCommandGroup(shooterCommand, new IndexerGroupB(indexer, shooterCommand)));

		// SIDE BUTTON -> Limelight alignment
		sideButton.whileHeld(new LimelightTrackingDrive(drivetrain));

		// TOP BUTTON 3 -> Intake and Indexer
		topButton3.whileHeld(new ParallelCommandGroup(new Intake(intake, true), new IndexerGroupA(indexer)));

		// TOP BUTTON 4 -> Piston lift
		// Set to whenReleased because having whileHeld would tell pistons go up and
		// down hundreds of times within a short period of time.
		topButton4.whenReleased(new PistonLift(pistonLift));

		// TOP BUTTON 5 -> Indexer
		topButton5.whileHeld(new Indexer(indexer));

		// TOP BUTTON 6 -> Autonomous Drive
		topButton6.whileHeld(new RotationDrive(drivetrain, 2));

		System.out.println("Configured");

	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */

	/**
	 * public Command getAutonomousCommand() { // An ExampleCommand will run in
	 * autonomous // return autoCommand; # Not defined }
	 */

}
