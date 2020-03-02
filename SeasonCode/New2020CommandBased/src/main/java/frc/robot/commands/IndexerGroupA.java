/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexerGroupA extends CommandBase {
	// TODO Fix ball shooting out of intake. Tune with indexer speeds and delays
	private final IndexerSubsystem indexer;
	private final double INITIAL_INDEXER_SPEED = 0.55;
	private final double SECONDARY_INDEXER_SPEED = 0.01;

	private boolean polarity;
	private DigitalInput limiter;

	/**
	 * Coordinates Intake with Indexer. Intake is constantly running. Activates
	 * Indexer when limit switch is triggered. Is contained within
	 * ParallelCommandGroup. See
	 * {@link frc.robot.RobotContainer#configureButtonBindings}
	 * 
	 * @param indexer Indexer subsystem
	 */
	public IndexerGroupA(IndexerSubsystem indexer) {
		this.indexer = indexer;
		limiter = new DigitalInput(0);

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

		if (!limiter.get()) {

			indexer.indexerDrive(INITIAL_INDEXER_SPEED, true);

			Timer.delay(0.13); // 0.15
			indexer.indexerDrive(SECONDARY_INDEXER_SPEED, false);
			Timer.delay(1.1);
			this.cancel();

		}
		else {
			indexer.indexerDrive(0, false);
		}

		// Psuedocode
		// if ball counter is 5
		// reverse intake direction (shoot balls outward because we are at max capacity)

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
