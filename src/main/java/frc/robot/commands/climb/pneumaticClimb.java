/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftMechanism;

public class pneumaticClimb extends CommandBase {
  /**
   * Creates a new pneumaticClimb.
   */
  LiftMechanism lift;
String pos;

  public pneumaticClimb(LiftMechanism lift, String pos) {
    this.lift = lift;
    this.pos = pos;
    addRequirements(lift);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    switch (pos) {
      case "up":
        lift.up();
        break;
      case "upp":
        lift.upp();
        break;
      case "down":
        lift.down();
        break;
      case "offNotExtra":
        lift.offNotExtra();
        break;
      case "off":
        lift.off();
      default:
        break;
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

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
