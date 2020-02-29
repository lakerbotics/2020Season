/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightTrackingDrive extends CommandBase {

	private final DriveTrainSubsystem drivetrain;
	
	// Limelight
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry limelight_tx = table.getEntry("tx");

	// Proportional tracking
	double heading_error;
	double distance_error;
	double steering_adjust;
	double distance_adjust;
	double KpAim;
	double KpDistance;

	private double left;
	private double right;
	private double tx;
	private double ty;
	
	double Kp = -0.015;
	double min_aim_command = 0.5; //0.36
	
	public LimelightTrackingDrive(DriveTrainSubsystem drivetrain) {
		
		this.drivetrain = drivetrain;
		
		addRequirements(drivetrain);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		trackTargetX();
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		drivetrain.tankDrive(0, 0);
	}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}

	public void trackTargetX() {
		tx = table.getEntry("tx").getDouble(0.0);
		
		heading_error = -tx;
		steering_adjust = 0;
		
		if (tx > 1.4) {
			steering_adjust = Kp * heading_error + min_aim_command;
		}
		else if (tx < -0.6) {
			steering_adjust = Kp * heading_error - min_aim_command;
		}
		
		left = steering_adjust;
		right = -1 * steering_adjust;
		
		if ( (Math.abs(2 - Math.abs(tx))) > 1 ) {
			drivetrain.tankDrive(left, right);
		}
	}
	
	public void trackTarget() {
		tx = table.getEntry("tx").getDouble(0.0);
		ty = table.getEntry("ty").getDouble(0.0);

		KpAim = -0.1;
		KpDistance = -0.1;

		heading_error = -tx;
        distance_error = -ty;
        steering_adjust = 0.0f;

        if (tx > 1.0)
        {
                steering_adjust = KpAim*heading_error - min_aim_command;
        }
        else if (tx < -1.0)
        {
                steering_adjust = KpAim*heading_error + min_aim_command;
        }

        distance_adjust = KpDistance * distance_error;

        left += steering_adjust + distance_adjust;
        right -= steering_adjust + distance_adjust;
	}
}
