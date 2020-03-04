/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexerGroupB extends CommandBase {
	private final double INDEXER_SPEED = 0.35; //0.5
	private final IndexerSubsystem indexer;
	private final Shooter shooter;

	private boolean polarity;

	/**
	 * Coordinates Indexer with Shooter. Shooter is constantly running. Activates
	 * Indexer when shooter is up to speed. Is contained within
	 * ParallelCommandGroup. See
	 * {@link frc.robot.RobotContainer#configureButtonBindings}
	 * 
	 * @param indexer Indexer subsystem
	 * @param shooter Shooter command
	 */
	public IndexerGroupB(IndexerSubsystem indexer, Shooter shooter) {
		this.indexer = indexer;
		this.shooter = shooter;

		addRequirements(indexer);
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
		if (shooter.isReady()) {
			indexer.indexerDrive(INDEXER_SPEED, true);
		}
		else {
			indexer.indexerDrive(0, true);
		}

	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		indexer.indexerDrive(0, polarity);
	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return false;
	}
}
