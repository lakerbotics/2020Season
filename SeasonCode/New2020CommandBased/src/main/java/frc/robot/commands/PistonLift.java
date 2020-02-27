/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PistonLiftSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class PistonLift extends CommandBase {	
	private final PistonLiftSubsystem pistonLift;
	
	public PistonLift(PistonLiftSubsystem pistonLift) {
		this.pistonLift = pistonLift;
		
		addRequirements(pistonLift);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		pistonLift.extend();
		
		// TODO Make piston actuate on command not automatically
		Timer.delay(3);
		
		pistonLift.retract();
		
	}
	
	
	@Override
	public void end(boolean interrupted) {
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
}
