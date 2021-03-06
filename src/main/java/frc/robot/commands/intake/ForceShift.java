/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStorage;

public class ForceShift extends CommandBase {
  /**
   * Creates a new ForceShift.
   */
  BallStorage ballStorage;
  int distance;
  int i = 0; 


  public ForceShift(BallStorage ballStorage, int distance) {
    this.distance = distance;
    this.ballStorage = ballStorage;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ballStorage);
  }

  public ForceShift(BallStorage ballStorage) {
    distance = 1;
    this.ballStorage = ballStorage;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ballStorage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ballStorage.resetBallCounter();
    ballStorage.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(i <= distance) {
      i++;
      ballStorage.moveSpace();
      System.out.println(i);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ballStorage.resetBallCounter();
    ballStorage.resetEncoder();
    i = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ballStorage.isShifted();
  }
}
