/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;

public class CompressorSubsystem extends SubsystemBase {

	/**
	 * Creates a new ExampleSubsystem.
	 */

	private Compressor compressor;
	private boolean state = false;

	public CompressorSubsystem() {
		compressor = new Compressor();
		Off();
		//System.out.println("compressor is off right now");
	}

	/**
	 * Called once per scheduler run
	 */
	public void On() {
		compressor.start();
		state = true;
	}
	
	public void Off() {
		compressor.stop();
		state = false;
	}

	public boolean getState() {

		if (compressor.getCompressorCurrent() < 1) {
			return false;
		} 
		else {
			return true;
		}
		//return state; // TODO : Change this
	}

	@Override
	public void periodic() {
	}
}
