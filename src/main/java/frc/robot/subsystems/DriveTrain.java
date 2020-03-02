/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.SensorConstants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  //our gyroscope
  public final PigeonIMU pigeonIMU= new PigeonIMU(SensorConstants.kPIDGEON_PORT);

  //for when we are turning/using PID setpoints
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
  
  //new encoders for each Neo's, they need to be defined in the code or the motors won't run
  private final CANEncoder leftDriveEncoder = leftDriveMotor.getEncoder();
  private final CANEncoder leftSlaveEncoder = leftSlaveMotor.getEncoder();
  private final CANEncoder rightDriveEncoder = rightDriveMotor.getEncoder();
  private final CANEncoder rightSlaveEncoder = rightSlaveMotor.getEncoder();

  //Defining networkTables


  //for trajectory generation and PID drive
  private final CANPIDController leftController = new CANPIDController(leftDriveMotor);
  private final CANPIDController rightController = new CANPIDController(rightDriveMotor);


  public DriveTrain() {
    //make slaves follow their masters
    leftSlaveMotor.follow(leftDriveMotor);
    rightSlaveMotor.follow(rightDriveMotor);

    leftDriveMotor.setInverted(true);
    leftSlaveMotor.setInverted(true);
    rightDriveMotor.setInverted(false);
    rightSlaveMotor.setInverted(false);

    leftController.setP(0.027);
    leftDriveEncoder.setPosition(0.0);
    leftSlaveEncoder.setPosition(0.0);

    rightController.setP(0.027);
    rightDriveEncoder.setPosition(0.0);
    rightSlaveEncoder.setPosition(0.0);

    rightDriveMotor.setSmartCurrentLimit(40);
    leftDriveMotor.setSmartCurrentLimit(40);
    rightSlaveMotor.setSmartCurrentLimit(40);
    leftSlaveMotor.setSmartCurrentLimit(40);
  }
/* A BUNCH OF COMMANDS ARE DEFINED HERE FOR USE IN OTHER PARTS OF THE CODE*/

  //better methods than calling them directly

  public double getLeftEncoderPosition(){
    return leftDriveEncoder.getPosition();
  }

  public double getRightEncoderPosition(){
    return rightDriveEncoder.getPosition();
  }

  public double getPosition(){
    double initial = (leftDriveEncoder.getPosition()+rightDriveEncoder.getPosition())/2;
    return initial;
  }

  public double getInEncoderDistance(double distance){
    double final_value = -(distance)/6/Math.PI*10.71;
    return final_value;
  }

  public double getInInches(double encoder_units){
    return -encoder_units*6*Math.PI/10.71; 
  }

  public double getFusedHeading(){
    return pigeonIMU.getFusedHeading();
  }

  public double getTurnInEncoderDistance(double degrees){
    //simple trig w/ wheel to wheel as the "diameter"
    return getInEncoderDistance(degrees/360*21*Math.PI);
  }

  //used for pretty much everything
  public void setReference(double left, double right){
    leftController.setReference(getLeftEncoderPosition()+ left , ControlType.kPosition);
    rightController.setReference(getRightEncoderPosition()+ right, ControlType.kPosition);
  }

  //CONSTANTLY CALLED. BE VERY CAREFUL WITH WHAT YOU PUT IN HERE
  @Override
  public void periodic() {
    //SmartDashboard.putNumber("jj", pigeonIMU.getFusedHeading());
  }
  
}
