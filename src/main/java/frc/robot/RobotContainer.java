/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ControlConstants;
import frc.robot.commands.climb.pneumaticClimb;
import frc.robot.commands.drive.drive;
import frc.robot.commands.drive.driveToLocation;
import frc.robot.commands.drive.limeTurn;
import frc.robot.commands.drive.turnToLocation;
import frc.robot.commands.drive.xboxDrive;
import frc.robot.commands.intake.BallShift;
import frc.robot.commands.intake.MoveIntakeDOWN;
import frc.robot.commands.intake.MoveIntakeUP;
import frc.robot.commands.intake.pneumaticIntake;
import frc.robot.commands.shooter.Shoot;
import frc.robot.commands.shooter.StorageShoot;
import frc.robot.commands.shooter.pneumaticBallStop;
import frc.robot.commands.shooter.pneumaticShooter;
import frc.robot.subsystems.BallStorage;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LiftMechanism;
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

  private final LiftMechanism lift = new LiftMechanism();

  //private final ColorSensor colorSensor = new ColorSensor();

  private final driveToLocation m_autoCommand = new driveToLocation(driveTrain, 0);

  public final Joystick bottomPortJoystick = new Joystick(ControlConstants.kDRIVE_CONTROLLER_PORT);

  public final Joystick topPortJoystick = new Joystick(ControlConstants.kBUTTON_JOYSTICK_PORT);

  public final XboxController NicoController = new XboxController(ControlConstants.kDRIVE_CONTROLLER_PORT);

  private final Button intakeButton = new JoystickButton(bottomPortJoystick, 1);

  private final Button counterResetButton = new JoystickButton(bottomPortJoystick, 2);

  //*/


  private ShuffleboardTab shooterTab = Shuffleboard.getTab("Shooter");

  

  
  
  


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    shooterTab.add("Set Zero", new StorageShoot(shooter, 0));
    shooterTab.add("Set One", new StorageShoot(shooter, 5000));

    shooterTab.add("Shoot", new Shoot(ballStorage, shooter, 4000));
    shooterTab.add("move intake up", new pneumaticIntake(intake, "up"));
    shooterTab.add("move intake down", new pneumaticIntake(intake, "down"));
    shooterTab.add("move intake down soft", new pneumaticIntake(intake, "soft"));
    shooterTab.add("move intake off", new pneumaticIntake(intake, "off"));

    shooterTab.add("move lift up", new pneumaticClimb(lift, "up"));
    shooterTab.add("move lift upp", new pneumaticClimb(lift, "upp"));
    shooterTab.add("move lift down", new pneumaticClimb(lift, "down"));
    shooterTab.add("move lift offNotExtra", new pneumaticClimb(lift,"offNotExtra"));
    shooterTab.add("move lift off", new pneumaticClimb(lift, "off"));


    shooterTab.add("run winch Joy", new RunCommand(()->lift.winchControl(topPortJoystick.getRawAxis(ControlConstants.kJOYSTICK_Y)), lift));

    shooterTab.add("90 degree turn", new turnToLocation(driveTrain, 90));

    shooterTab.add("move ballstop up", new pneumaticBallStop(shooter, "down"));
    shooterTab.add("move ballstop down", new pneumaticBallStop(shooter, "up"));

    shooterTab.add("backtrack", new RunCommand(()-> ballStorage.backTrack() , ballStorage));

    shooterTab.add("reset ball", new InstantCommand(()->ballStorage.resetBallCounter(), ballStorage));
    shooterTab.add("move bag", new RunCommand(()->ballStorage.runMotor(.35), ballStorage));

    shooterTab.add("shooter zero", new pneumaticShooter(shooterLift, 0));
    shooterTab.add("shooter one", new pneumaticShooter(shooterLift, 1));
    shooterTab.add("shooter two", new pneumaticShooter(shooterLift, 2));
    shooterTab.add("shooter three", new pneumaticShooter(shooterLift, 3));

    shooterTab.add("KeyLimePi On", new InstantCommand(() -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0)));
    shooterTab.add("KeyLimePi Off", new InstantCommand(() -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1)));

    shooterTab.add("limeturn", new limeTurn(driveTrain));
    //
    
    /*driveTrain.setDefaultCommand(new drive(driveTrain, 
                                           bottomPortJoystick));
    
    //*/
    //ballStorage.setDefaultCommand(new BallShift(ballStorage));

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
      intakeButton.whenHeld(new Shoot(ballStorage, shooter, 2300))
      .whenReleased(new StorageShoot(shooter, 0))
      .whenReleased(new pneumaticBallStop(shooter, "up"));
      intakeButton.whenInactive(new InstantCommand(()->ballStorage.resetBallCounter()));
      
      counterResetButton.whenActive(new MoveIntakeDOWN(intake, shooterLift));
      counterResetButton.whenReleased(new MoveIntakeUP(intake));
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

