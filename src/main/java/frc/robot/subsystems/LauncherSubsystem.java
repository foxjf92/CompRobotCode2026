// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {
  
  private SparkFlex launcherLeftVortex; 
  private SparkFlex launcherRightVortex; 
  private SparkFlexConfig launcherVortexConfig;
  
  public LauncherSubsystem() {

    launcherVortexConfig = new SparkFlexConfig();
        
    launcherVortexConfig
      .smartCurrentLimit(120)
      .idleMode(IdleMode.kCoast)
      .voltageCompensation(12);

    launcherLeftVortex = new SparkFlex(20, MotorType.kBrushless); // TODO set CAN ID on hardware and in code
    launcherLeftVortex.configure(launcherVortexConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
    
    launcherRightVortex = new SparkFlex(19, MotorType.kBrushless); // TODO set CAN ID on hardware and in code
    launcherRightVortex.configure(launcherVortexConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);

  }

  public void spinLauncher(double speed) {
    launcherLeftVortex.set(speed);
    launcherRightVortex.set(-speed);
  }

  // public boolean atSpeed() {
  //   // Query some boolean state, such as a digital sensor.
  //   return false;
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
