// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.commands.TeleopShootCommand;
import frc.robot.commands.AutoDriveIntakeCMD;
import frc.robot.commands.AutoDriveCMD;
import frc.robot.commands.TeleopIntakeCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShootSubsystem m_shootSubsystem = new ShootSubsystem();

  // make sure it's a command not a string. 
  // Also it's not  final because what m_Chooser is assigned changes
  private SendableChooser<Command> m_Chooser;

  CommandXboxController m_controller1 = new CommandXboxController(Constants.ControllerConstants.kXboxController1Port); //drive controller
  CommandXboxController m_controller2 = new CommandXboxController(Constants.ControllerConstants.kXboxController2Port); //shoot/intake/actuator controller

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    
    /*
      This is added because I believe it runs the method and prevents a null error 
      since m_chooser will be assigned a command once the method runs
    */
    configureAuton();

    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        Commands.run(
            () ->
                m_robotDrive.drive((m_controller1.getLeftY())*0.5, m_controller1.getLeftX()), m_robotDrive));

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    // while a is being held on the 2nd controller, create a new instance of TeleopIntakeCommand
    m_controller2.a().whileTrue(new TeleopIntakeCommand(m_intakeSubsystem));
    m_controller2.x().whileTrue(new TeleopShootCommand(m_shootSubsystem));


  }
  // creates method to hold the auto options 
  public void configureAuton(){
    /*
      finishes the sendableChooser object. Similar to how motorcontrollers are set up.
      but you can also finish the set up on line 36 so on line 36, it looks as follows:
          private SendableChooser<Command> m_Chooser = new SendableChooser<>();
     */
    m_Chooser = new SendableChooser<>();
    // Adds the Drive Only Auto command
    m_Chooser.addOption("Drive Only", new AutoDriveCMD(m_robotDrive));
    // Adds the DriveIntakeCMD
    m_Chooser.addOption("Drive, Intake, Shoot", new AutoDriveIntakeCMD(m_robotDrive, m_intakeSubsystem));
    
    // This is a default opition in case no auto is selected. It's good to have as a back up in case someone forgets to select an auto
    m_Chooser.setDefaultOption("DRIVE ONLY", new AutoDriveCMD(m_robotDrive));
    // Creates the drop down to choose an auto on Shuffleboard 
    SmartDashboard.putData("Auto Chooser", m_Chooser);


  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_Chooser.getSelected();  // When the robot runs auto, it looks to see what chooser is selected
  }
}
