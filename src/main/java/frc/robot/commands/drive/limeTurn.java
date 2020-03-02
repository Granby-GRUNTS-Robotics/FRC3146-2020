/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ControlConstants;
import frc.robot.subsystems.DriveTrain;

public class limeTurn extends CommandBase {
  /**
   * Creates a new turnToLocation.
   */
  private DriveTrain driveTrain;
  private double degrees;
  private double distance;
  private double m_speed;
  private Joystick joy;

  public limeTurn(DriveTrain driveTrain, Joystick joy) {
    this.joy = joy;
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  public double getLimelightInDegrees(double limelight){
    return limelight;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_speed = joy.getRawAxis(ControlConstants.kJOYSTICK_Y);

    //deadzone code w/ simplified if-else statement
    m_speed = ((Math.abs(m_speed) < ControlConstants.kDEADZONE) ? (0) : (m_speed*30));
  

    //gets limelight turn ("tx") value from Network Tables
    degrees = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    //changes the degree turn amount into correct distance for the motors to run
    distance = driveTrain.getTurnInEncoderDistance(degrees);
    distance = (Math.abs(distance) < 0.5) ? 0 : distance*3;
    driveTrain.setReference(+distance+m_speed, -distance+m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  //used as a default command for now, we will see for auto what we will do
  @Override
  public boolean isFinished() {
    return false;
  }
}
