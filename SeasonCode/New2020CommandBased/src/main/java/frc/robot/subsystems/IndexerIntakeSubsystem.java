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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IndexerIntakeSubsystem extends SubsystemBase {

  private PWMVictorSPX m_front;
  private PWMVictorSPX m_back;
  private SpeedControllerGroup m_indexer;

  public IndexerIntakeSubsystem()
  {
    m_front = new PWMVictorSPX(Constants.frontIndexer);
    //m_back = new PWMVictorSPX(Constants.backIndexer);
    //m_back.setInverted(true);
    //m_indexer = new SpeedControllerGroup(m_front, m_back);

  }

  public void drive(double speed)
  {

    //m_indexer.set(speed);
    m_front.set(speed);
  } 

  @Override
  public void periodic() {
    
    
  }
}
