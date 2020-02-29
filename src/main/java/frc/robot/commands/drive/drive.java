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


/**
 * An example command that uses an example subsystem.
 */
public class drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  //to give this subsysem access to its passed methods
  private final DriveTrain m_DriveTrain;
  private Joystick m_joy;
  private double m_turn;
  private double m_speed;
  /**
   * Creates a new ExampleCommand.
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
    m_DriveTrain.setReference(m_speed*30-m_turn*20, m_speed*30+m_turn*20);
    System.out.println(m_speed + ", " + m_turn);
    m_speed = m_joy.getRawAxis(ControlConstants.kJOYSTICK_Y);
    m_turn = m_joy.getRawAxis(ControlConstants.kJOYSTICK_TWIST);//
    

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
