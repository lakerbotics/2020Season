/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.PistonLiftSubsystem;

public class PistonLift extends CommandBase {
	// TODO Test
	private final PistonLiftSubsystem pistonLift;
	
	/**
	 * Activates pneumatics. Both pistons operate in unison
	 * 
	 * @param pistonLift PistonLight subsystem
	 */
	public PistonLift(PistonLiftSubsystem pistonLift) {
		this.pistonLift = pistonLift;
		addRequirements(pistonLift);
	}

	/**
	 * Called when the command is initially scheduled
	 */
	@Override
	public void initialize() {
		pistonLift.off();
	}

	/**
	 * Called everytime the scheduler runs while command is scheduled
	 */
	@Override
	public void execute() {
		//System.out.println("being executed");
		if (!pistonLift.isExtended()) {
			pistonLift.extend();
		}
		else {
			pistonLift.retract();
		}
		//pistonLift.retract();

		this.cancel();
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return false;
	}
}
