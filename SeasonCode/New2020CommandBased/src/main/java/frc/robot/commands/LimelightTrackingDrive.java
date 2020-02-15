/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.subsystems.DriveTrain;


public class LimelightTrackingDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveTrain m_drivetrain;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry limelight_tx = table.getEntry("tx");

  private double m_left;
  private double m_right;
  private double m_tx;

  double tx;
  double ty;

  private double m_turn;

  // New tracking
  double heading_error;
  double distance_error;
  double steering_adjust;
  double distance_adjust;
  
  double Kp = -0.015;
  double min_aim_command = 0.36;

  public LimelightTrackingDrive(DriveTrain drivetrain) {
   
    m_drivetrain = drivetrain;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    /*
    if (table.getEntry("tv").getDouble(0.0) == 1)
    {
      
      if (Math.abs(table.getEntry("tx").getDouble(0.0)) > 0.5) {
        calculateTurnSpeed();
        //System.out.println("Right: " + m_right);
        //System.out.println("Left: " + m_left);
        m_drivetrain.drive(0, m_turn);
      }

    }
    else {
      System.out.println("NO TARGET");
    }*/

    trackTargetX();
    
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

  public void calculateTurnSpeed() {
    m_left = 0;
    m_right = 0;
    m_tx = table.getEntry("tx").getDouble(0.0);
    double scale = -.4; // .35
    double scale2 = .75; // .8

    m_turn = (2*scale2  / (1 + Math.pow(Math.E, m_tx * scale))) - 1*scale2;
    //m_turn = 0;
  }

  public void trackTargetX() {

    tx = table.getEntry("tx").getDouble(0.0);
    
    heading_error = -tx;
    steering_adjust = 0;

    if (tx > 1.0) {
      steering_adjust = Kp*heading_error + min_aim_command;
    }
    else if (tx < 1.0) {
      steering_adjust = Kp*heading_error - min_aim_command;
    }

    m_left = steering_adjust;
    m_right = -1 * steering_adjust;

    if ( (Math.abs(1 - Math.abs(tx))) > 1 ) {
      m_drivetrain.tankDrive(m_left, m_right);
    }
  }

  public void trackTargetY() {
    ty = table.getEntry("ty").getDouble(0.0);

    heading_error = -ty;
    steering_adjust = 0;

    if (ty > 1.0) {
      steering_adjust = Kp*heading_error + min_aim_command;
    }
    else if (ty < 1.0) {
      steering_adjust = Kp*heading_error - min_aim_command;
    }

    m_left = steering_adjust;
    m_right = -1 * steering_adjust;

    if ( (Math.abs(1 - Math.abs(ty))) > 1) {
      m_drivetrain.tankDrive(m_left, m_right);
    }
  }
}
