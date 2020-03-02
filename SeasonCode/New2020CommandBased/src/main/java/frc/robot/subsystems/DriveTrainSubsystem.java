/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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

	private TrapezoidProfile.State goal;
	private boolean goalHasBeenSet;
	private final Encoder encoder;
	private final TrapezoidProfile.Constraints constraints;
	private final ProfiledPIDController controller;

	// TODO Implement PID & Encoder

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
		constraints = new TrapezoidProfile.Constraints(1.75, 1);
		controller = new ProfiledPIDController(0.2, 0, 0.0, constraints); // 0.2, 0, 0

		encoder = new Encoder(5, 4);

		goalHasBeenSet = false;
	}

	/**
	 * Drives in arcade
	 * 
	 * @param x Joystick x
	 * @param z Joystick z
	 */
	public void drive(double x, double z) {
		drive.arcadeDrive(x, z);
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

	/**
	 * Sets target for autonomous drive
	 * 
	 * @param rotations Rotations to drive in autonomous
	 */
	public void setTarget(double rotations) {
		encoder.reset();
		encoder.setDistancePerPulse(Math.PI * 8 * 1 / 360);
		goal = new TrapezoidProfile.State(5, 0);
		goalHasBeenSet = true;

		System.out.print("INIT HERE");
	}

	/**
	 * Drives autonomously
	 */
	public void rotationDrive() {
		if (!goalHasBeenSet) {
			return;
		}

		controller.setGoal(24.83 * 1); //12 is around half //24 is full

		// TODO Fix this
		left.set(-controller.calculate(encoder.getDistance()) * 0);
		right.set(-controller.calculate(encoder.getDistance()) * 0);
		System.out.println(encoder.getDistance());

		if (controller.atGoal()) {	
				System.out.println(controller.atGoal());}
				System.out.println(encoder.getDistance());
				System.out.println(encoder.getRaw());
		System.out.println("Projected " + controller.calculate(encoder.getDistance()));
	}

	/**
	 * Called once per scheduler run
	 */
	@Override
	public void periodic() {
	}
}
