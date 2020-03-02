/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.LiftConstants;
import frc.robot.subsystems.LiftMechanism;

public class joystickWinch extends CommandBase {
  /**
   * Creates a new joystickWinch.
   */
  LiftMechanism lift;
  Joystick joy;
  String direction;
  public joystickWinch(LiftMechanism lift, Joystick joy, String direction) {
    this.lift = lift;
    this.joy = joy;
    this.direction = direction;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lift);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   switch (direction) {
     case "up":
    lift.setWinch(
      (lift.getLiftEncoder()<LiftConstants.kUP_MAX_DISTANCE)
                ? joy.getRawAxis(ControlConstants.kJOYSTICK_Y) 
                : 0
    );
       break;
    case "down":
    lift.setWinch(joy.getRawAxis(ControlConstants.kJOYSTICK_Y));
       break;
     default:
       break;
   }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return joy.getRawButtonPressed(7);
  }
}
