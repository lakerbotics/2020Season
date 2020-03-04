/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class LimelightTrackingDrive extends CommandBase {

	private final DriveTrainSubsystem drivetrain;

	// Limelight
	NetworkTable limeLight = NetworkTableInstance.getDefault().getTable("limelight");

	// Proportional tracking
	double headingError;
	double distanceError;
	double steeringAdjust;
	double distanceAdjust;
	double KpAim;
	double KpDistance;

	private double left;
	private double right;
	private double tx;
	private double ty;

	private final double KP = -0.015;
	private final double MIN_AIM_COMMAND = 0.6; // 0.36 , 0.5

	/**
	 * Controls drivetrain and aligns robot with LimeLight crosshair
	 * 
	 * @param drivetrain Drivetrain subsystem
	 */
	public LimelightTrackingDrive(DriveTrainSubsystem drivetrain) {
		this.drivetrain = drivetrain;

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
		trackTargetX();
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		drivetrain.tankDrive(0, 0);
	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return false;
	}

	/**
	 * Rotates robot with tank drive to correct X offset
	 */
	public void trackTargetX() {
		tx = limeLight.getEntry("tx").getDouble(0.0);

		headingError = -tx;
		steeringAdjust = 0;

		if (tx > 1.4) {
			steeringAdjust = KP * headingError + MIN_AIM_COMMAND;
		}
		else if (tx < -0.6) {
			steeringAdjust = KP * headingError - MIN_AIM_COMMAND;
		}

		left = steeringAdjust;
		right = -1 * steeringAdjust;
		
		if ((Math.abs(2 - Math.abs(tx))) > 1) {
			drivetrain.tankDrive(left, right);
		}
	}

	/**
	 * Moves & Rotates the robot with tank drive to correct X and Y offset
	 */
	public void trackTarget() {
		// TODO Test alignment
		tx = limeLight.getEntry("tx").getDouble(0.0);
		ty = limeLight.getEntry("ty").getDouble(0.0);

		KpAim = -0.15; // To be tuned
		KpDistance = -0.1; // To be tuned

		// MIN_AIM_COMMAND   To be tuned

		headingError = -tx;
		distanceError = -ty;
		steeringAdjust = 0.0;

		if (tx > 1.0) {
			steeringAdjust = KpAim * headingError - MIN_AIM_COMMAND;
		}
		else if (tx < -1.0) {
			steeringAdjust = KpAim * headingError + MIN_AIM_COMMAND;
		}

		distanceAdjust = KpDistance * distanceError;

		left += steeringAdjust + distanceAdjust;
		right -= steeringAdjust + distanceAdjust;
	}
}
