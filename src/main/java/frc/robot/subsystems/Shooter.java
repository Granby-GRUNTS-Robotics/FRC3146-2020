/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends PIDSubsystem {
  /**
   *I defined the Sparkmax's here so that only the subsystem can use them. I didn't group them together because we might want 
   *to have them running at different speeds
   */
  
    

  private final static CANSparkMax topIntakeMotor = new CANSparkMax(ShooterConstants.kTOP_SHOOTER_MOTOR_PORT,
      MotorType.kBrushless);
  private final CANSparkMax bottomIntakeMotor = new CANSparkMax(ShooterConstants.kBOTTOM_SHOOTER_MOTOR_PORT,
      MotorType.kBrushless);

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    super(new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD));
    bottomIntakeMotor.setInverted(true);
  }

  public double getInEncoderUnits(double Speed){
    double encoder_speed = Speed;
    return encoder_speed;
  }
  


  //We should create a method(?) Idk why.

  @Override
  public void periodic() {
    useOutput(getController().calculate(getMeasurement()), getController().getSetpoint());
    // This method will be called once per scheduler run
  }

  @Override
  protected void useOutput(double output, double setpoint) {
    // TODO Auto-generated method stub

  }

  @Override
  protected double getMeasurement() {
    // TODO Auto-generated method stub
    return 0;
  }
}
