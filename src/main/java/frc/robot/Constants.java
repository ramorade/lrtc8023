// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public class DriveTrainConstants {
       
        public static final int kLeftDrive = 0;//change when they plug in the ports to whatever they set it to
        public static final int kRightDrive = 1;
    }

    public class OIConstants {
        public static final int kDriverControllerPort = 1; //it is whatever usb port it is defined as in FRC Driver Station

        //buttons for driver joystick
        public static final int kMoveAxis = 3;//XY axis
        public static final int kRotate = 4;//rotate axis 
        public static final int kTrigger = 1;//trigger
        public static final int kButton = 2;//thumb button 
        
    
    }

    public class XBConstants {
        public static final int kXBoxControllerPort = 0;

        //buttons for xbox controller
        //public static final int kLeftBumper = 9;
        // public static final int kRightTrigger = 6;
        // public static final int kLeftTrigger = 2;
        public static final int kAButton = 1;
        public static final int kBButton = 2;
        public static final int kYButton = 4;
        public static final int kLeftBumper = 5;
        public static final int kRightBumper = 6;



    }

    public class PSConstants {
        public static final int kPSControllerPort = 2;

        //buttons for ps4 controller
        public static final int kRightTrigger = 6;
        public static final int kLeftTrigger = 7;
        public static final int kXButton = 1;
        public static final int kOButton = 2;
        public static final int kSButton = 3;
        public static final int kTButton = 4;



        
    }


}
