

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IndexerIntakeSubsystem;


public class IndexerIntakeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final IndexerIntakeSubsystem m_intake;

  private double m_left;
  private double m_right;

  private double speed;

  public IndexerIntakeDrive(IndexerIntakeSubsystem intake, boolean r) {
   
    m_intake = intake;
    speed = .5;

    if (r) {
      speed *= -1;
    }

    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.drive(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.drive(0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
