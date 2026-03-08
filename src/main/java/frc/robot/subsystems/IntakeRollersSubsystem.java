package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Controls the intake rollers (Vortex motors). */
public class IntakeRollersSubsystem extends SubsystemBase {
  private final SparkFlex leftRoller;
  private final SparkFlex rightRoller;
  private final SparkFlexConfig rollerConfig;

  public IntakeRollersSubsystem() {
    rollerConfig = new SparkFlexConfig();
    rollerConfig
        .smartCurrentLimit(1)
        .idleMode(IdleMode.kBrake)
        .voltageCompensation(12);

    leftRoller = new SparkFlex(17, MotorType.kBrushless);
    leftRoller.configure(rollerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kNoPersistParameters);

    rightRoller = new SparkFlex(18, MotorType.kBrushless);
    rightRoller.configure(rollerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kNoPersistParameters);
  }

  public void spinRollers(double speed) {
    leftRoller.set(speed);
    rightRoller.set(-speed);
  }

  @Override
  public void periodic() {
    // nothing for now
  }
}
