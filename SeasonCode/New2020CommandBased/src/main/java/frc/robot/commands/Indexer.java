/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;

public class Indexer extends CommandBase {
	// TODO Finetune indexer speed
	private final double INDEXER_SPEED = 0.5;
	private final IndexerSubsystem indexer;

	private boolean polarity;

	/**
	 * Moves robot indexers at a set speed
	 * 
	 * @param indexer Indexer subsystem
	 */
	public Indexer(IndexerSubsystem indexer, boolean polarity) {
		this.indexer = indexer;
		this.polarity = polarity;
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
		indexer.indexerDrive(INDEXER_SPEED, this.polarity);
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
