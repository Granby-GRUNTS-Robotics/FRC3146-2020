/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   */
  private static Servo cameraServo = new Servo(0);

  public Vision() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public void setPosition(double degrees){
    cameraServo.set(degrees);
  }

  // Called when the command is initially scheduled.
  @Override
  public void periodic() {
    // TODO Auto-generated method stub
  }
}
