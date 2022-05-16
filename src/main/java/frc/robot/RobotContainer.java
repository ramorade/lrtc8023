// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.PSConstants;
import frc.robot.Constants.XBConstants;
import frc.robot.subsystems.ControlArmSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //Instantiates the pins with switch and sets boolean for code to reference for auto
  static DigitalInput autoSwitch = new DigitalInput(0);
  // static boolean autoBool = autoSwitch.get();
  


  //code for joystick instantian, etc
  //private final Joystick m_driverController = new Joystick(OIConstants.kDriverControllerPort);
  //private final PS4Controller m_driverController = new PS4Controller(PSConstants.kPSControllerPort);
  private final XboxController m_driverController = new XboxController(XBConstants.kXBoxControllerPort);
  private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
  private final ControlArmSubsystem m_controlArm = new ControlArmSubsystem();
  private final Robot m_robot = new Robot();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    
    //Instantiates the camera and changes various settings
    UsbCamera camera = CameraServer.startAutomaticCapture(0);
    camera.setResolution(320, 240);
    camera.setFPS(20);
  
    //configures buttons binds (obviously)
    configureButtonBindings();

    //sets the default command for the drive train. Basically it's getting 
    m_drivetrain.setDefaultCommand(
        new RunCommand(() -> m_drivetrain.drive(
            m_driverController.getRawAxis(1),
            m_driverController.getRawAxis(4),
            m_driverController.getRawAxis(3),
            m_driverController.getRawAxis(2)), m_drivetrain)); 
            // for the second axis, its 4 for xbox and 2 for ps4
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //mid bar raising command to be called by left bumper
    Command m_raise = new SequentialCommandGroup(
      new RunCommand(()-> m_controlArm.midHanger(1.0),
       m_controlArm).withTimeout(1.28),
      new RunCommand(() -> m_controlArm.stopMid(), m_controlArm).withTimeout(0.01));

    // final Command m_raise = new RunCommand(() -> m_controlArm.midHanger(0.4),
    //         m_controlArm).withTimeout(2);
    // new JoystickButton(m_driverController, XBConstants.kLeftTrigger)
    //     .whenPressed(() -> {
    //       m_drivetrain.IsSlower = true;
    //     })
    //     .whenReleased(() -> {
    //       m_drivetrain.IsSlower = false;
    //     });

    // new JoystickButton(m_driverController, XBConstants.kRightTrigger)
    //     .whenPressed(() -> {
    //       m_drivetrain.IsFaster = true;
    //     })
    //     .whenReleased(() -> {
    //       m_drivetrain.IsFaster = false;
    //     });

    new JoystickButton(m_driverController, XBConstants.kAButton)
        .whenPressed(() -> {
          m_controlArm.clampLow();
        })
        .whenReleased(() -> {
          m_controlArm.stopLow();
        });

    new JoystickButton(m_driverController, XBConstants.kBButton)
        .whenPressed(() -> {
          m_controlArm.safety = 1;
        })
        .whenReleased(() -> {
          m_controlArm.safety = 0;
        });

    // new JoystickButton(m_driverController, XBConstants.kLeftBumper)
    //     .whenPressed(() -> {
    //       // m_controlArm.clampMid();
    //       m_controlArm.midHanger(0.4);
    //     })
    //     .whenReleased(() -> {
    //       m_controlArm.stopMid();
    //     });

    new JoystickButton(m_driverController, XBConstants.kLeftBumper)
        .whenPressed(() -> {
          // m_controlArm.clampMid();
          // m_controlArm.midHanger(0.3);
          
          // if (m_controlArm.safety == 0){
          //   m_driverController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
          //   m_driverController.setRumble(GenericHID.RumbleType.kRightRumble, 1);
          // }
          // else {
          //   m_driverController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
          //   m_driverController.setRumble(GenericHID.RumbleType.kRightRumble, 0);
          // }

          //command is at start of this binding class 
          m_raise.schedule();
          // m_raise.withTimeout(1.0);  
        })
        ;
        // .whenReleased(() -> {
        //   m_controlArm.stopMid();
        // });
    
    new JoystickButton(m_driverController, XBConstants.kRightBumper)
        .whenPressed(() -> {
          m_controlArm.midHanger(1.0);
        })
        .whenReleased(() -> {
          m_controlArm.stopMid();
        }); 
        
        new JoystickButton(m_driverController, XBConstants.kYButton)
          .whenPressed(() -> {
            m_controlArm.direction *= -1;
          });
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    boolean autoBool = autoSwitch.get();
    
    if (autoBool){
      return new SequentialCommandGroup(
        new RunCommand(()-> m_drivetrain.drive(
          1.0, 0), m_drivetrain).withTimeout(0.80),
        new RunCommand(()-> m_drivetrain.drive(0.0, 0.0), 
        m_drivetrain).withTimeout(1.5),
        new RunCommand(() -> m_drivetrain.drive(
          -0.75, 0), m_drivetrain).withTimeout(2.0));
    } else {
      return new RunCommand(() -> m_drivetrain.drive(
        -0.75,
        0.0), m_drivetrain).withTimeout(1.85);
    }
    
    // return new RunCommand(() -> m_drivetrain.drive(
    //     -0.75,
    //     0), m_drivetrain).withTimeout(2);

    // return new SequentialCommandGroup(
    //   new RunCommand(()-> m_drivetrain.drive(
    //         1.0, 0), m_drivetrain).withTimeout(0.80), 
    //   new RunCommand(() -> m_drivetrain.drive(
    //         -0.75, 0), m_drivetrain).withTimeout(2.0)); 

  }
}
