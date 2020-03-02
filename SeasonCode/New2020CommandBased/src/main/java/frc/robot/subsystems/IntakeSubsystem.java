/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
	private WPI_VictorSPX intake;

	/**
	 * Creates new intake subsystem
	 */
	public IntakeSubsystem() {
		intake = new WPI_VictorSPX(Constants.mainIntake);
	}

	/**
	 * Moves intake at given speed and direction
	 * 
	 * @param speedSpeed of intake
	 * @param polarity Direction of intake (boolean)
	 */
	public void driveIntake(double speed, boolean polarity) {
		if (polarity) {
			intake.setInverted(true);
		}
		else {
			intake.setInverted(false);
		}
		intake.set(speed);
	}

	/**
	 * Called once per scheduler run
	 */
	@Override
	public void periodic() {
	}
}