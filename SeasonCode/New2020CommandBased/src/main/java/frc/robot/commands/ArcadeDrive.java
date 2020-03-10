/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code 	*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj.DutyCycleEncoder;
// import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.subsystems.DriveTrainSubsystem;

// TODO Modify throttle mixers

public class ArcadeDrive extends CommandBase {
	private final DriveTrainSubsystem driveTrain;
	private final DoubleSupplier x;
	private final DoubleSupplier z;


	// private DutyCycleEncoder motorEncoder;

	/**
	 * Drives robot in arcade mode (using joystick)
	 * 
	 * @param drivetrain Drivtrain subsystem
	 * @param x          Joystick X value
	 * @param z          Joystick Y value
	 */
	public ArcadeDrive(DriveTrainSubsystem drivetrain, DoubleSupplier x, DoubleSupplier z) {
		this.driveTrain = drivetrain;
		this.x = x;
		this.z = z;

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
		driveTrain.drive(-1 * newThrottleMixerX(), newThrottleMixerZ());
		//driveTrain.drive(-1 * x.getAsDouble(), z.getAsDouble());
		// CameraServer.getInstance().
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		driveTrain.drive(0, 0);
	}

	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return false;
	}

	/**
	 * Adjusts raw input of Joystick into value for motor controllers (joystick
	 * front and back)
	 * 
	 * @return Adjusted x value
	 */
	private double throttleMixerX() {
		double xSpeed = x.getAsDouble();
		if ((xSpeed > 0.2) | (xSpeed < -0.2)) {
			return xSpeed * 0.85;
		}
		else if ((xSpeed <= 0.2) & (xSpeed >= -0.2)) {
			return xSpeed * 0;
		}
		else {
			return xSpeed;
		}
	}

	/**
	 * Adjusts raw input of Joystick into value for motor controllers (joystick
	 * rotation)
	 * 
	 * @return Adjusted z value
	 */
	private double throttleMixerZ() {
		double zSpeed = z.getAsDouble();
		if ((zSpeed > 0.2) | (zSpeed < -0.2)) {
			return zSpeed * 0.9;
		}
		else if ((zSpeed <= 0.2) & (zSpeed >= -0.2)) {
			return zSpeed * 1;
		}
		else {
			return zSpeed;
		}

	}

	private double newThrottleMixerX() {
		double xSpeed = x.getAsDouble();
		// if (Math.abs(xSpeed) > 0.7) {
		// 	return xSpeed * 0.85;
		// }
		// else if (Math.abs(xSpeed) > 0.3) {
		// 	return xSpeed * 0.9;
		// }
		// else if (Math.abs(xSpeed) > 0.05) {
		// 	return xSpeed;
		// }
		// else {
		// 	return xSpeed;
		// }
		double time = 2.51230562398;

		double adjusted = 2 / (1 + Math.pow(Math.E, -time * xSpeed)) - 1;

		return adjusted;
	}
	private double newThrottleMixerZ() {
		double zSpeed = z.getAsDouble();
		// if (Math.abs(xSpeed) > 0.7) {
		// 	return xSpeed * 0.85;
		// }
		// else if (Math.abs(xSpeed) > 0.3) {
		// 	return xSpeed * 0.9;
		// }
		// else if (Math.abs(xSpeed) > 0.05) {
		// 	return xSpeed;
		// }
		// else {
		// 	return xSpeed;
		// }
		double time = 2.51230562398;

		double adjusted = 2 / (1 + Math.pow(Math.E, -time * zSpeed)) - 1;

		return adjusted * 0.7;
	}
}
