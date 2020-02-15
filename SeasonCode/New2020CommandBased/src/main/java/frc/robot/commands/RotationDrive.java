/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;


public class RotationDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveTrain m_drivetrain;

  private double m_left;
  private double m_right;

  double heading_error;
  double distance_error;
  double steering_adjust;
  double distance_adjust;
  
  double Kp = -0.015;
  double min_aim_command = 0.36;

  private int shaftCurrent;
  private int shaftTarget;
  private int shaftDifference;

  public RotationDrive(DriveTrain drivetrain, double rotations) {
   
    m_drivetrain = drivetrain;

    shaftCurrent = 0; //encoder.getpos
    shaftTarget = (int)rotations + shaftCurrent;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    trackTargetShaft();
    m_drivetrain.tankDrive(m_left, m_right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void trackTargetShaft() {

    shaftCurrent = 0; // encoder.getpos()
    shaftDifference  = shaftTarget - shaftCurrent;

    heading_error = shaftDifference;
    steering_adjust = 0;

    if ( shaftDifference > 1) {
      steering_adjust = Kp*heading_error + min_aim_command;
    }
    else if (shaftDifference < 1) {
      steering_adjust = Kp*heading_error - min_aim_command;
    }

    m_left = steering_adjust;
    m_right = -1 * steering_adjust;

    if ( shaftDifference > 1) {
      m_drivetrain.tankDrive(m_left, m_right);
    }
  }

}
