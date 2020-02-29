/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    /*
     * AS OF JANUARY 11, MOST CONSTANTS ARE NOT DEFINED! THIS IS OKAY! 
     * MOST OF THEM WILL BE DEFINED ONCE THE HARDWARE/WIRING IS COMPLETE OR IN PROGRESS
     */

    //Drivetrain Constants defined here for ease of access
    public static final class DriveTrainConstants{
        public static final int kRIGHT_MOTOR_PORT = 1;
        public static final int kLEFT_MOTOR_PORT = 2;
        public static final int kRIGHT_SLAVE_MOTOR_PORT = 3;
        public static final int kLEFT_SLAVE_MOTOR_PORT = 4;
        public static final double kSPECIFICITY = 0.1;
        public static final double kDEGREE_SPECIFICITY = 2;

        //Used for velocity PID control of the drive base. Must reconfigure once full weight is on the drive base
        public final static double kP = 0.029;
        public final static double kI = 0.0;
        public final static double kD = 0.0;

    }

    /**
     * ShooterConstants
     */
    public static final class ShooterConstants {
        public static final int kLEFT_SHOOTER_MOTOR_PORT = 11;
        public static final int kRIGHT_SHOOTER_MOTOR_PORT = 10;
        public static final double kSHOOTER_NORMAL_SPEED = 0;
        public static final boolean kSON = false;
        public static final boolean kSOFF = true;

        //shooter velocity pid constants
        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
    }


    /**
     * IntakeConstants
     */
    public static final class IntakeConstants {
        public static final int kINTAKE_MOTOR_PORT = 6;
        public static final double kINTAKE_NORMAL_SPEED = 0;
        public static final int kBAG_CONTROLLER_PORT = 7;
        public static final double kBALL_STORAGE_DISTANCE = 3;
        public static final int[] kTHROUGH_BORE_PORT = {0,0};
        public static final int kBALL_DISTANCE_SETPOINT = 160;
    }

    /**
     * Pneumatic Constants
     * they have long names but that's fine for now
     */
    public static final class PneumaticConstants {
        public static final int kPCM_Port = 14;

        public static final int kFRONT_INTAKE_PORT = 0;
        public static final int kBACK_INTAKE_PORT = 1;

        public static final int kFIRST_CLIMB_PORT = 5;
        public static final int kSECOND_CLIMB_PORT = 6;
        public static final int kThird_CLIMB_PORT = 4;

        public static final int kSHOOTER_STOP_PORT = 7;

        public static final int kTOP_SHOOTER_LIFT_PORT = 3;
        public static final int kBOTTOM_SHOOTER_LIFT_PORT = 2;

        public static final boolean kON = true;
        public static final boolean kOFF = false;

    }

    /**
     * Keep all sensors together, even if in different classes
     */
    public static final class SensorConstants {
        public static final int kBALL_COUNTER_PORT = 0;
        public static final int kCOLOR_SENSOR_PORT = 0;
        public static final int kPIDGEON_PORT = 5;

    }



    /**
     * Not sure if we will make a controller subclass, just for future convenience putting constants here
     */
    public static final class ControlConstants {
        public static final int kBUTTON_JOYSTICK_PORT = 1;
        public static final int kDRIVE_CONTROLLER_PORT = 0;
        public static final int kTANK_DRIVE_CONTROLLER_PORT = 1;
        public static final int kJOYSTICK_TWIST = 2;
        public static final int kJOYSTICK_Y = 1;
        public static final int kJOYSTICK_SLIDER = 3;
        public static final int kXBOX_X = 0;
        public static final int kXBOX_LT = 2;
        public static final int kXBOX_RT = 3;
        public static final int kXBOX_Y = 1;
    
        public static final double kTWIST_MULTIPLIER = 20;
        public static final double kTHROTTLE_MULTIPLIER = 30;
        public static final double kDEADZONE = 0.05;

    }

    public static final class LiftConstants {
        public static final int[] kLIFT_MOTOR_PORTS = {8,9};
        public static final int voltageMax = 40;
    }


}
