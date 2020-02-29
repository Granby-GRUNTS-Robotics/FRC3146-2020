/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import frc.robot.Constants.ControlConstants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.ControlConstants.kTHROTTLE_MULTIPLIER;
import static frc.robot.Constants.ControlConstants.kTWIST_MULTIPLIER;


/**
 * An example command that uses an example subsystem.
 */
public class drive extends CommandBase {

  //Joystick is passed instead of doubles for more variability within the command itsself
  private final DriveTrain m_DriveTrain;
  private Joystick m_joy;
  private double m_turn;
  private double m_speed;
  /**
   * Creates a new drive command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public drive(DriveTrain subsystem,Joystick joy) {
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
    m_speed = m_joy.getRawAxis(ControlConstants.kJOYSTICK_Y);
    m_turn = m_joy.getRawAxis(ControlConstants.kJOYSTICK_TWIST);//

    //deadzone code w/ simplified if-else statement
    m_speed = ((Math.abs(m_speed) < ControlConstants.kDEADZONE) ? (0) : (m_speed));
    m_turn = ((Math.abs(m_turn)<ControlConstants.kDEADZONE) ? (0) : (m_turn));
    
    //sets the target speed based on axes
    m_DriveTrain.setReference(m_speed*kTHROTTLE_MULTIPLIER-m_turn*kTWIST_MULTIPLIER, 
                              m_speed*kTHROTTLE_MULTIPLIER+m_turn*kTWIST_MULTIPLIER);
    
    //for testing
    //System.out.println(m_speed + ", " + m_turn);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end
  //default command so never finished
  @Override
  public boolean isFinished() {
    return false;
  }
}
