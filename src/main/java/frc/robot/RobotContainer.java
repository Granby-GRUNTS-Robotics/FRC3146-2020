/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ControlConstants;
import frc.robot.commands.intake.BallShift;
import frc.robot.commands.drive.drive;
import frc.robot.commands.drive.driveToLocation;
import frc.robot.subsystems.BallStorage;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterLift;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  DriverStation mDriverStation;
  
  // The robot's subsystems and commands are defined here...
  
  public final DriveTrain driveTrain = new DriveTrain();
  
  public final Shooter shooter = new Shooter();

  private final ShooterLift shooterLift = new ShooterLift();
  


  private final Intake intake = new Intake();

  private final BallStorage ballStorage = new BallStorage();

  //private final ColorSensor colorSensor = new ColorSensor();

  private final driveToLocation m_autoCommand = new driveToLocation(driveTrain, 0);

  public final Joystick bottomPortJoystick = new Joystick(ControlConstants.kDRIVE_CONTROLLER_PORT);
  
  public final Joystick topPortJoystick = new Joystick(ControlConstants.kTANK_DRIVE_CONTROLLER_PORT);

  private final Button intakeButton = new JoystickButton(bottomPortJoystick, 1);

  private final Button counterResetButton = new JoystickButton(bottomPortJoystick, 2);

  //*/

  private ShuffleboardTab shooterTab = Shuffleboard.getTab("Shooter");

  

  
  
  


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    shooterTab.add("Set Zero", new RunCommand(()->shooter.setSetpoint(0), shooter));
    shooterTab.add("Set One", new RunCommand(()->shooter.setSetpoint(5000), shooter));
    shooterTab.add("move bag", new RunCommand(()->ballStorage.addBallCounter(), ballStorage));
    shooterTab.add("move piston F", new RunCommand(()->shooterLift.setPosition(0), shooterLift));
    

    driveTrain.setDefaultCommand(new drive(driveTrain, bottomPortJoystick.getRawAxis(ControlConstants.kJOYSTICK_Y), bottomPortJoystick.getRawAxis(ControlConstants.kJOYSTICK_TWIST)));
    ballStorage.setDefaultCommand(new BallShift(ballStorage));
    // Configure the button bindings*/
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
      /*intakeButton.whenHeld(new RunCommand(()->intake.setIntakeMotorVelocity(0.75), intake))
      .whenInactive(new RunCommand(()->intake.setIntakeMotorVelocity(0.0), intake));

      intakeButton.whenHeld(new RunCommand(() -> ballStorage.runMotor(0.75), ballStorage))
      .whenInactive(new RunCommand(() -> ballStorage.runMotor(0.0), ballStorage));
      
      counterResetButton.whenHeld(new RunCommand(()->shooter.setSetpoint(-2000), shooter))
      .whenInactive(new RunCommand(()->shooter.setSetpoint(0.0), shooter));
      //*/
    }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}

