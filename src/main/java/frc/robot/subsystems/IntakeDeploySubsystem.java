package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Controls the intake out motors (NEOs) and their encoders. */
public class IntakeDeploySubsystem extends SubsystemBase {
  private final SparkMax leftOutNeo;
  private final SparkMax rightOutNeo;
  private final SparkMaxConfig intakeDeployConfig;
  private final RelativeEncoder leftEncoder;

  public double currentPosition;

  public IntakeDeploySubsystem() {
    intakeDeployConfig = new SparkMaxConfig();
    intakeDeployConfig
        .smartCurrentLimit(1)
        .idleMode(IdleMode.kBrake);

    leftOutNeo = new SparkMax(0, MotorType.kBrushless);
    leftOutNeo.configure(intakeDeployConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
    rightOutNeo = new SparkMax(1, MotorType.kBrushless);
    rightOutNeo.configure(intakeDeployConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);

    leftEncoder = leftOutNeo.getEncoder();
  }

  public void extendIntake(double speed) {

    leftOutNeo.set(-speed);
    rightOutNeo.set(speed);

  }

  public void holdDownIntake() {

    leftOutNeo.set(0);
    rightOutNeo.set(0);

  }

  @Override
  public void periodic() {
    currentPosition = leftEncoder.getPosition();
    // Publish left encoder position
    SmartDashboard.putNumber("IntakeDeployLeftPosition", currentPosition);

  }
}
