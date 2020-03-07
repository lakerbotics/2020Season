/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.cscore.HttpCamera;

/**
 * An example command that uses an example subsystem.
 */

public class CameraManagement extends CommandBase {
	@SuppressWarnings("unused")
	/**
	 * Example subsystem
	 * @param subsystem
	 */

	UsbCamera camera1;
	UsbCamera camera2;
	HttpCamera limelight;
	VideoSink server1;
	VideoSink server2;

	VideoCamera[] cameraArray;

	boolean switchVar = true;

	public CameraManagement() {
		
		// TODO Check if camera server actually work
		camera1 = CameraServer.getInstance().startAutomaticCapture();
		camera2 = CameraServer.getInstance().startAutomaticCapture();
		limelight = new HttpCamera("limelight", "http://limelight.local:5800/");
			
		server1 = CameraServer.getInstance().getServer();
		//server2 = CameraServer.getInstance().getServer();

		camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
		camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
		limelight.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

		server1.setSource(camera1);

		cameraArray = new VideoCamera[]{camera1, camera2};

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

		//]server2.setSource(limelight);

		if (switchVar) {
			server1.setSource(camera1);
			switchVar = !switchVar;
		}
		else if (switchVar == false){
			server1.setSource(camera2);
			switchVar = !switchVar;
		}

		this.cancel();
	}
	
	/**
	 * Called when the command ends or is interrupted
	 */
	@Override
	public void end(boolean interrupted) {
	}
	
	/**
	 * Returns true when command is ended
	 */
	@Override
	public boolean isFinished() {
		return false;
	}
}
