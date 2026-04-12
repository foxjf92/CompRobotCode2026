package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeDeploySubsystem extends SubsystemBase {
  private final SparkMax leftOutNeo;
  private final SparkMax rightOutNeo;
  private final SparkMaxConfig intakeDeployConfig;
  public final RelativeEncoder leftEncoder;

  public double currentPosition;

  public IntakeDeploySubsystem() {
    intakeDeployConfig = new SparkMaxConfig();
    intakeDeployConfig
        .smartCurrentLimit(15)
        .idleMode(IdleMode.kBrake);

    leftOutNeo = new SparkMax(10, MotorType.kBrushless);
    leftOutNeo.configure(intakeDeployConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
    rightOutNeo = new SparkMax(13, MotorType.kBrushless);
    rightOutNeo.configure(intakeDeployConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);

    leftEncoder = leftOutNeo.getEncoder();
  }

  // public void extendIntake() {
  //   leftOutNeo.set(0.1);
  //   rightOutNeo.set(-0.1);
  // }

  // public void holdDownIntake() {
  //   leftOutNeo.set(0.1);
  //   rightOutNeo.set(-0.1);
  // }

  // public void retractIntake() {
  //   leftOutNeo.set(-0.1);
  //   rightOutNeo.set(0.1);
  // }

  // public void holdUpIntake() {
  //   leftOutNeo.set(-0.1);
  //   rightOutNeo.set(0.1); 
  // }

  public void moveIntake(double intakeSpeed) {
    leftOutNeo.set(intakeSpeed);
    rightOutNeo.set(-intakeSpeed);
  }

  @Override
  public void periodic() {
    currentPosition = leftEncoder.getPosition();
    // Publish left encoder position
    SmartDashboard.putNumber("IntakeDeployLeftPosition", currentPosition);
    // SmartDashboard.putNumber("Retract Control effort", controlEffort);

  }
}
