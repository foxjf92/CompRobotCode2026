// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private SparkFlex intakeLeftVortex; 
  private SparkFlex intakeRightVortex; 
  private SparkFlexConfig intakeVortexConfig;
  private SparkMax leftIntakeOut;
  private SparkMax rightIntakeOut;
  private SparkMaxConfig intakeNeoConfig;
  public double currentPosition;

  public IntakeSubsystem() {

    intakeVortexConfig = new SparkFlexConfig();
        
        intakeVortexConfig
          .smartCurrentLimit(80)
          .idleMode(com.revrobotics.spark.config.SparkBaseConfig.IdleMode.kBrake)
          .voltageCompensation(12);

        intakeLeftVortex = new SparkFlex(17, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        intakeLeftVortex.configure(intakeVortexConfig, com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, com.revrobotics.spark.SparkBase.PersistMode.kNoPersistParameters);
        
        intakeRightVortex = new SparkFlex(18, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        intakeRightVortex.configure(intakeVortexConfig, com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, com.revrobotics.spark.SparkBase.PersistMode.kNoPersistParameters);


      intakeNeoConfig = new SparkMaxConfig();
        intakeNeoConfig
          .smartCurrentLimit(20)
          .idleMode(IdleMode.kBrake);


    leftIntakeOut = new SparkMax(0,MotorType.kBrushless);
    leftIntakeOut.configure(intakeNeoConfig);
    rightIntakeOut = new SparkMax(0,MotorType.kBrushless);
    rightIntakeOut.configure(intakeNeoConfig);

    rightIntakeOutEncoder = rightIntakeOut.getEncoder();
    leftIntakeOutEncoder = leftIntakeOut.getEncoder();
  }    


  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command spinIntake(double speed) {
   intakeLeftVortex.set(speed);
   intakeRightVortex.set(-speed);
   
  }

    public Command intakeRetract() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.

    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

    public Command intakeExtend() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }


  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public void moveIntake(double intakeSpeed){
    rightIntakeOut.set(intakeSpeed);
    leftIntakeOut.set(-intakeSpeed);
  }


  @Override
  public void periodic() {
    currentPosition = rightIntakeOutEncoder;
    SmartDashboard.putNumber("IntakePosition",currentPosition);
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
