/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;

public class Indexer extends CommandBase {
	@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
	private final IndexerSubsystem indexer;
	
	private double speed;
	private boolean polarity;
	private Joystick Joy;

	public Indexer(IndexerSubsystem indexer, Joystick Joy, boolean polarity) {
		this.indexer = indexer;
		
		this.polarity = polarity;
		this.Joy = Joy;
		
		addRequirements(indexer);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		speed = Joy.getX();
		indexer.indexerDrive(speed, polarity);
	}
	
	@Override
	public void end(boolean interrupted) {
		indexer.indexerDrive(0, polarity);
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
}
