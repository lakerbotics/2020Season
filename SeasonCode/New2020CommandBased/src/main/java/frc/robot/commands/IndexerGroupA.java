/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
// import Timer

public class IndexerGroupA extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final IndexerSubsystem indexer;

	private double speed;
	private boolean polarity;
	private DigitalInput limiter;

	public IndexerGroupA(IndexerSubsystem indexer) {
		this.indexer = indexer;
		limiter = new DigitalInput(0);

		addRequirements(indexer);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {

		if (!limiter.get()) {
			
			indexer.indexerDrive(0.55, true);

			Timer.delay(0.13); //0.15
			indexer.indexerDrive(0.01, false);
			Timer.delay(1.1);
			this.cancel();

			
		} else {
			indexer.indexerDrive(0, false);
		}

		/*
		 * if counter = 5 intake.setpolarity() to be reversed (shoots outwards)
		 */

	}

	@Override
	public void end(boolean interrupted) {
		indexer.indexerDrive(0, polarity);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
