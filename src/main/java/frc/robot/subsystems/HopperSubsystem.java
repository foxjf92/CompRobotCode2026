// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {
  
  private SparkMax hopperLeftNeo;
  private SparkMax hopperRightNeo;
  private SparkMaxConfig hopperNeoConfig;

  public HopperSubsystem() {
    hopperNeoConfig = new SparkMaxConfig();

    hopperNeoConfig
        .smartCurrentLimit(1)
        .idleMode(IdleMode.kBrake)
        .voltageCompensation(12);

    hopperLeftNeo = new SparkMax(2, MotorType.kBrushless);
    hopperLeftNeo.configure(hopperNeoConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
    hopperRightNeo = new SparkMax(3, MotorType.kBrushless);
    hopperRightNeo.configure(hopperNeoConfig,com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
  }

  public void spinHopper(double speed) {
    hopperLeftNeo.set(speed);
    hopperRightNeo.set(speed);
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
