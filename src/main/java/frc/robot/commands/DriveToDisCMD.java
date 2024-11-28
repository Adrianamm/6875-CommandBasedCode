// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveToDisCMD extends Command {
  /** Creates a new beepboopAutoCMD. */
  private final DriveSubsystem m_DriveSubsystem;
  private final double speed;
  private final double targetDistance;

  /*
    This is set up to take in 3 parameters, drive, speed and targetDistance
      Speed and targetDistance will later get numbers once they are passed in
      when DriveToDisCMD is used in the SequentialCommandGroup for auto
  */
  public DriveToDisCMD(DriveSubsystem drive, double speed, double targetDistance) {
    m_DriveSubsystem = drive;
    this.speed = speed;
    this.targetDistance = targetDistance;
    addRequirements(m_DriveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
      when this command is called, it takes in speed and targetDistance. 
      These are passed in when the method is called in the SequentialCommandGroup
    */ 
    m_DriveSubsystem.driveDistance(speed, targetDistance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //prints "auto done" when distance is reached and the command is done
    DriverStation.reportWarning("auto done", false);
    return true; //command finishes after distance is reached
  }
}
