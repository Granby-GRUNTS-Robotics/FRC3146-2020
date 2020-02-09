/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;

import javax.swing.Spring;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.*;
import com.revrobotics.Rev2mDistanceSensor.Port;

public class BallStorage extends SubsystemBase {

  TalonSRX bagController = new TalonSRX(IntakeConstants.kBAG_CONTROLLER_PORT);
  // Encoder throughBoreEncoder = new
  // Encoder(IntakeConstants.kTHROUGH_BORE_PORT[0],
  // IntakeConstants.kTHROUGH_BORE_PORT[1]);
  Rev2mDistanceSensor ballCheck = new Rev2mDistanceSensor(Port.kOnboard, Rev2mDistanceSensor.Unit.kMillimeters,
      Rev2mDistanceSensor.RangeProfile.kDefault);
  private String isCaptured = "AAA";
  private String dwightMood = "ee";
  private int ballCount = 0;
  private int state_test = 0;
  final Timer timer = new Timer();
                                      
  
  /**
   * 
   * Creates a new BallStorage.
   */

   
  private double getDistanceSensor(){
    return ballCheck.getRange()-30;
  }

  public boolean hasBall(){
    return getDistanceSensor() < IntakeConstants.kBALL_DISTANCE_SETPOINT;
  }

  public BallStorage() {
    ballCheck.setAutomaticMode(true);
  }

  public double obtainEncoderPosition() {
    return 0.0; //throughBoreEncoder.get();
  }

  public void bagMotorSetPosition(double position) {
    bagController.set(ControlMode.PercentOutput, position);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(getBallCount());
    System.out.println(timer.get());
    if(ballCheck.getRange()<100) {
      
      isCaptured="true";
      dwightMood="true";
      
    }
    else {
      isCaptured="false";
      dwightMood="false";
    }
    SmartDashboard.putString("Is Captured", isCaptured);
    SmartDashboard.putString("Dwight mood: ", dwightMood);

    
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
  public void reset(){
    timer.reset();
  }
  public void moveSpace(){
    timer.start();
    if(timerT()) {
      bagMotorSetPosition(0.5);
    }else {
      
    }
  }
  public boolean timerT(){
    return timer.get() < 0.5;
  }

  public void resetBallCounter(){
    ballCount = 0;
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
 */