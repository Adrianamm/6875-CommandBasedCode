// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShootSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopShootCommand extends Command {

  private final ShootSubsystem m_shootSubsystem;

  /**
   * Creates a new TeleopShootCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopShootCommand(ShootSubsystem shootSubsystem) {
    m_shootSubsystem = shootSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shootSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shootSubsystem.runNeos(0.7);
    if (m_shootSubsystem.getShootSpeed(0.65)){
      m_shootSubsystem.runSims(0.7);
      DriverStation.reportWarning("Shoot Speed",false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_shootSubsystem.stopShoot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
