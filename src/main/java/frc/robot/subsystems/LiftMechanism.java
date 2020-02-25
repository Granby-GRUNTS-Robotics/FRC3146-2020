/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LiftConstants;
import frc.robot.Constants.PneumaticConstants;;

public class LiftMechanism extends SubsystemBase {
  /**
   * Creates a new LiftMechanism.
   */

  private final VictorSPX mainLiftSpx = new VictorSPX(LiftConstants.kLIFT_MOTOR_PORTS[0]);
  private final VictorSPX slaveLiftSpx = new VictorSPX(LiftConstants.kLIFT_MOTOR_PORTS[1]);
  private final Solenoid upSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kFIRST_CLIMB_PORT);
  private final Solenoid downSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kSECOND_CLIMB_PORT);
  private final Solenoid extraSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kThird_CLIMB_PORT);
  

  public LiftMechanism() {

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
