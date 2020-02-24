/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SII extends SubsystemBase {

  private WPI_VictorSPX m_IndexerTop;
  private WPI_VictorSPX m_IndexerBottom;
  private SpeedControllerGroup m_Indexer;

  private WPI_VictorSPX m_Intake;

  public SII()
  {

    // Indexer Setup
    m_IndexerTop = new WPI_VictorSPX(Constants.topIndexer);
    m_IndexerBottom = new WPI_VictorSPX(Constants.bottomIndexer);
    m_IndexerBottom.setInverted(true);
    m_Indexer = new SpeedControllerGroup(m_IndexerTop, m_IndexerBottom);

    //m_back = new PWMVictorSPX(Constants.backIndexer);
    //m_back.setInverted(true);
    //m_indexer = new SpeedControllerGroup(m_front, m_back);

  }

  public void indexerDrive(double speed, boolean polarity)
  {
    if (polarity) {
      m_Indexer.setInverted(true);
    }
    else {
      m_Indexer.setInverted(false);
    }
    m_Indexer.set(speed);
    System.out.println(speed);
  } 

  @Override
  public void periodic() {
    
    
  }
}
