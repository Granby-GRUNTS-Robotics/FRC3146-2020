/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.DriveTrain;

public class turnToLocation extends CommandBase {
  /**
   * Creates a new turnToLocation.
   */
  private DriveTrain driveTrain;
  private double degrees;
  private double distance;
  private double initial;
  private boolean isFinished;

  public turnToLocation(DriveTrain driveTrain, double degrees) {
    this.driveTrain = driveTrain;
    this.degrees = degrees;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  //changes to offset from pigeonIMU for use in feedback loop, rather than blind turning
  @Override
  public void initialize() {
    initial = driveTrain.getFusedHeading();
    degrees += initial;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(driveTrain.getFusedHeading()+ ", " + degrees);
    //distance to target changes each time, we will see if this works or not
    distance = driveTrain.getTurnInEncoderDistance(degrees - driveTrain.getFusedHeading());
    //sets the actual reference point
    driveTrain.setReference(-distance, distance);
    //for troubleshooting
    //System.out.println(distance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    isFinished = Math.abs((initial+degrees) - driveTrain.getFusedHeading()) < DriveTrainConstants.kDEGREE_SPECIFICITY;
    return isFinished;
  }
}
