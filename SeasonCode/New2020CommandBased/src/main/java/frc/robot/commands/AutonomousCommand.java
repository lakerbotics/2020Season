/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * An example command that uses an example subsystem.
 */
public class AutonomousCommand extends SequentialCommandGroup {

	// Commands
	LimelightTrackingAuto limelightTracking;
	ShooterAuto shooterControl;
	IndexerGroupBAuto indexerControl;
	RotationDrive driveControl;

	public AutonomousCommand(DriveTrainSubsystem drivetrain, ShooterSubsystem shooterSubsystem, IndexerSubsystem indexerSubsystem){
		

		limelightTracking = new LimelightTrackingAuto(drivetrain);
		shooterControl = new ShooterAuto(shooterSubsystem);
		indexerControl = new IndexerGroupBAuto(indexerSubsystem, shooterControl);
		driveControl = new RotationDrive(drivetrain);

		addCommands(
			limelightTracking,
			new ParallelCommandGroup(shooterControl, indexerControl),
			driveControl
		);

	}
	
}
