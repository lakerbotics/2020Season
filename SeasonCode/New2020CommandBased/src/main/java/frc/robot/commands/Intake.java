

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake extends CommandBase {
	private final IntakeSubsystem intake;
	private double speed;
	private boolean polarity;
	
	public Intake(IntakeSubsystem intake, boolean polarity) {
		this.intake = intake;
		speed = 1.0;
		this.polarity = polarity;

		addRequirements(intake);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		intake.driveIntake(speed, polarity);
	}
	
	@Override
	public void end(boolean interrupted) {
		intake.driveIntake(0, false);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}