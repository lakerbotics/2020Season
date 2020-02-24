/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// Connectables
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

// Subsystems imports
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SII;

// Command imports
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.LimelightTrackingDrive;
import frc.robot.commands.IndexerDrive;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final DriveTrain m_drivetrain;
  private final SII m_sii;

  private final Joystick Joy;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

  public RobotContainer() {
    
    m_drivetrain = new DriveTrain();
    m_sii = new SII();

    Joy = new Joystick(0);
    //Xbox = new XboxController(0);


    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain, () -> Joy.getY(), () -> Joy.getZ()));

    configureButtonBindings();

    // Plug in both cameras to access
    //CameraServer.getInstance().startAutomaticCapture();
    //CameraServer.getInstance().startAutomaticCapture();

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton sideButton2 = new JoystickButton(Joy, 2);
    final JoystickButton topButton3 = new JoystickButton(Joy, 3);

    sideButton2.whileHeld(new LimelightTrackingDrive(m_drivetrain));
    topButton3.whileHeld(new IndexerDrive(m_sii, Joy, false));

    System.out.println("Configured");


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  /**
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
     //return m_autoCommand; # Not defined
  }
  */

}
