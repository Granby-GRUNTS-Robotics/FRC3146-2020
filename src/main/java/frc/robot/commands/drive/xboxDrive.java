/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OtherMethods;
import frc.robot.Constants.ControlConstants;
import frc.robot.subsystems.DriveTrain;
import static frc.robot.Constants.ControlConstants.kTHROTTLE_MULTIPLIER;
import static frc.robot.Constants.ControlConstants.kTWIST_MULTIPLIER;

/**
 * An example command that uses an example subsystem.
 */
public class xboxDrive extends CommandBase {

  //to give this subsysem access to its passed methods
  private final DriveTrain m_DriveTrain;
  private XboxController m_joy;
  private double m_turn;
  private double m_speed;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public xboxDrive(DriveTrain subsystem,XboxController joy) {
    m_DriveTrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    m_joy = joy;
    addRequirements(subsystem);

  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //speed just as the sum of both triggers
    m_speed = -m_joy.getRawAxis(ControlConstants.kXBOX_RT)+m_joy.getRawAxis(ControlConstants.kXBOX_LT);
    m_turn = m_joy.getRawAxis(ControlConstants.kXBOX_X);//
    //copy and pasted from "drive.java"
    m_speed = OtherMethods.getValueWithDeadband(m_speed);
    m_turn = OtherMethods.getValueWithDeadband(m_turn);
    //sets reference after deadzone has been applied
    m_DriveTrain.setReference(m_speed*kTHROTTLE_MULTIPLIER-m_turn*kTWIST_MULTIPLIER, 
                              m_speed*kTHROTTLE_MULTIPLIER+m_turn*kTWIST_MULTIPLIER);
    
    //for troubleshooting
    //System.out.println(m_speed + ", " + m_turn);
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
