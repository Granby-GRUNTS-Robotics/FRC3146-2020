/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.SensorConstants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public final PigeonIMU pigeonIMU= new PigeonIMU(SensorConstants.kPIDGEON_PORT);

  public double m_fwd;
  public double m_rot;
  

  // Creates left and right speedcontroller groups for individual control. Spark
  // max's defined here to be able to limit each motor's voltage
  private final CANSparkMax leftDriveMotor = new CANSparkMax(DriveTrainConstants.kLEFT_MOTOR_PORT,
      MotorType.kBrushless);
  private final CANSparkMax leftSlaveMotor = new CANSparkMax(DriveTrainConstants.kLEFT_SLAVE_MOTOR_PORT,
      MotorType.kBrushless);
  private final CANSparkMax rightDriveMotor = new CANSparkMax(DriveTrainConstants.kRIGHT_MOTOR_PORT,
      MotorType.kBrushless);
  private final CANSparkMax rightSlaveMotor = new CANSparkMax(DriveTrainConstants.kRIGHT_SLAVE_MOTOR_PORT,
      MotorType.kBrushless);

  // Defines the encoders for each Drive neo
  
  private final CANEncoder leftDriveEncoder = leftDriveMotor.getEncoder();
  private final CANEncoder leftSlaveEncoder = leftSlaveMotor.getEncoder();
  private final CANEncoder rightDriveEncoder = rightDriveMotor.getEncoder();
  private final CANEncoder rightSlaveEncoder = rightSlaveMotor.getEncoder();

  private final CANPIDController leftController = new CANPIDController(leftDriveMotor);
  private final CANPIDController righController = new CANPIDController(rightDriveMotor);
  

    
//makes the encoders a group for pid

  // Defines the speed controller groups, slaves leftSlaveMotor to leftDriveMotor

  // differential drive because robotDrive is deprecated, no difference, simply
  // different term with a slightly different definition
  private final DifferentialDrive m_drive = new DifferentialDrive(leftDriveMotor, rightDriveMotor);

  // Not Definite, going to be used

  public DriveTrain() {
    leftSlaveMotor.follow(leftDriveMotor);
    rightSlaveMotor.follow(rightDriveMotor);

    leftDriveMotor.setInverted(true);
    leftSlaveMotor.setInverted(true);

  }
  //Defined here so that commands can use the same method
  public void arcadeDrive(double fwd, double rot){
    m_drive.arcadeDrive(fwd, rot);
  }

  public double getLeftEncoder(){
    return leftDriveEncoder.getPosition();
  }

  public double getRightEncoder(){
    return rightDriveEncoder.getPosition();
  }

  public double getPosition(){
    double initial = (leftDriveEncoder.getPosition()+rightDriveEncoder.getPosition())/2;
    return initial;
  }

  //TODO
  public double getInEncoderDistance(double distance){
    double final_value = -(distance-0.5)/6/Math.PI*10.71;
    return final_value;
  }

  public double getVelocityInRPM(double velocity_per_second){
    return velocity_per_second * 10.71;
  }

  public double getInInches(double encoder_units){
    return -encoder_units*6*Math.PI/10.71; 
  }

  public void setPID(double left, double right){
    leftController.setReference(left, ControlType.kVelocity);
    righController.setReference(right, ControlType.kVelocity);
  }

  @Override
  public void periodic() {
    setPID(0.0, 0.0);
    // This method will be called once per scheduler run
    //Jane Gradle
  }
  
}
