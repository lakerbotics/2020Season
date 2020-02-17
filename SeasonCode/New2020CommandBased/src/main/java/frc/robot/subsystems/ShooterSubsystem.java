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
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSubsystem extends SubsystemBase {
 
  private PWMVictorSPX m_left;
  private PWMVictorSPX m_right;

  private SpeedControllerGroup m_shooter;

  public ShooterSubsystem() {

    // Switch motor controllers from victors to talons
    m_left = new PWMVictorSPX(Constants.leftShooter);
    m_right = new PWMVictorSPX(Constants.rightShooter);
    m_right.setInverted(true);

    m_shooter = new SpeedControllerGroup(m_left, m_right);

  }

  public void drive(double speed)
  {
    m_shooter.set(speed);
  } 

  @Override
  public void periodic() {
    
  }
}
