/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ControlConstants;
import frc.robot.commands.BallShift;
import frc.robot.commands.drive;
import frc.robot.commands.driveToLocation;
import frc.robot.subsystems.BallStorage;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  DriverStation mDriverStation;
  
  // The robot's subsystems and commands are defined here...
  public final Joystick bottomPortJoystick = new Joystick(ControlConstants.kXBOX_CONTROLLER_PORT );
  public final Joystick topPortJoystick = new Joystick(1 );
  
  public final DriveTrain driveTrain = new DriveTrain();
  
  public final Shooter j = new Shooter();

  //private final driveToLocation m_autoCommand = new driveToLocation(driveTrain, -24);

  Button intakeButton = new JoystickButton(bottomPortJoystick, 1);

  Button counterResetButton = new JoystickButton(bottomPortJoystick, 2);

  private final ColorSensor m_ColorSensor = new ColorSensor();

  private Intake m_Intake = new Intake();

  //private final BallStorage m_BallStorage = new BallStorage();
  
  


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
        //complicated way of setting the default command using a lambda function.
        //I will explain it if we have time, for now just remember the syntax
        //basically this sets the drive train's defaul command to a normal user drive
        driveTrain.setDefaultCommand(new RunCommand(() -> driveTrain.setPID(bottomPortJoystick.getRawAxis(Constants.ControlConstants.kJOYSTICK_Y), topPortJoystick.getRawAxis(Constants.ControlConstants.kJOYSTICK_Y) ), driveTrain));
          /*new RunCommand(() -> driveTrain.arcadeDrive(
                              -bottomPortJoystick.getRawAxis(Constants.ControlConstants.kJOYSTICK_Y), 
                              bottomPortJoystick.getRawAxis(Constants.ControlConstants.kJOYSTICK_TWIST)), 
                              driveTrain));*/
                              
                              


        

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
      /*intakeButton.whenHeld(new BallShift(m_BallStorage)
      ).whenInactive(new RunCommand(
        () -> m_BallStorage.bagMotorSetPosition(0.0)
                              )
      );

      counterResetButton.whenHeld(new RunCommand(
        () -> m_BallStorage.resetBallCounter()
      ));*/
      

    }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new driveToLocation(driveTrain, 10);
  }
}

