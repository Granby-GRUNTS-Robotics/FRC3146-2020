/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.intake.ForceShift;
import frc.robot.commands.intake.backSpace;
import frc.robot.subsystems.BallStorage;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterLift;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Shoot extends SequentialCommandGroup {
  /**
   * Creates a new Shoot.
   */
  public Shoot(BallStorage ballStorage, Shooter shooter, ShooterLift lift, double pos) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(    
      new pneumaticShooter(lift, 1),
      new InstantCommand(()->ballStorage.resetBallCounter(), ballStorage),
      new backSpace(ballStorage),
      new pneumaticBallStop(shooter, "down"),
      new StorageShoot(shooter, pos),
      new RunCommand(() -> ballStorage.runMotor(IntakeConstants.kSHOOTER_BAG_SPEED), ballStorage)
      );
  }
}
