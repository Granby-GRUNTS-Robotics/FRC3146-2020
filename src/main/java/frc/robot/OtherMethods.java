/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants.ControlConstants;

/**
 * Add your docs here.
 */
public class OtherMethods {
    public static double getLimelighttx(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    }

    public static double getValueWithDeadband(double rawAxis){
        double inbetween;
        inbetween = ((Math.abs(rawAxis) < ControlConstants.kDEADZONE) ? (0) : (rawAxis)) * (1 - ControlConstants.kDEADZONE);
        return inbetween;
    } 
}
