package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

public class DrivetrainSubsystem extends SubsystemBase {
    //front and rear may be mixed up for right and left portions. 
    //but the right should be 0 and 1, and left should be 2 and 3
    public static WPI_VictorSPX m_leftFront = new WPI_VictorSPX(1);
    public static WPI_VictorSPX m_leftRear = new WPI_VictorSPX(2);
    public static WPI_VictorSPX m_rightFront = new WPI_VictorSPX(3);
    public static WPI_VictorSPX m_rightRear = new WPI_VictorSPX(4);
 
    public static MotorControllerGroup m_left;
    public static MotorControllerGroup m_right;  
     
    public static DifferentialDrive m_drive;

    public boolean IsFaster = false;
    public boolean IsSlower = false;

    public DrivetrainSubsystem() {
        m_leftFront.setNeutralMode(NeutralMode.Brake);
        m_leftRear.setNeutralMode(NeutralMode.Brake);
        m_rightFront.setNeutralMode(NeutralMode.Brake);
        m_rightRear.setNeutralMode(NeutralMode.Brake);

        m_left = new MotorControllerGroup(m_leftFront, m_leftRear);
        m_right = new MotorControllerGroup(m_rightFront, m_rightRear);

        m_left.setInverted(true);

        m_drive = new DifferentialDrive(m_left, m_right);
        //m_right.setInverted(false);
    }

    public void drive(double forward, double rotate, double fastMode, double slowMode){
        double throttleFactor = 0.6;

        if (fastMode > 0.2) {
            IsFaster = true;
        } else {
            IsFaster = false;
        }

        if (slowMode > 0.2) {
            IsSlower = true;
        } else {
            IsSlower = false;
        }

        if (IsFaster) {
            throttleFactor = (0.40 * fastMode) + 0.6;
        }

        if (IsSlower) {
            throttleFactor = (-0.2 * slowMode) + 0.6;
        }

        m_drive.arcadeDrive((forward * throttleFactor *-1), (rotate * throttleFactor*-1));
    }


    public void drive(double forward, double rotate){
        double throttleFactor = 1.0;
        // if (IsFaster) {
        //     throttleFactor = 0.85;
        // }

        // if (IsSlower) {
        //     throttleFactor = 0.4;
        // }

        m_drive.arcadeDrive((forward * throttleFactor ), (rotate * throttleFactor));
    }
}
