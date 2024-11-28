// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDriveIntakeCMD extends SequentialCommandGroup {
  /** Creates a new AutoDriveIntakeCMD. */
  public AutoDriveIntakeCMD(DriveSubsystem m_DriveSubsystem, IntakeSubsystem m_IntakeSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
   
    // add all commands inside addComands()
    addCommands(
      // prints the message, in SequentialCommandGroups, we use new PrintCommand() to print
      new PrintCommand("Drive Intake Auto has started"),
      // calls the DriveToDisCMD. It should run at the speed of 0.5 and the targetDistance is 15
      new DriveToDisCMD(m_DriveSubsystem, 0.5, 15),
      // prints when DriveToDisCMD is finished
      new PrintCommand("Driving done"),
      // Runs AutoIntakeCommand
      new AutoIntakeCommand(m_IntakeSubsystem),
      // Prints when the AutoIntakeCommand is done
      new PrintCommand("Intake Done")
    );
  }
}
