/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.PneumaticConstants;
import static frc.robot.Constants.PneumaticConstants.kON;
import static frc.robot.Constants.PneumaticConstants.kOFF;

public class Intake extends SubsystemBase {


  VictorSPX intakeMotor = new VictorSPX(Constants.IntakeConstants.kINTAKE_MOTOR_PORT);

  Solenoid backArmSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kBACK_INTAKE_PORT);
  Solenoid frontArmSolenoid = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kFRONT_INTAKE_PORT);

  /**
   * Creates a new Intake.
   */
  public Intake() {
    CameraServer.getInstance().startAutomaticCapture();

  }
//CONSTANTLY CALLED. BE VERY CAREFUL WITH WHAT YOU PUT IN HERE
  @Override
  public void periodic() {    
    // This method will be called once per scheduler run
  }
  
  public void setIntakeMotorVelocity(double percent){
    intakeMotor.set(ControlMode.PercentOutput, percent);
  }

  public void up(){
    backArmSolenoid.set(kOFF);
    frontArmSolenoid.set(!kON);
    System.out.println("intake up");
  }

  public void soft(){
    backArmSolenoid.set(kON);
    frontArmSolenoid.set(!kON);
    System.out.println("intake soft");
  }

  public void off(){
    backArmSolenoid.set(kOFF);
    frontArmSolenoid.set(!kOFF);
    System.out.println("intake off");
  }

  public void down(){
    backArmSolenoid.set(kON);
    frontArmSolenoid.set(!kOFF);
    System.out.println("intake down");
  }

  public String getPosition(){
    return "";
    
  }
}
