/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStorage;

public class backSpace extends CommandBase {
  /**
   * Creates a new ForceShift.
   */
  BallStorage ballStorage;
  int distance;


  public backSpace(BallStorage ballStorage) {
    this.ballStorage = ballStorage;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ballStorage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ballStorage.resetBallCounter();
    ballStorage.resetEncoder();
    ballStorage.backTrack();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ballStorage.resetBallCounter();
    ballStorage.resetEncoder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ballStorage.isShifted();
  }
}
