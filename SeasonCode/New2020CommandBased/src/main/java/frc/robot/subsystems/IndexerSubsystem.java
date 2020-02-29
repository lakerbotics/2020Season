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

public class IndexerSubsystem extends SubsystemBase {
	
	private WPI_VictorSPX indexerTop;
	private WPI_VictorSPX indexerBottom;
	private SpeedControllerGroup indexer;
	
	public IndexerSubsystem()
	{
		
		// Indexer Setup
		indexerTop = new WPI_VictorSPX(Constants.topIndexer);
		indexerBottom = new WPI_VictorSPX(Constants.bottomIndexer);
		indexer = new SpeedControllerGroup(indexerTop, indexerBottom);  
	}
	
	public void indexerDrive(double speed, boolean polarity)
	{
		if (polarity) {
			indexer.setInverted(true);
		}
		else {
			indexer.setInverted(false);
		}
		indexer.set(speed);
	} 
	
	@Override
	public void periodic() {
	}
}
