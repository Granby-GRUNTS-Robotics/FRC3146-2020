/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import static frc.robot.Constants.ControlConstants.kTHROTTLE_MULTIPLIER;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OtherMethods;
import frc.robot.Constants.ControlConstants;
import frc.robot.subsystems.DriveTrain;


/**
 * An example command that uses an example subsystem.
 */
public class tankDrive extends CommandBase {

  //Joystick is passed instead of doubles for more variability within the command itsself
  private final DriveTrain m_DriveTrain;
  private Joystick ljoy;
  private Joystick rjoy;

  private double left;
  private double right;
  /**
   * Creates a new drive command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public tankDrive(DriveTrain subsystem,Joystick ljoy, Joystick rjoy) {
    m_DriveTrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    this.ljoy = ljoy;
    this.rjoy = rjoy;
    addRequirements(subsystem);

  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    left = ljoy.getRawAxis(ControlConstants.kJOYSTICK_Y);
    right = rjoy.getRawAxis(ControlConstants.kJOYSTICK_TWIST);//

    //deadzone code w/ simplified if-else statement
    left = OtherMethods.getValueWithDeadband(left);
    right = OtherMethods.getValueWithDeadband(right);
    
    //sets the target speed based on axes
    m_DriveTrain.setReference(left*kTHROTTLE_MULTIPLIER, 
                              right*kTHROTTLE_MULTIPLIER);
    
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
