
/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends CommandBase {
	private final double INTAKE_SPEED = 0.7; //0.8
	private final IntakeSubsystem intake;
	private boolean polarity;

	/**
	 * Activates Intake subsystem in certain direction (speed is constant)
	 * 
	 * @param intake   Intake subsystem
	 * @param polarity Direction of intake (boolean)
	 */
	public Intake(IntakeSubsystem intake, boolean polarity) {
		this.intake = intake;
		this.polarity = polarity;

		addRequirements(intake);
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
		intake.driveIntake(INTAKE_SPEED, polarity);
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		intake.driveIntake(0, false);

	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return false;
	}
}