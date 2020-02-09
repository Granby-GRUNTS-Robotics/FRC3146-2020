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
  private DriveTrain m_DriveTrain;
  private double m_distance;
  private double initial_position;
  
  /**
   * Creates a new driveToLocation.
   */


  public driveToLocation(DriveTrain driveTrain, double distance) {
    m_DriveTrain = driveTrain;
    initial_position = m_DriveTrain.getPosition();
    
    m_distance = initial_position + m_DriveTrain.getInEncoderDistance(distance);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }


  //check to make sure we are at target
  private boolean isAtTarget(){
    if(Math.abs(m_DriveTrain.getPosition() - m_distance) < DriveTrainConstants.kSPECIFICITY){
      return true;
    } else return false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_distance < m_DriveTrain.getPosition()){
      m_DriveTrain.arcadeDrive(0, -0.35);
    }else if (m_distance > m_DriveTrain.getPosition()
    ){
      m_DriveTrain.arcadeDrive(0, 0.35);
    }
    String john = m_DriveTrain.getInInches(m_DriveTrain.getPosition()) + " , " + m_distance + " , " + initial_position;
    System.out.println(john);
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
