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
import frc.robot.Constants;
import frc.robot.Constants.PneumaticConstants;

public class Intake extends SubsystemBase {//this is literally all it is, sorry for the bare-bones

  
  VictorSPX topIntakeMotor = new VictorSPX(Constants.IntakeConstants.kTOP_INTAKE_MOTOR_PORT);

  //Solenoid leftArmSolenoid = new Solenoid(PneumaticConstants.kLEFT_INTAKE_PISTON_PORT);
  //Solenoid rightArmSolenoid = new Solenoid(PneumaticConstants.kRIGHT_INTAKE_PISTON_PORT);

  /**
   * Creates a new Intake.
   */
  public Intake() {

  }
//CONSTANTLY CALLED. BE VERY CAREFUL WITH WHAT YOU PUT IN HERE
  @Override
  public void periodic() {    
    // This method will be called once per scheduler run
  }
  public void setIntakeMotorVelocity(double speed){
    topIntakeMotor.set(ControlMode.Velocity, speed);
  }
}
