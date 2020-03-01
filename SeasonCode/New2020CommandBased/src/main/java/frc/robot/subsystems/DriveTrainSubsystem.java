/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.fasterxml.jackson.core.json.DupDetector;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;


public class DriveTrainSubsystem extends SubsystemBase {
	
	private WPI_VictorSPX frontRight;
	private WPI_VictorSPX frontLeft;
	private WPI_VictorSPX backRight;
	private WPI_VictorSPX backLeft;
	
	private SpeedController right;
	private SpeedController left;
	
	private final DifferentialDrive drive;


	private double goal;
	private final Encoder m_encoder;
	private final TrapezoidProfile.Constraints m_constraints = new TrapezoidProfile.Constraints(1.75, 0.1);
	private final ProfiledPIDController m_controller = new ProfiledPIDController(1.2, 1, 1, m_constraints);

	// TODO Implement PID into drivetrain
	
	public DriveTrainSubsystem() {
		
		frontRight = new WPI_VictorSPX(Constants.frontRight);
		frontLeft = new WPI_VictorSPX(Constants.frontLeft);
		backRight = new WPI_VictorSPX(Constants.backRight);
		backLeft = new WPI_VictorSPX(Constants.backLeft);
		
		right = new SpeedControllerGroup(frontRight, backRight);
		left = new SpeedControllerGroup(frontLeft, backLeft);
		
		drive = new DifferentialDrive(left, right);

		m_encoder = new Encoder(5, 4);



	}
	
	public void drive(double x, double z)
	{
		drive.arcadeDrive(x, z);
	} 
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}

	public void setTarget(double rotations) {
		m_encoder.reset();
		m_encoder.setDistancePerPulse(Math.PI * 7.9/360);;
		this.goal = rotations;
		System.out.print("INIT HERE");
	}

	public void rotationDrive() {
		m_controller.setGoal(this.goal);
		left.set(m_controller.calculate(m_encoder.getDistance()));
		right.set(m_controller.calculate(m_encoder.getDistance()));

		System.out.println(m_encoder.getDistance());
	}
	
	@Override
	public void periodic() {
	}
}
