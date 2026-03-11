// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {
  final TalonFX leftFeedMotor;
  final TalonFX rightFeedMotor;
  final TalonFXConfiguration feedMotorConfig;
  final CurrentLimitsConfigs feedMotorCurrentLimits;
  
  public FeederSubsystem() {
    feedMotorConfig = new TalonFXConfiguration();
    feedMotorConfig
      .MotorOutput.withNeutralMode(com.ctre.phoenix6.signals.NeutralModeValue.Brake);

    feedMotorCurrentLimits = new CurrentLimitsConfigs();
    feedMotorCurrentLimits.SupplyCurrentLimit = 1;         // limits power consumption
    feedMotorCurrentLimits.SupplyCurrentLimitEnable = true; 
    feedMotorCurrentLimits.StatorCurrentLimit = 5;         // limits acceleration and peak torque, top
    feedMotorCurrentLimits.StatorCurrentLimitEnable = true;

    feedMotorConfig
      .withCurrentLimits(feedMotorCurrentLimits);

    leftFeedMotor = new TalonFX(16, CANBus.roboRIO()); // TODO set CAN ID on hardware and in code
    leftFeedMotor.getConfigurator().refresh(feedMotorConfig);   
    leftFeedMotor.getConfigurator().apply(feedMotorConfig);

    rightFeedMotor = new TalonFX(17, CANBus.roboRIO()); // TODO set CAN ID on hardware and in code
    rightFeedMotor.getConfigurator().refresh(feedMotorConfig);
    rightFeedMotor.getConfigurator().apply(feedMotorConfig);
  }

  public void spinFeeder(double speed) {
    leftFeedMotor.set(speed);
    rightFeedMotor.set(-speed);
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
