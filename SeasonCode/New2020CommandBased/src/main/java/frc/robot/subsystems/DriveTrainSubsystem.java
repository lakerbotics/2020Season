/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
// import com.fasterxml.jackson.core.json.DupDetector;
// import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
// import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
	
	private WPI_VictorSPX frontRight;
	private WPI_VictorSPX frontLeft;
	private WPI_VictorSPX backRight;
	private WPI_VictorSPX backLeft;
	
	private SpeedController right;
	private SpeedController left;
	
	private final DifferentialDrive drive;

	private double polarity;
	
	private double timeGoal;
	private boolean running;
//	private final Encoder encoder;
	int encoderDistanceLimit;
	
	
	/**
	* Creates drivetrain subsystem
	*/
	public DriveTrainSubsystem() {
		
		frontRight = new WPI_VictorSPX(Constants.frontRight);
		frontLeft = new WPI_VictorSPX(Constants.frontLeft);
		backRight = new WPI_VictorSPX(Constants.backRight);
		backLeft = new WPI_VictorSPX(Constants.backLeft);
		
		right = new SpeedControllerGroup(frontRight, backRight);
		left = new SpeedControllerGroup(frontLeft, backLeft);
		
		drive = new DifferentialDrive(left, right);
		
//		encoder = new Encoder(5, 4); //5, 4
		encoderDistanceLimit = 200;
//		encoder.reset();
	
		running = true;
		polarity = 1;

	}
	
	/**
	* Drives in arcade
	* 
	* @param x Joystick x
	* @param z Joystick z
	*/
	public void drive(double x, double z) {
		drive.arcadeDrive(x * polarity, z * polarity);
	}
	
	/**
	* Drives in tank
	* 
	* @param leftSpeed  Left motor group speed
	* @param rightSpeed Right motor group speed
	*/
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void setTankDrive(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
	}
	/**
	* Sets target for autonomous drive
	* 
	* @param rotations Rotations to drive in autonomous
	*/
	public void setTarget() {
//		encoder.reset();
//		encoder.setDistancePerPulse(Math.PI * 8 / 360);
		
		timeGoal = System.currentTimeMillis();
	}
	
	/**
	* Drives autonomously
	*/
	public void autoDrive() {
		drive.arcadeDrive(0.8, 0);
	}

	/**
	 * 
	 */
	public boolean getAutoDrive() {
		return this.running;
	}

	public void pause() {
		drive(0, 0);
	}
	
	/**
	 * Reverses the polarity
	 */
	public void reverse() {
		this.polarity *= -1;
	}

	/**
	* Called once per scheduler run
	*/
	@Override
	public void periodic() {
	}
}
