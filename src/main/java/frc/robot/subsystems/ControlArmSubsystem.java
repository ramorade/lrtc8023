package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlArmSubsystem extends SubsystemBase {
    public static WPI_VictorSPX m_lowRung = new WPI_VictorSPX(6);
    public static WPI_VictorSPX m_midRung = new WPI_VictorSPX(5);
    
    
    //Instantiates the pins with switch and sets boolean for code to reference for auto
    static DigitalInput ratchetSwitch = new DigitalInput(1);
    
    
    //use this for network table in case ratchet bool doesnt work

    public int direction = 1; 
    public int safety = 0;

    public ControlArmSubsystem() {
        // m_lowRung.setNeutralMode(NeutralMode.Brake);
        m_midRung.setNeutralMode(NeutralMode.Brake);
    }

    //low hanger functions
    public void clampLow(){
        m_lowRung.set(-1.0);
    }
    public void stopLow(){
        m_lowRung.stopMotor();
    }
    public void releaseLow(){
        m_lowRung.set(1.0);
    }
    
    //Mid hanger functions
    public void stopMid(){
        m_midRung.stopMotor();
    }
    public void midHanger(double speed){
        boolean ratchetBool = ratchetSwitch.get(); 
        if (ratchetBool){
            m_midRung.set(speed * direction * safety);
        } else {
            m_midRung.set(speed * -1 * direction * safety);
        }
        
        // m_midRung.set(speed * -1);
        // //when the rope is under, it is positive
        // //when it is over, it is negative

    }

}
