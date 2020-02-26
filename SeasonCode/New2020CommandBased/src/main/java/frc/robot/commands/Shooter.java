

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ShooterSubsystem;;


public class Shooter extends CommandBase {
	@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
	
	private final ShooterSubsystem m_shooter;
	
	private final double targetVelocity;
	private double speed;
	private boolean flag;
	
	public Shooter(ShooterSubsystem shooter) {
		
		targetVelocity = 100; // Number subject to testing
		speed = 0;
		m_shooter = shooter;
		
		addRequirements(m_shooter);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		m_shooter.drive(bangBang());
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_shooter.drive(0);
	}
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
	
	public double bangBang() {
		
		/* if (encoder.getVelocity > targetVelocity) {
			speed = 0;
		}
		else {
			speed = voltageSpeedThatIsSlightlyGreaterThanTarget;
		}
		*/
		
		// To get the velocity from the encoder, you must be using the phoenix/ctre encoder class (requires talons)
		
		return speed;
	}
	
}
