/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class RotationDrive extends CommandBase {
	private final DriveTrainSubsystem drivetrain;
	double rotations;

	/**
	 * Drives the robot a set amount of rotations autonomously.
	 * 
	 * @param drivetrain Drivetrain subsystem
	 * @param rotations  Rotations to move robot. Distance = Wheel Circumference *
	 *   Rotations
	 */
	public RotationDrive(DriveTrainSubsystem drivetrain, double rotations) {
		this.drivetrain = drivetrain;
		this.rotations = rotations;

		addRequirements(drivetrain);
	}

	/**
	 * Called when the command is initially scheduled
	 */
	@Override
	public void initialize() {
		drivetrain.setTarget(this.rotations);
	}

	/**
	 * Called everytime the scheduler runs while command is scheduled
	 */
	@Override
	public void execute() {
		drivetrain.rotationDrive();
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
