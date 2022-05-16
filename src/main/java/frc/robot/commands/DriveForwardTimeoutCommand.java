package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveForwardTimeoutCommand extends CommandBase {

    DrivetrainSubsystem m_drivetrainSubsystem;


    public DriveForwardTimeoutCommand(DrivetrainSubsystem drivetrainSubsystem) {
        m_drivetrainSubsystem = drivetrainSubsystem;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_drivetrainSubsystem.drive(0.1, 0.1);
    }
}
