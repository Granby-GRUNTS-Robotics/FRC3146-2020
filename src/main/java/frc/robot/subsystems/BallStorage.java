/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class BallStorage extends SubsystemBase {

  TalonSRX bagController = new TalonSRX(IntakeConstants.kBAG_CONTROLLER_PORT);
  // Encoder throughBoreEncoder = new
  // Encoder(IntakeConstants.kTHROUGH_BORE_PORT[0],
  // IntakeConstants.kTHROUGH_BORE_PORT[1]);
  Rev2mDistanceSensor ballCheck = new Rev2mDistanceSensor(Port.kOnboard, Rev2mDistanceSensor.Unit.kMillimeters,
      Rev2mDistanceSensor.RangeProfile.kDefault);
  private int ballCount = 0;
  private int state_test = 0;
                                      
  double position = 0;
  /**
   * 
   * Creates a new BallStorage.
   */

   
  private double getDistanceSensor(){
    return ballCheck.getRange();
  }

  public boolean hasBall(){
    return getDistanceSensor() < IntakeConstants.kBALL_DISTANCE_SETPOINT && getDistanceSensor() != -1;
  }

  public BallStorage() {
    ballCheck.setAutomaticMode(true);
    bagController.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder );
    bagController.configFactoryDefault();
    bagController.config_kP(0, 0.15);
    resetEncoder();
    //Shuffleboard.getTab("John").add("Ball Count", getBallCount());
  }

  public double obtainEncoderPosition() {
    return bagController.getSelectedSensorPosition();
  }

  //sets the target position to rotations * 4096 (quadrature counts/rot)
  public void bagMotorSetPosition(double posit) {
    bagController.set(ControlMode.Position, posit*4096);
  }

  //sets encoder position to zero (hypothetically)
  public void resetEncoder(){
    bagController.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    //System.out.println(getBallCount() +" "+ obtainEncoderPosition());
    // This method will be called once per scheduler run
    //System.out.println("sensor: "+ getDistanceSensor()+", ball count: " + getBallCount());
 }

  //once it gets a ball, increase the counter. Once the ball is gone, start reading again
  public void countBall() {
    if (state_test == 0){
      if(hasBall()) {state_test++; ballCount++;}
      
    }else if (state_test == 1){
      if(!hasBall()){
        state_test = 0;
      }
    }
  }

  public int getBallCount(){
    return ballCount;
  }

  public void backTrack(){
    resetEncoder();
    bagMotorSetPosition(-1);
  }

  public int getState(){
    return state_test;
  }
  
  public void moveSpace(){
    position++;
    bagMotorSetPosition(IntakeConstants.kBALL_STORAGE_DISTANCE*position);
  }

  //useless
  /*public boolean isShifted(){
    return Math.abs(bagController.getErrorDerivative())<1000;
  }*/

  //good override function
  public void runMotor(double percent){
    bagController.set(ControlMode.PercentOutput, percent);
  }

  public void resetBallCounter(){
    ballCount = 0; position = 0; runMotor(0);
  }

  public void addBallCounter(){
    ballCount += 1;
  }
}

/**
 * Welcome to Proverb Corner
 * 
 * If you build a man a fire, he'll be warm for the night, 
 * but if you set a man on fire, he'll be warm for the rest of his life.
 * 
 * If you give a fish a man, he'll eat for like, 24 days.
 * If you teach a fish to man, he becomes a shark.
 * 
 * If you give a man a 3d printer, he'll eat for a day
 * If you teach a man to 3d print, he eats for life
 * 
 * If you give a man a match, he'll light one candle,
 * but if you teach a man to make matches, he'll ban books.
 * 
 * If you let a man go to sleep, he'll feel pretty refreshed in the morning,
 * but if you put a man to sleep, what morning? 
 */