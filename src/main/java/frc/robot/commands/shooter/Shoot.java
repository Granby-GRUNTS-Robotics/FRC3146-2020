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
import frc.robot.subsystems.BallStorage;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Shoot extends SequentialCommandGroup {
  /**
   * Creates a new Shoot.
   */
  public Shoot(BallStorage ballStorage, Shooter shooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(    
      new InstantCommand(()->ballStorage.resetBallCounter(), ballStorage),
      new InstantCommand(()->ballStorage.backTrack(), ballStorage),
      new pneumaticBallStop(shooter, "down"),
      new StorageShoot(shooter, 4000),
      new InstantCommand(()->ballStorage.runMotor(0.5), ballStorage),
      new InstantCommand(()->ballStorage.resetEncoder(), ballStorage),
      new InstantCommand(()->ballStorage.resetBallCounter(), ballStorage)


    
      
      );
  }
}
