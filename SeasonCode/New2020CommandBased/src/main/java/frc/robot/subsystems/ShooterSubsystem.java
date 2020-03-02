/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code 	*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

	private TalonSRX left;
	private TalonSRX right;

	/**
	 * Creates new shooter subsystem
	 */
	public ShooterSubsystem() {

		// Master-Slave relationship ->
		// https://en.wikipedia.org/wiki/Master/slave_(technology)
		left = new TalonSRX(Constants.leftShooter); // Master
		right = new TalonSRX(Constants.rightShooter); // Slave

		left.selectProfileSlot(0, 0);
		left.config_kF(0, 0.248); // 0.248
		left.config_kP(0, 0);
		left.config_kI(0, 0);
		left.config_kD(0, 0);

		right.setInverted(true);
		right.follow(left);

	}

	/**
	 * Runs the shooter at max speed.
	 * 
	 * @param activate Determines whether or not to enable shooter. If activate,
	 *                 shooter will run, otherwise, shooter will stop.
	 */
	public void drive(boolean activate) {

		if (activate) {
			left.set(ControlMode.Position, -4096);
			SmartDashboard.putNumber("Shooter Vel", left.getSelectedSensorVelocity());
		}
		else {
			left.set(ControlMode.PercentOutput, 0);
		}

	}

	/**
	 * Gets the speed of shooter
	 * 
	 * @return Shooter speed
	 */
	public int getSpeed() {
		return left.getSelectedSensorVelocity();
	}

	/**
	 * Called once per scheduler run
	 */
	@Override
	public void periodic() {
	}
}
