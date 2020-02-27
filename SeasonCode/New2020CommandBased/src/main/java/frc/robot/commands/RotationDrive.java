

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrainSubsystem;


public class RotationDrive extends CommandBase {
	private final DriveTrainSubsystem driveTrain;
	
	private double left;
	private double right;
	
	// Proportional Tracking
	double heading_error;
	double distance_error;
	double steering_adjust;
	double distance_adjust;
	
	double Kp = -0.015;
	double min_aim_command = 0.36;
	
	// Shaft data
	private int shaftCurrent;
	private int shaftTarget;
	private int shaftDifference;
	
	public RotationDrive(DriveTrainSubsystem drivetrain, double rotations) {
		
		driveTrain = drivetrain;
		
		shaftCurrent = 0;
		shaftTarget = (int) rotations + shaftCurrent;
		
		addRequirements(driveTrain);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		
		trackTargetShaft();
		driveTrain.tankDrive(left, right);
	}
	
	@Override
	public void end(boolean interrupted) {
		driveTrain.tankDrive(0, 0);
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
	
	public void trackTargetShaft() {
		
		shaftCurrent = 0;
		shaftDifference  = shaftTarget - shaftCurrent;
		
		heading_error = shaftDifference;
		steering_adjust = 0;
		
		if ( shaftDifference > 1) {
			steering_adjust = Kp * heading_error + min_aim_command;
		}
		else if (shaftDifference < 1) {
			steering_adjust = Kp * heading_error - min_aim_command;
		}
		
		left = steering_adjust;
		right = -1 * steering_adjust;
		
		if (shaftDifference > 1) {
			driveTrain.tankDrive(left, right);
		}
	}
	
}
