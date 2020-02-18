/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.DriveTrain;

public class driveToLocation extends CommandBase {
  private DriveTrain m_driveTrain;
  private double m_distance;
  private double initial_position;
  
  /**
   * Creates a new driveToLocation.
   */


  public driveToLocation(DriveTrain driveTrain, double distance) {
    m_driveTrain = driveTrain;    
    m_distance = m_driveTrain.getInEncoderDistance(distance);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }


  //check to make sure we are at target
  private boolean isAtTarget(){
    if(Math.abs(m_driveTrain.getPosition() - m_distance) < DriveTrainConstants.kSPECIFICITY){
      return true;
    } else return false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.setReference(m_distance, m_distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (isAtTarget()){
    return true;
    }else return false;
  }
}
