/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class pneumaticIntake extends CommandBase {
  /**
   * Creates a new pneumaticIntake.
   */
  Intake m_intake; 
  String m_pos;

  public pneumaticIntake(Intake intake, String pos) {
    m_intake = intake;
    m_pos = pos;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_pos == "down"){
      m_intake.down();
    }else if(m_pos == "up"){
      m_intake.up();
    }else if(m_pos == "soft"){
      m_intake.soft();
    }else if(m_pos == "off"){
      m_intake.off();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    // TODO Auto-generated method stub
    return true;
  }

  // Returns true when the command should end.
}
