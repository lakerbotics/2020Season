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

public class PistonLift extends SubsystemBase {

  private DoubleSolenoid gigantor;         // Primary Lift
  private DoubleSolenoid miniGigantor;    // Inverted Pusher

  public PistonLift() {

    // Remove and change these parameters

    int forwardChannel = 0;
    int reverseChannel = 0;

    gigantor = new DoubleSolenoid(forwardChannel, reverseChannel);
    miniGigantor = new DoubleSolenoid(forwardChannel, reverseChannel);

  }

  public void gigantorExtend(){
    gigantor.set(DoubleSolenoid.Value.kForward);
  }
  public void gigantorRetract(){
    gigantor.set(DoubleSolenoid.Value.kReverse);
  }

  public void miniGigantorEtend(){
    miniGigantor.set(DoubleSolenoid.Value.kForward);
  }
  public void miniGigantorRetract(){
    miniGigantor.set(DoubleSolenoid.Value.kReverse);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
