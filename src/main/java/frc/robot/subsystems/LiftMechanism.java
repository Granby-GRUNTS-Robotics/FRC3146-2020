/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.PneumaticConstants.kOFF;
import static frc.robot.Constants.PneumaticConstants.kON;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PneumaticConstants;

public class LiftMechanism extends SubsystemBase {
  /**
   * Creates a new LiftMechanism.
   */
  private final AnalogInput winchEncoder = new AnalogInput(3);
  private final Servo ratchetServo = new Servo(9);
  private final VictorSPX mainLiftSpx = new VictorSPX(8);
  private final VictorSPX slaveLiftSpx = new VictorSPX(9);
  private final Solenoid upSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kFIRST_CLIMB_PORT);
  private final Solenoid downSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kSECOND_CLIMB_PORT);
  

  public LiftMechanism() {
    slaveLiftSpx.follow(mainLiftSpx);
    setServo("no");  
  }

  public void setWinch(double speed) {
    mainLiftSpx.set(ControlMode.PercentOutput, speed);
  }

  public void setServo(String ratcheting){
    if (ratcheting=="no"){
      ratchetServo.set(180);
    }else if (ratcheting == "yes"){
      ratchetServo.set(0);
    }
  }

  public double getLiftEncoder(){
    return (((double)winchEncoder.getValue())-4)/400;
  }

  public void up(){
    upSolenoid.set(kON);
    downSolenoid.set(kOFF);
  }

  public void off(){
    upSolenoid.set(kOFF);
    downSolenoid.set(kOFF);
  }

  public void down(){
    upSolenoid.set(kOFF);
    downSolenoid.set(kON);
  }

  @Override
  public void periodic() {
    System.out.println(getLiftEncoder());
    // This method will be called once per scheduler run
  }
}
