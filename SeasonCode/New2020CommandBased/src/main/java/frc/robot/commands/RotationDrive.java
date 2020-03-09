/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class RotationDrive extends CommandBase {

	private final DriveTrainSubsystem drivetrain;

	/**
	 * Drives the robot a set amount of rotations autonomously.
	 * 
	 * @param drivetrain Drivetrain subsystem
	 * @param rotations  Rotations to move robot. Distance = Wheel Circumference *
	 *   Rotations
	 */

	private double timeGoal;

	public RotationDrive(DriveTrainSubsystem drivetrain) {
		this.drivetrain = drivetrain;
		timeGoal = System.currentTimeMillis() + (1000 * 2);
		addRequirements(drivetrain);
	}

	/**
	 * Called when the command is initially scheduled
	 */
	@Override
	public void initialize() {

		timeGoal = System.currentTimeMillis() + (1000 * 0.8);

	}

	/**
	 * Called everytime the scheduler runs while command is scheduled
	 */
	@Override
	public void execute() {
		
		drivetrain.setTankDrive(0.8, 0.8);
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		this.cancel();
	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		if (System.currentTimeMillis() <= timeGoal) {
			return false;
		}
		else {
			end(true);
			return true;
		}
		
	}

}
