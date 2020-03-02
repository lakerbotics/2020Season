
/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;;

public class Shooter extends CommandBase {
	private final int SPEED_THRESHOLD = 350000;
	private final ShooterSubsystem shooter;

	/**
	 * Activates the shooter on or off
	 * 
	 * @param shooter Shooter subsystem
	 */
	public Shooter(ShooterSubsystem shooter) {
		this.shooter = shooter;

		addRequirements(shooter);
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
		shooter.drive(true);
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		shooter.drive(false);
	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return false;
	}

	/**
	 * Determines if the shooter is ready to fire if the shooter speed is over
	 * threshold
	 * 
	 * @return If shooter is ready or not
	 */
	public boolean isReady() {
		if (Math.abs(shooter.getSpeed()) > SPEED_THRESHOLD) {
			return true;
		}
		else {
			return false;
		}
	}
}
