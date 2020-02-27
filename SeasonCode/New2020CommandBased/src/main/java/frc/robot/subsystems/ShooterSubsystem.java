/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ShooterSubsystem extends SubsystemBase {
 
  private TalonSRX m_left;
  private TalonSRX m_right;

  public ShooterSubsystem() {

    m_left = new TalonSRX(7);
    m_right = new TalonSRX(8);

    m_left.selectProfileSlot(0, 0);
		m_left.config_kF(0, 0.248); //0.248
		m_left.config_kP(0, 0);
		m_left.config_kI(0, 0);
    m_left.config_kD(0, 0);
    
    m_right.setInverted(true);
    m_right.follow(m_left);
    
  }

  public void drive(boolean flag)
  {

    if (flag) {
      m_left.set(ControlMode.Position, -4096);
      System.out.println(m_left.getSelectedSensorVelocity());
    }
    else {
      m_left.set(ControlMode.PercentOutput, 0);
    }
    
  } 

  @Override
  public void periodic() {
    
  }
}
