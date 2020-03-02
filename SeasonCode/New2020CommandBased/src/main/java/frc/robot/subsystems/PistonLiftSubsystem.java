/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PistonLiftSubsystem extends SubsystemBase {
	private DoubleSolenoid pistonLeft;
	private DoubleSolenoid pistonRight;

	/**
	 * Creates new piston lift subsystem
	 */
	public PistonLiftSubsystem() {
		//pistonLeft = new DoubleSolenoid(Constants.leftPistonForward, Constants.leftPistonReverse);
		//pistonRight = new DoubleSolenoid(Constants.rightPistonForward, Constants.rightPistonReverse);
	}

	/**
	 * Actuates both pistons
	 */
	public void extend() {
		pistonLeft.set(Value.kForward);
		pistonRight.set(Value.kForward);
	}

	/**
	 * Deactivates both pistons
	 */
	public void retract() {
		pistonLeft.set(Value.kReverse);
		pistonRight.set(Value.kReverse);
	}

	/**
	 * Checks if the pistons are extended
	 * @return If pistons are extended or not
	 */
	public boolean isExtended() {
		// TODO Test this out
		return pistonLeft.get() == Value.kForward;
	}

	/**
	 * Called once per scheduler run
	 */
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
