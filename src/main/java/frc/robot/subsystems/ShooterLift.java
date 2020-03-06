/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import static frc.robot.Constants.PneumaticConstants;
import static frc.robot.Constants.ShooterConstants.kSON;
import static frc.robot.Constants.ShooterConstants.kSOFF;


public class ShooterLift extends SubsystemBase {
  /**
   * Creates a new ShooterLift.
   */
  private final Solenoid topSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kTOP_SHOOTER_LIFT_PORT);
  private final Solenoid bottomSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kBOTTOM_SHOOTER_LIFT_PORT);

  //private static final Servo cameraServo = new Servo(8);


  public ShooterLift() {

  }

  public final void setPosition(int pos){
    /*Case defines which 
     *position the shooter lift will go to. the pistons are different
     *sizes so we can use different combinations for different positions
     *HARDWARE IS FLIPPED SO ALL SOLENOIDS ARE REVERSED
     */
    switch (pos) {
      case 0:
        topSolenoid.set(kSOFF);
        bottomSolenoid.set(kSOFF);
        //setServo(ShooterConstants.kZERO_SERVO_POS);
        break;
      case 1:
        topSolenoid.set(kSOFF);
        bottomSolenoid.set(kSON);
        //setServo(ShooterConstants.kONE_SERVO_POS);
        break;
      case 2:
        topSolenoid.set(kSON);
        bottomSolenoid.set(kSOFF);
        //setServo(ShooterConstants.kTWO_SERVO_POS);
        break;
      case 3:
        topSolenoid.set(kSON);
        bottomSolenoid.set(kSON);
        //setServo(ShooterConstants.kTHREE_SERVO_POS);
        break;
      default:
      topSolenoid.set(kSOFF);
      bottomSolenoid.set(kSOFF);
      break;
    }
  }

  /*public void setServo(double degrees){
    cameraServo.set(degrees);
  }*/

  @Override
  public void periodic() {
    //System.out.println(cameraServo.get());
    // This method will be called once per scheduler run
  }
}
