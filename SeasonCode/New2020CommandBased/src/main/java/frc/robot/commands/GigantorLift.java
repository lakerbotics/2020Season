/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.PistonLift;


public class GigantorLift extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final PistonLift p_PistonLift;

  public GigantorLift(PistonLift pistonLift) {
   
    p_PistonLift = pistonLift;

    addRequirements(p_PistonLift);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    p_PistonLift.gigantorExtend();
    
    // TODO: Insert sleep here

    p_PistonLift.gigantorRetract();

    // TODO: Insert sleep here

    p_PistonLift.miniGigantorEtend();

    // TODO: Insert sleep here

    p_PistonLift.miniGigantorRetract();

  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
