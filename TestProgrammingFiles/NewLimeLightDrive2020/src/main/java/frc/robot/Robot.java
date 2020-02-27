/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//import javax.management.timer.Timer;
import edu.wpi.first.wpilibj.Timer ;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.Counter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private PWMVictorSPX m_frontRight;
  private PWMVictorSPX m_frontLeft;
  private PWMVictorSPX m_backRight;
  private PWMVictorSPX m_backLeft;
  private DifferentialDrive m_drive;

  private Joystick Joy;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  
  NetworkTableEntry tv = table.getEntry("tv");

  double Targetx = tx.getDouble(0.0);
  double Targety = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);
  
  Timer myTimer;

  double distanceToTarget;
  boolean x_aligning;
  boolean y_aligning;
  int alignDepth;
  double accuracyDivisor;

  private Counter counter;
  private static final double offset = 0.0;
  private static final double factor = 1;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_frontRight = new PWMVictorSPX(0);
    m_frontLeft = new PWMVictorSPX(2);
    m_backRight = new PWMVictorSPX(1);
    m_backLeft = new PWMVictorSPX(3);

    x_aligning = false;

    Joy = new Joystick(0);
    myTimer = new Timer();

    final SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_backLeft);
    final SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_backRight);

    m_drive = new DifferentialDrive(m_left, m_right);

    distanceToTarget = 0;

    counter = new Counter(9);
    counter.setMaxPeriod(10.0);
    counter.setSemiPeriodMode(true);
    counter.reset();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    if (!x_aligning  && !y_aligning)
    {
      m_drive.arcadeDrive(Joy.getY() , Joy.getZ() *-1);
    }

    // Show status of align modes
    

    //read values periodically
    area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", tx.getDouble(0.0));
    SmartDashboard.putNumber("LimelightY", ty.getDouble(0.0));
    SmartDashboard.putNumber("LimelightArea", area);

    if (Joy.getRawButtonPressed(2))
    {
      distanceToTarget = getDistance();
      SmartDashboard.putNumber("Estimated Distance", distanceToTarget);
      
    }
    if (Joy.getRawButtonPressed(5))
    {
      alignX();
    }

    if (Joy.getRawButtonPressed(6))
    {
      alignY();
    }

    if (Joy.getTriggerPressed())
    {
      /*alignDepth = 0;
      while ( ((Math.abs(tx.getDouble(0.0)) > 1) || (Math.abs(ty.getDouble(0.0)) > 1)) || (tx.getDouble(0.0) == 0 & ty.getDouble(0.0) == 0) || (alignDepth < 3))
      {
        alignX();
        alignY();
        alignDepth ++;
      }
      System.out.println("FULL ALIGN");*/

      System.out.println(lidarTest());
    }
      
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private double getDistance()
  {

    // Constants
    // h2: center of target (height from groudn)
    final double h2 = 86.7;
    // h1: height of top of camera
    final double h1 = 17.6;

    // a1: angle of camera (relative to chasis)
    final double a1 = 36.175;
    // a2: angle of target relative to a1
    final double a2 = ty.getDouble(0.0);

    // distance: distance from camera to base of target
    final double distance = ((h2 - h1) / Math.tan(Math.toRadians(a1 + a2)));


    
    final double d = Math.sqrt(Math.pow(h2-h1,2)+Math.pow(distance,2));
    SmartDashboard.putNumber("Py Distance", d);

    return(distance);

  }

  private void setAlignView()
  {
    if ( x_aligning )
    {
      SmartDashboard.putString("X Align Status", "ACTIVE");
    }
    else
    {
      SmartDashboard.putString("X Align Status", "INACTIVE");
    }
    if ( y_aligning )
    {
      SmartDashboard.putString("Y Align Status", "ACTIVE");
    }
    {
      SmartDashboard.putString("Y Align Status", "INACTIVE");
    }
  }
  private void alignX()
  {
    x_aligning = true;
      System.out.println("X Aligning mode ACTIVE");
      setAlignView();
      while ( Math.abs(tx.getDouble(0.0)) > 1)
      {
        if (tv.getDouble(0.0) == 0)
        {
          break;
        }
        setAlignView();
        if (Math.abs(tx.getDouble(0.0)) < 1)
        {
          break;
        }

        if (Math.abs(tx.getDouble(0.0)) < 3)
        {
          accuracyDivisor = 60;
        }
        if (Math.abs(tx.getDouble(0.0)) < 5)
        {
          accuracyDivisor = 45;
        }
        else
        {
          accuracyDivisor = 35;
        }
        Targetx = tx.getDouble(0.0);
        if ( (tx.getDouble(0.0) > 0) )
        {
          // move to left
          m_frontRight.set(-0.6);
          m_frontLeft.set(-0.6);
          Timer.delay(Math.abs(Targetx)/accuracyDivisor);
          System.out.println("MOVE RIGHT");

        
        }
        else if ( Targetx < 0)
        {
          // move to right
          m_frontRight.set(0.55);
          m_frontLeft.set(0.55);
          Timer.delay(Math.abs(Targetx)/accuracyDivisor);
          System.out.println("MOVE LEFT");
          
        }

        Timer.delay(0.5);
      }
      x_aligning = false;
      System.out.println("X Aligning mode EXIT");
  }
  private void alignY()
  {
    y_aligning = true;
      System.out.println("Y Aligning mode ACTIVE");
      setAlignView();
      while ( Math.abs(ty.getDouble(0.0)) > 1)
      {
        if (tv.getDouble(0.0) == 0)
        {
          break;
        }
        setAlignView();
        if (ty.getDouble(0.0) > 0)
        {
          // move forward
          m_frontRight.set(0.25);
          m_frontLeft.set(-0.25);
          System.out.println("FORWARD");
          
        }
        else if (ty.getDouble(0.0) < 0)
        {
          // move forward
          m_frontRight.set(-0.25);
          m_frontLeft.set(0.25);
          System.out.println("BACKWARDS");
        }

        myTimer.delay(0.3);
      }
      System.out.println("Y Aligning mode EXIT");
      y_aligning = false;
  }

  public double lidarTest() {
    System.out.println(counter.get());
    if (counter.get() < 1) {
        return 0.0;
    }
    return (counter.getPeriod() * factor + offset); // Use lin reg to obtain values
  }

}