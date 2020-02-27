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
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ShooterSubsystem extends SubsystemBase {
	
	private PWMVictorSPX left; // Master

	@SuppressWarnings("unused")
	private PWMVictorSPX right; // Slave
	
	// private VictorSPX victor;
	// private SpeedControllerGroup shooter;
	
	public ShooterSubsystem() {
		// TODO Switch motor controllers from victors to talons
		left = new PWMVictorSPX(Constants.leftShooter);
	}
	
	public void drive(double speed) {
		left.set(speed);
	} 
	
	@Override
	public void periodic() {
		
	}
}
