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


public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveTrain m_drivetrain;
  private final DoubleSupplier m_x;
  private final DoubleSupplier m_z;


  public ArcadeDrive(DriveTrain drivetrain, DoubleSupplier x, DoubleSupplier z) {
   
    m_drivetrain = drivetrain;
    m_x = x;
    m_z = z;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.drive(throttleMixerY() *-1, throttleMixerZ());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private double throttleMixerY() {
    double YSpeed = m_x.getAsDouble();
    if ((YSpeed > 0.2) | (YSpeed < -0.2)) {
      return YSpeed*0.9;
    }
    else if ((YSpeed <= 0.2) & (YSpeed >= -0.2)) {
      return YSpeed*0;
    }
    else {
      return YSpeed;
    }
  }

  private double throttleMixerZ() {
    double ZSpeed = m_z.getAsDouble();
    if ((ZSpeed > 0.2) | (ZSpeed < -0.2)) {
      return ZSpeed*0.7;
    }
    else if ((ZSpeed <= 0.2) & (ZSpeed >= -0.2)) {
      return ZSpeed*0;
    }
    else {
      return ZSpeed;
    }
  }
}

