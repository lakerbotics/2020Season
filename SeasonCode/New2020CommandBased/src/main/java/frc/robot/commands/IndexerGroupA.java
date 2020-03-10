/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IndexerGroupA extends CommandBase {
	
	private final IndexerSubsystem indexer;
	private final Joystick Joy;
	private final double INITIAL_INDEXER_SPEED = 0.55;
	private final double SECONDARY_INDEXER_SPEED = 0.01;

	private final double PROXIMITY_THRESHOLD = 93;
	private boolean polarity;
	
	private final I2C.Port i2cPort = I2C.Port.kOnboard;
	private ColorSensorV3 colorSensor;

	/**
	 * Coordinates Intake with Indexer. Intake is constantly running. Activates
	 * Indexer when limit switch is triggered. Is contained within
	 * ParallelCommandGroup. See
	 * {@link frc.robot.RobotContainer#configureButtonBindings}
	 * 
	 * @param indexer Indexer subsystem
	 */
	public IndexerGroupA(IndexerSubsystem indexer, Joystick Joy) {
		this.indexer = indexer;
		this.Joy = Joy;

		colorSensor = new ColorSensorV3(i2cPort);

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

		SmartDashboard.putNumber("Proximity", colorSensor.getProximity());

		if (colorSensor.getProximity() > PROXIMITY_THRESHOLD) {

			//indexer.indexerDrive(INITIAL_INDEXER_SPEED, true);

			//Timer.delay(0.23); // 0.13, 0.20, 0.23

			indexer.indexerDrive(calculateSpeed(), true); //0.72
			Timer.delay(calculateTime()); //0.2

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

	public double calculateSpeed() {
		double JoySpeed = Math.abs(Joy.getY());
		if (JoySpeed >= 0.65) {
			return (JoySpeed * 0.82);
		}
		else {
			return (0.52);
		}
	}

	public double calculateTime() {
		double JoyTime = Math.abs(Joy.getY());
		double base = 0.5;
		if (JoyTime < 0.4) {
			return base;
		}
		else {
			return ( base - (JoyTime * 0.25));
		}
	}
}
