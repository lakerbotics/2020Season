/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.CompressorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * An example command that uses an example subsystem.
 */
public class CompressorActivation extends CommandBase {
	@SuppressWarnings("unused")
	private final CompressorSubsystem subsystem;

	private double pressure;
	private AnalogInput pressureSensor;

	/**
	 * Example subsystem
	 * 
	 * @param subsystem
	 */
	public CompressorActivation(CompressorSubsystem subsystem) {
		this.subsystem = subsystem;
		subsystem.Off();

		pressureSensor = new AnalogInput(1);
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(subsystem);
	}

	/**
	 * Called when the command is initially scheduled
	 */
	@Override
	public void initialize() {
	}

	/**
	 * Called everytime the scheduler runs while command is scheduled
	 */
	@Override
	public void execute() {
		/*
		pressure = (250 * (pressureSensor.getVoltage() / 4.8) - 25);
		if (pressure < 50 && !(subsystem.getState())) {
			//subsystem.On();
		}
		else if (!(subsystem.getState())) {
			subsystem.Off();
		}
		//System.out.println("pressure " + pressure); */

		//System.out.println("state " + subsystem.getState());

		if (subsystem.getState()) {
			subsystem.Off();
			System.out.println("Turned off");
		}
		else {
			subsystem.On();
			System.out.println("Turned on");
		}
		
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
