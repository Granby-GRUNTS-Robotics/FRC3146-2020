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
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.climb.fullClimb;
import frc.robot.commands.climb.pneumaticClimb;
import frc.robot.commands.climb.ratchet;
import frc.robot.commands.drive.drive;
import frc.robot.commands.drive.driveToLocation;
import frc.robot.commands.drive.limeTurn;
import frc.robot.commands.intake.BallShift;
import frc.robot.commands.intake.ForceShift;
import frc.robot.commands.intake.MoveIntakeDOWN;
import frc.robot.commands.intake.MoveIntakeUP;
import frc.robot.commands.intake.intakeSet;
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
import frc.robot.subsystems.Vision;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  DriverStation mDriverStation;
  
  // The robot's subsystems and commands are defined here...

  public final Vision vision = new Vision();
  
  public final static DriveTrain driveTrain = new DriveTrain();
  
  public final static Shooter shooter = new Shooter();

  public final static ShooterLift shooterLift = new ShooterLift();
  
  public final static Intake intake = new Intake();

  public final static BallStorage ballStorage = new BallStorage();

  public final static LiftMechanism lift = new LiftMechanism();

  //private final ColorSensor colorSensor = new ColorSensor();

  private final driveToLocation m_autoCommand = new driveToLocation(driveTrain, 0);

  private final Joystick bottomPortJoystick = new Joystick(ControlConstants.kDRIVE_CONTROLLER_PORT);

  private final Joystick buttonJoystick = new Joystick(ControlConstants.kBUTTON_JOYSTICK_PORT);

  //private final XboxController NicoController = new XboxController(ControlConstants.kDRIVE_CONTROLLER_PORT);
  //Wher I define a whole crapload of buttons

  private final Button intakeButton = new JoystickButton(buttonJoystick, 2);
  private final Button shootButton = new JoystickButton(buttonJoystick, 1);
  private final Button magazineZeroButton = new JoystickButton(buttonJoystick, 12);
  private final Button magazineOneButton = new JoystickButton(buttonJoystick, 10);
  private final Button magazineThreeButton = new JoystickButton(buttonJoystick, 8);
  private final Button intakeUp = new JoystickButton(buttonJoystick, 5);
  private final Button intakeDown = new JoystickButton(buttonJoystick, 6);
  private final Button bagForceButton = new JoystickButton(buttonJoystick, 3);
  private final Button intakeEjectButton = new JoystickButton(buttonJoystick, 4);
  private final Button limeLightButton = new JoystickButton(bottomPortJoystick, 1);

  private final Button climb = new JoystickButton(buttonJoystick, 9);
  private final Button climbB = new JoystickButton(buttonJoystick, 11);
  //*/


  private ShuffleboardTab shooterTab = Shuffleboard.getTab("Shooter");

  

  
  
  


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //lots off testing commands
    
    shooterTab.add("Set Zero", new StorageShoot(shooter, 0));
    shooterTab.add("Set One", new StorageShoot(shooter, 5000));

    shooterTab.add("move intake up", new pneumaticIntake(intake, "up"));
    shooterTab.add("move intake down", new pneumaticIntake(intake, "down"));
    shooterTab.add("move intake down soft", new pneumaticIntake(intake, "soft"));
    shooterTab.add("move intake off", new pneumaticIntake(intake, "off"));

    shooterTab.add("move lift up", new pneumaticClimb(lift, "up"));
    shooterTab.add("move lift upp", new pneumaticClimb(lift, "upp"));
    shooterTab.add("move lift down", new pneumaticClimb(lift, "down"));
    shooterTab.add("move lift offNotExtra", new pneumaticClimb(lift,"offNotExtra"));
    shooterTab.add("move lift off", new pneumaticClimb(lift, "off"));

    shooterTab.add("run winch Joy", new RunCommand(()->lift.setWinch(buttonJoystick.getRawAxis(ControlConstants.kJOYSTICK_Y)), lift));

    shooterTab.add("move ballstop up", new pneumaticBallStop(shooter, "down"));
    shooterTab.add("move ballstop down", new pneumaticBallStop(shooter, "up"));

    shooterTab.add("shooter zero", new pneumaticShooter(shooterLift, 0));
    shooterTab.add("shooter one", new pneumaticShooter(shooterLift, 1));
    shooterTab.add("shooter two", new pneumaticShooter(shooterLift, 2));
    shooterTab.add("shooter three", new pneumaticShooter(shooterLift, 3));

    shooterTab.add("KeyLimePi On", new InstantCommand(() -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0)));
    shooterTab.add("KeyLimePi Off", new InstantCommand(() -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1)));
    //*/    

    shooterTab.add("ratchet on", new ratchet(lift, "yes"));
    shooterTab.add("ratchet off", new ratchet(lift, "no"));

    driveTrain.setDefaultCommand(new drive(driveTrain, 
                                           bottomPortJoystick));
    
    //*/
    ballStorage.setDefaultCommand(new BallShift(ballStorage));

    //lift.setDefaultCommand(new ratchet(lift, "yes"));

    //math was to get up to equal up & down to equal down
    vision.setDefaultCommand(new RunCommand(
                              ()->vision.setPosition(
                              (-bottomPortJoystick.getRawAxis(ControlConstants.kJOYSTICK_SLIDER)+1)/2*.68
                                ),
                                vision
                              )
                            );

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
      shootButton.whenHeld(new Shoot(ballStorage, shooter, shooterLift, ShooterConstants.kSHOOTER_NORMAL_SPEED
      ))
      .whenReleased(new StorageShoot(shooter, 0))
      .whenReleased(new pneumaticBallStop(shooter, "up"));
      shootButton.whenInactive(new InstantCommand(()->ballStorage.resetBallCounter()));
      
      intakeButton.whenActive(new MoveIntakeDOWN(intake, shooterLift));
      intakeButton.whenReleased(new MoveIntakeUP(intake));

      limeLightButton.whenHeld(new limeTurn(driveTrain, bottomPortJoystick)).whenPressed(new InstantCommand(() -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0)))
      .whenInactive(new InstantCommand(() -> NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1)));

      bagForceButton.whenPressed(new ForceShift(ballStorage));
      intakeEjectButton.whenPressed(new intakeSet(intake, -0.5)).whenReleased(new intakeSet(intake, 0));

      magazineZeroButton.whenPressed(new pneumaticShooter(shooterLift, 0));
      magazineOneButton.whenPressed(new pneumaticShooter(shooterLift, 1));
      magazineThreeButton.whenPressed(new pneumaticShooter(shooterLift, 3));

      intakeUp.whenPressed(new pneumaticIntake(intake, "up"));
      intakeDown.whenPressed(new pneumaticIntake(intake, "soft"));

      ((Button) climb.and(climbB)).whenHeld(new fullClimb(intake, shooterLift, lift, buttonJoystick));
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

