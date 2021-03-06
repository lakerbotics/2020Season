/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code 	*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;


public class ReverseDrive extends CommandBase {
	private final DriveTrainSubsystem driveTrain;

	boolean finished = false;

	public ReverseDrive(DriveTrainSubsystem drivetrain) {
		this.driveTrain = drivetrain;
		addRequirements(drivetrain);
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
		driveTrain.pause();

		Timer.delay(1);

		driveTrain.reverse();
		finished = true;
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		driveTrain.drive(0, 0);
	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return finished;
	}

}
