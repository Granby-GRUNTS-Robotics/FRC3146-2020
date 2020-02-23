/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.PneumaticConstants;
import static frc.robot.Constants.PneumaticConstants.kON;
import static frc.robot.Constants.PneumaticConstants.kOFF;


public class ShooterLift extends SubsystemBase {
  /**
   * Creates a new ShooterLift.
   */
  private final Solenoid topSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kTOP_SHOOTER_LIFT_PORT);
  private final Solenoid bottomSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kBOTTOM_SHOOTER_LIFT_PORT);



  public ShooterLift() {

  }

  public final void setPosition(int pos){
    /*Case defines which 
     *position the shooter lift will go to. the pistons are different
     *sizes so we can use different combinations for different positions
     */
    switch (pos) {
      case 0:
        topSolenoid.set(kOFF);
        bottomSolenoid.set(kOFF);
        break;
      case 1:
        topSolenoid.set(kOFF);
        bottomSolenoid.set(kON);
        break;
      case 2:
        topSolenoid.set(kON);
        bottomSolenoid.set(kOFF);
      case 3:
        topSolenoid.set(kON);
        bottomSolenoid.set(kON);
      default:
      topSolenoid.set(kON);
      bottomSolenoid.set(kOFF);
        break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
