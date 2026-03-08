package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeRollersSubsystem extends SubsystemBase {
  private final SparkFlex leftRollerMotor;
  private final SparkFlex rightRollerMotor;
  private final SparkFlexConfig rollerConfig;

  public IntakeRollersSubsystem() {
    rollerConfig = new SparkFlexConfig();
    rollerConfig
        .smartCurrentLimit(1)
        .idleMode(IdleMode.kBrake)
        .voltageCompensation(12);

    leftRollerMotor = new SparkFlex(17, MotorType.kBrushless);
    leftRollerMotor.configure(rollerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kNoPersistParameters);

    rightRollerMotor = new SparkFlex(18, MotorType.kBrushless);
    rightRollerMotor.configure(rollerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kNoPersistParameters);
  }

  public void spinRollersIntake(double speed) {
    leftRollerMotor.set(speed);
    rightRollerMotor.set(speed);
  }

  @Override
  public void periodic() {
    // nothing for now
  }
}
