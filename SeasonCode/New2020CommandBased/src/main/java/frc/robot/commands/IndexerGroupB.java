/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.Timer;
// import Timer

public class IndexerGroupB extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final IndexerSubsystem indexer;
    private final Shooter shooter;

	private double speed;
	private boolean polarity;

	public IndexerGroupB(IndexerSubsystem indexer, Shooter shooter) {
        this.indexer = indexer;
        this.shooter = shooter;

		addRequirements(indexer);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {

        /**
         * 1. Get shooter up to speed
         * 2. Enable indexer
         * 3. Keep showing until no longer pressed
         */

        if (shooter.isReady()) {
            indexer.indexerDrive(0.5, true);
            
        }
        else {
            indexer.indexerDrive(0, true);
        }

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
