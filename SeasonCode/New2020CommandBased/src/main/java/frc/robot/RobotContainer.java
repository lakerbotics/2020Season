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


import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;

// Commands
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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

	// Subsystems

	DriveTrainSubsystem drivetrainSubsystem;
	IndexerSubsystem indexerSubsystem;
	IntakeSubsystem intakeSubsystem;
	ShooterSubsystem shooterSubsystem;
	PistonLiftSubsystem pistonLiftSubsystem;
	CompressorSubsystem compressorSub;


	// Commands
	private final ArcadeDrive arcadeDrive;
	private final Indexer indexerReverse;
	private final LimelightTrackingDrive limeLightDrive;
	private final Shooter shooter;
	private final Intake intake;
	private final Indexer indexer;
	private final IndexerGroupA indexerGroupA;
	private final IndexerGroupB indexerGroupB;
	private final PistonLift pistonLift;
	
	private final ParallelCommandGroup intakeIndexerGroup;
	private final ParallelCommandGroup indexerShooterGroup;
	private CompressorActivation compressorActivation;
	private final CameraManagement cameraCommand;

	private SequentialCommandGroup autonomousCommand;
	

	private final Joystick Joy;

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */

	public RobotContainer() {
		Joy = new Joystick(0);

		// Subsystem initialization
		drivetrainSubsystem = new DriveTrainSubsystem();
		indexerSubsystem = new IndexerSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		shooterSubsystem = new ShooterSubsystem();
		pistonLiftSubsystem = new PistonLiftSubsystem();
		compressorSub = new CompressorSubsystem();


		// Command initializations
		arcadeDrive = new ArcadeDrive(drivetrainSubsystem, () -> Joy.getY(), () -> Joy.getZ());

		limeLightDrive = new LimelightTrackingDrive(drivetrainSubsystem);
		shooter = new Shooter(shooterSubsystem);
		intake = new Intake(intakeSubsystem, true, Joy);
		indexer = new Indexer(indexerSubsystem, true);
		indexerReverse = new Indexer(indexerSubsystem, false);
		indexerGroupA = new IndexerGroupA(indexerSubsystem, Joy);
		indexerGroupB = new IndexerGroupB(indexerSubsystem, shooter);
		pistonLift = new PistonLift(pistonLiftSubsystem);
		compressorActivation = new CompressorActivation(compressorSub);

		intakeIndexerGroup = new ParallelCommandGroup(intake, indexerGroupA);
		indexerShooterGroup = new ParallelCommandGroup(shooter, indexerGroupB);

		cameraCommand = new CameraManagement();
		
		
		// Default commands
		drivetrainSubsystem.setDefaultCommand(arcadeDrive);
		//intakeSubsystem.setDefaultCommand(intake);
		//compressorSub.setDefaultCommand(compressorActivation);

		// Autonomous command
		//autonomousCommand = new SequentialCommandGroup(new LimelightTrackingAuto(drivetrainSubsystem), new ParallelCommandGroup( shooterAuto, new IndexerGroupBAuto(indexerSubsystem, shooterAuto) ), new RotationDrive(drivetrainSubsystem));
		autonomousCommand = new AutonomousCommand(drivetrainSubsystem, shooterSubsystem, indexerSubsystem);

		configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		// TODO Finalize button mappings

		final JoystickButton trigger = new JoystickButton(Joy, 1);
		final JoystickButton sideButton = new JoystickButton(Joy, 2);
		final JoystickButton topButton3 = new JoystickButton(Joy, 3);
		final JoystickButton topButton4 = new JoystickButton(Joy, 4);
		final JoystickButton topButton5 = new JoystickButton(Joy, 5);
		final JoystickButton topButton6 = new JoystickButton(Joy, 6);

		final JoystickButton bottomButton8 = new JoystickButton(Joy, 8);
		final JoystickButton bottomButton10 = new JoystickButton(Joy, 10);
		final JoystickButton bottomButton12 = new JoystickButton(Joy, 12);

		// TRIGGER -> Indexer and Shooter
		trigger.whileHeld(indexerShooterGroup);
		
		// SIDE BUTTON -> Limelight alignment
		sideButton.whileHeld(limeLightDrive);

		// TOP BUTTON 3 -> Intake and Indexer
		topButton3.whileHeld(intakeIndexerGroup);

		// TOP BUTTON 4 -> Piston lift
		// Set to whenReleased because having whileHeld would tell pistons go up and
		// down hundreds of times within a short period of time.
		topButton4.whenPressed(pistonLift);
				
		// TOP BUTTON 5 -> Indexer
		topButton5.whileHeld(indexer);

		// TOP BUTTON 6 -> Autonomous Drive
		topButton6.whileHeld(indexerReverse);

		// Bottom Button 8 -> Compressor On/Off For now
		bottomButton8.whenPressed(compressorActivation);

		// Bottom Button 10 -> Camera Switch
		bottomButton10.whenPressed(cameraCommand);
		
		// Bottom Button 12 -> Reverses drivetrain polarity

		// TODO : FIX THIS
		//bottomButton12.whenPressed(new ReverseDrive(drivetrainSubsystem));

		System.out.println("Buttons Mapped");

	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		return autonomousCommand;
	}

}
