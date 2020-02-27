/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj.DutyCycleEncoder;
// import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.subsystems.DriveTrainSubsystem;

public class ArcadeDrive extends CommandBase {	
	private final DriveTrainSubsystem driveTrain;
	private final DoubleSupplier x;
	private final DoubleSupplier z;
	
	// TODO Setup drivetrain encoder
	// private DutyCycleEncoder motorEncoder;
	
	public ArcadeDrive(DriveTrainSubsystem drivetrain, DoubleSupplier x, DoubleSupplier z) {
		this.driveTrain = drivetrain;
		this.x = x;
		this.z = z;
		
		addRequirements(drivetrain);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		driveTrain.drive(-1 * throttleMixerY(), throttleMixerZ());
		//CameraServer.getInstance().
	}
	
	@Override
	public void end(boolean interrupted) {
		driveTrain.drive(0, 0);
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
	
	private double throttleMixerY() {
		double xSpeed = x.getAsDouble();
		if ((xSpeed > 0.2) | (xSpeed < -0.2)) {
			return xSpeed * 0.9;
		}
		else if ((xSpeed <= 0.2) & (xSpeed >= -0.2)) {
			return xSpeed * 0;
		}
		else {
			return xSpeed;
		}
	}
	
	private double throttleMixerZ() {
		double zSpeed = z.getAsDouble();
		if ((zSpeed > 0.2) | (zSpeed < -0.2)) {
			return zSpeed * 0.7;
		}
		else if ((zSpeed <= 0.2) & (zSpeed >= -0.2)) {
			return zSpeed * 0;
		}
		else {
			return zSpeed;
		}
	}
}

