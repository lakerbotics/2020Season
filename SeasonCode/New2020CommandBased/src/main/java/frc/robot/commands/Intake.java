
/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;

public class Intake extends CommandBase {
	private final double INTAKE_SPEED = 0.4; //0.8, 9.7
	private final IntakeSubsystem intake;
	private boolean polarity;
	private final Joystick Joy;
	private final double PROXIMITYTHRESHOLD;

	private ColorSensorV3 colorSensor;
	private final I2C.Port i2cPort = I2C.Port.kOnboard;

	/**
	 * Activates Intake subsystem in certain direction (speed is constant)
	 * 
	 * @param intake   Intake subsystem
	 * @param polarity Direction of intake (boolean)
	 */
	public Intake(IntakeSubsystem intake, boolean polarity, Joystick Joy) {
		this.intake = intake;
		this.polarity = polarity;
		this.Joy = Joy;
		this.PROXIMITYTHRESHOLD = 93;

		colorSensor = new ColorSensorV3(i2cPort);

		addRequirements(intake);
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
		if ( colorSensor.getProximity() >= PROXIMITYTHRESHOLD ) {
			intake.driveIntake(0.3, true);
		}
		else {
			intake.driveIntake(calculateSpeed(), polarity);
		}
		
	}

	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
		intake.driveIntake(0, false);

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
			return (JoySpeed * 0.8);
		}
		else {
			return (0.5);
		}
	}
}