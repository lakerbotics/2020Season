

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ShooterSubsystem;;


public class Shooter extends CommandBase {	
	private final ShooterSubsystem shooter;
	
	private final double targetVelocity;
	
	public Shooter(ShooterSubsystem shooter) {
		this.shooter = shooter;
		targetVelocity = 35000; // Number subject to testing
		
		addRequirements(shooter);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		shooter.drive(targetVelocity);
	}
	
	@Override
	public void end(boolean interrupted) {
		shooter.drive(0);
	}
	@Override
	public boolean isFinished() {
		return false;
	}
}
