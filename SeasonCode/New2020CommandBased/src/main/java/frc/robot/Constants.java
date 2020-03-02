/*------------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.							*/
/* Open Source Software - may be modified and shared by FRC teams. The code		*/
/* must be accompanied by the FIRST BSD license file in the root directory of	*/
/* the project.																	*/
/*------------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 * 
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
	public static final int frontRight = 1;
	public static final int frontLeft = 3;
	public static final int backRight = 2;
	public static final int backLeft = 4;

	public static final int bottomIndexer = 5;
	public static final int topIndexer = 6;

	public static final int mainIntake = 9;

	public static final int leftShooter = 7;
	public static final int rightShooter = 8;

	public static final int leftPistonForward = -1; // TODO Change these values
	public static final int leftPistonReverse = -1;
	public static final int rightPistonForward = -1;
	public static final int rightPistonReverse = -1;

	public static final DutyCycleEncoder leftDriveEncoder = new DutyCycleEncoder(0);
}