/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;
import frc.robot.Constants.PneumaticConstants;
import static frc.robot.Constants.PneumaticConstants.kON;
import static frc.robot.Constants.PneumaticConstants.kOFF;

public class LiftMechanism extends SubsystemBase {
  /**
   * Creates a new LiftMechanism.
   */

  private final VictorSPX mainLiftSpx = new VictorSPX(8);
  private final VictorSPX slaveLiftSpx = new VictorSPX(9);
  private final Solenoid upSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kFIRST_CLIMB_PORT);
  private final Solenoid downSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kSECOND_CLIMB_PORT);
  private final Solenoid extraSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kThird_CLIMB_PORT);
  

  public LiftMechanism() {
    slaveLiftSpx.follow(mainLiftSpx);
    mainLiftSpx.configVoltageCompSaturation(LiftConstants.voltageMax);
  }

  public void winchControl(double speed) {
    mainLiftSpx.set(ControlMode.PercentOutput, speed);
  }

  public void up(){
    upSolenoid.set(kON);
    downSolenoid.set(kOFF);
    extraSolenoid.set(kOFF);
  }

  public void upp(){
    upSolenoid.set(kON);
    extraSolenoid.set(kON);
    downSolenoid.set(kOFF);
  }

  public void offNotExtra() {
    upSolenoid.set(kOFF);
    downSolenoid.set(kOFF);
    extraSolenoid.set(kON);
  }

  public void off(){
    upSolenoid.set(kOFF);
    downSolenoid.set(kOFF);
    extraSolenoid.set(kOFF);
  }

  public void down(){
    upSolenoid.set(kOFF);
    extraSolenoid.set(kOFF);
    downSolenoid.set(kON);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
