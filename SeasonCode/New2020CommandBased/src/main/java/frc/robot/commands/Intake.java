

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;;

@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

public class Intake extends CommandBase {
	private final IntakeSubsystem m_intake;
	private double speed;
	private boolean polarity;
	
	public Intake(IntakeSubsystem intake) {
		speed = 10;
		polarity = true;
		m_intake = intake;
		
		addRequirements(m_intake);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		m_intake.driveIntake(speed, polarity);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_intake.driveIntake(0, false);
	}
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}