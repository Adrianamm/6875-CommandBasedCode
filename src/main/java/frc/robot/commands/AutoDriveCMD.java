// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDriveCMD extends SequentialCommandGroup {
  /** Creates a new AutoDriveCMD. */
  public AutoDriveCMD(DriveSubsystem m_DriveSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
    // add all commands inside addComands()
    addCommands(
      // Prints that auto is running
      new PrintCommand("auto running"),
      // calls the DriveToDisCMD. It should run at the speed of 0.5 and the targetDistance is 10
      new DriveToDisCMD(m_DriveSubsystem, 0.5, 10),
      // prints when DriveToDisCMD is finished
      new PrintCommand("Sequential command: done")
    );
  }
}
