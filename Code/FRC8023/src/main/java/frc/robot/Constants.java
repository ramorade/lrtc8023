// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
        public static final int kDriverControllerPort = 0; //it is whatever usb port it is defined as in FRC Driver Station

        //buttons for driver joystick
        public static final int kMoveAxis = 1;//XY axis
        public static final int kRotate = 4;//rotate axis 
    
    }
}
