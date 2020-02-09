/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStorage;

public class BallShift extends CommandBase {
  BallStorage m_BallStorage;
  Timer timer= new Timer();
  /**
   * Creates a new BallShift.
   */
  public BallShift(BallStorage ballStorage) {
    m_BallStorage = ballStorage;
    addRequirements(ballStorage);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_BallStorage.hasBall() && m_BallStorage.getBallCount() < 5){
      m_BallStorage.reset();
      m_BallStorage.moveSpace();
    }else  {
      if(!m_BallStorage.timerT()) {m_BallStorage.bagMotorSetPosition(0.0);}
    }

    m_BallStorage.countBall();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}