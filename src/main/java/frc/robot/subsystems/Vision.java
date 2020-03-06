//

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Servo;

public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   */
  private static Servo cameraServo = new Servo(8);

  public Vision() {
  }

  public void setServo(double pos){
    cameraServo.set(pos);
  }
    // Use addRequirements() here to declare subsystem dependencies.    
  @Override
  public void periodic() {
    // TODO Auto-generated method stub
    //SmartDashboard.putNumber("Camera Position: ", cameraServo.get());
  }
}