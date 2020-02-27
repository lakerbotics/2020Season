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


public class IntakeSubsystem extends SubsystemBase {
	private WPI_VictorSPX m_intake;
	
	public IntakeSubsystem(){
		m_intake = new WPI_VictorSPX(Constants.mainIntake);
	}
	
	public void driveIntake(double speed, boolean polarity){
		if (polarity) {
			m_intake.setInverted(true);
		}
		else {
			m_intake.setInverted(false);
		}
		m_intake.set(speed);
		System.out.println(speed);
		
	}
	
	@Override
	public void periodic() {
		
		
	}
	
	
}