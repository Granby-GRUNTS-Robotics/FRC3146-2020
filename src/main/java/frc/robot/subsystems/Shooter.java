/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.PneumaticConstants.kOFF;
import static frc.robot.Constants.PneumaticConstants.kON;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PneumaticConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  /**
   * I defined the Sparkmax's here so that only the subsystem can use them. I
   * didn't group them together because we might want to have them running at
   * different speeds
   */

  private static final Solenoid ballStop = new Solenoid(PneumaticConstants.kPCM_Port, PneumaticConstants.kSHOOTER_STOP_PORT);

  private final static CANSparkMax leftShooterMotor = new CANSparkMax(ShooterConstants.kLEFT_SHOOTER_MOTOR_PORT,
      MotorType.kBrushless);
  private final static CANSparkMax rightShooterMotor = new CANSparkMax(ShooterConstants.kRIGHT_SHOOTER_MOTOR_PORT,
      MotorType.kBrushless);

  private final static CANEncoder leftEncoder = new CANEncoder(leftShooterMotor);
  private final static CANEncoder rightEncoder = new CANEncoder(rightShooterMotor);

  private static final CANPIDController shooterController = new CANPIDController(leftShooterMotor);

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    rightShooterMotor.setInverted(false);
    leftShooterMotor.setInverted(false);
    rightShooterMotor.follow(leftShooterMotor, true); 
    leftShooterMotor.getPIDController().setP(0, 0);
    leftShooterMotor.getPIDController().setD(0, 0);
    rightEncoder.setPosition(0.0);
    leftEncoder.setPosition(0.0);
  }

  public void down(){
    ballStop.set(kON);
  }

  public void up(){
    ballStop.set(kOFF);
  }
//TODO
  public double getInEncoderUnits(double Speed){
    double encoder_speed = Speed/1.25;
    return encoder_speed;
  }

  public double getInRPM(double Speed){
    double encoder_speed = Speed*1.25;
    return encoder_speed;
  }

  public void setSetpoint(double setpoint){
    shooterController.setReference(setpoint, ControlType.kVelocity, 0);
  }

  public double getSpeed(){
    return leftEncoder.getVelocity();
  }
  


  //We should create a method(?) Idk why. Maybe we want to.

  @Override
  public void periodic() {
  }

}
