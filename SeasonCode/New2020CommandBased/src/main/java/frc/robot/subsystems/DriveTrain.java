/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends SubsystemBase {
 
  private PWMVictorSPX m_frontRight;
  private PWMVictorSPX m_frontLeft;
  private PWMVictorSPX m_backRight;
  private PWMVictorSPX m_backLeft;

  private SpeedController m_right;
  private SpeedController m_left;

  private final DifferentialDrive m_drive;
  
  private int shaftPosition;
  private int shaftTarget;

  public DriveTrain() {

    m_frontRight = new PWMVictorSPX(Constants.frontRight);
    m_frontLeft = new PWMVictorSPX(Constants.frontLeft);
    m_backRight = new PWMVictorSPX(Constants.backRight);
    m_backLeft = new PWMVictorSPX(Constants.backLeft);

    m_right = new SpeedControllerGroup(m_frontRight, m_backRight);
    m_left = new SpeedControllerGroup(m_frontLeft, m_backLeft);

    m_drive = new DifferentialDrive(m_left, m_right);

  }

  public void drive(double x, double z)
  {
    m_drive.arcadeDrive(x, z);
  } 

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
    
    // Used for rotationDrive()
    shaftPosition = 0; // encoder.getPosition()
    
  }
}
