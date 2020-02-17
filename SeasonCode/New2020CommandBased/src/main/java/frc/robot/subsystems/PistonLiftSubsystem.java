/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class PistonLiftSubsystem extends SubsystemBase {

  private DoubleSolenoid piston1;
  private DoubleSolenoid piston2;

  public PistonLiftSubsystem() {

    // Remove and change these parameters

    int forwardChannel = 0;
    int reverseChannel = 0;

    piston1 = new DoubleSolenoid(forwardChannel, reverseChannel);
    piston2 = new DoubleSolenoid(forwardChannel, reverseChannel);

  }

  public void extend() {
    piston1.set(Value.kForward);
    piston2.set(Value.kForward);
  }

  public void retract() {
    piston1.set(Value.kReverse);
    piston2.set(Value.kReverse);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
