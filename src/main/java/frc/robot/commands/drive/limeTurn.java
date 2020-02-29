/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class limeTurn extends CommandBase {
  /**
   * Creates a new turnToLocation.
   */
  private DriveTrain driveTrain;
  private double degrees;
  private double distance;

  public limeTurn(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  public double getLimelightInDegrees(double limelight){
    return limelight;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    degrees = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    distance = driveTrain.getTurnInEncoderDistance(getLimelightInDegrees(degrees));
    driveTrain.setReference(-distance, distance);
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
