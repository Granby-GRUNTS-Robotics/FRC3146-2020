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

public class ShooterLift extends SubsystemBase {
  /**
   * Creates a new ShooterLift.
   */
  private final Solenoid leftBottomSolenoid = new Solenoid(PneumaticConstants.kLOW_LEFT_LIFT_PORT);
  private final Solenoid rightBottomSolenoid = new Solenoid(PneumaticConstants.kLOW_RIGHT_LIFT_PORT);


  public ShooterLift() {

  }

  public final void up(){
    leftBottomSolenoid.set(PneumaticConstants.kSHOOTER_UP);
    rightBottomSolenoid.set(PneumaticConstants.kSHOOTER_UP);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
