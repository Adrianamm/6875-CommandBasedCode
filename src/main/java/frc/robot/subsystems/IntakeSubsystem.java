
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {


// declare all of our motors. use the constants for the port
 CANSparkMax leftIntake = new CANSparkMax(7, MotorType.kBrushless);
 CANSparkMax rightIntake = new CANSparkMax(8, MotorType.kBrushless);

//digital input sensor
 DigitalInput intakeSensor = new DigitalInput(Constants.IntakeConstants.kIntakeSensorPort);



 
  /** Creates a new ExampleSubsystem. */
  public IntakeSubsystem() {
     
    // set motor inversion 
    rightIntake.setInverted(false);
    leftIntake.setInverted(false);
   
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  // public Command exampleMethodCommand() {
  //   // Inline construction of command goes here.
  //   // Subsystem::RunOnce implicitly requires `this` subsystem.
  //   return runOnce(
  //       () -> {
  //         /* one-time action goes here */
  //       });
  // }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
 
  

  public void intake(double speed) {
    rightIntake.set(speed);
    leftIntake.set(speed);
  }   // end intake

    
  public void intakeStop() {
    rightIntake.set(0);
    leftIntake.set(0);
  }   // end intake

  public boolean isIntake(){
    return intakeSensor.get();
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
