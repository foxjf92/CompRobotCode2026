package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeRollersSubsystem extends SubsystemBase {
  private final SparkFlex leftRollerMotor;
  private final SparkFlex rightRollerMotor;
  private final SparkFlexConfig rollerConfig;
  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;

  public IntakeRollersSubsystem() {
    rollerConfig = new SparkFlexConfig();
    rollerConfig
      .smartCurrentLimit(40) // TODO tune current limit at some point
      .idleMode(IdleMode.kBrake)
      .voltageCompensation(12);

    leftRollerMotor = new SparkFlex(14, MotorType.kBrushless);
    leftRollerMotor.configure(rollerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kNoPersistParameters);
    leftEncoder = leftRollerMotor.getEncoder();

    rightRollerMotor = new SparkFlex(15, MotorType.kBrushless);
    rightRollerMotor.configure(rollerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kNoPersistParameters);
    rightEncoder = rightRollerMotor.getEncoder();
  }

  public void spinRollersCollect(double speed) {
    leftRollerMotor.set(speed);
    rightRollerMotor.set(0.33*speed); // was .67, speed match is .33
  }

  public void spinRollersFeed(double speed) {
    leftRollerMotor.set(speed);
    rightRollerMotor.set(0.33*speed); // same as above speed reduction
  }

  public void spinRollersDeployIntake(double speed) {
    leftRollerMotor.set(speed);
  }

  @Override
  public void periodic() {
    // Publish intake roller telemetry
    // double leftRpm = leftEncoder.getVelocity();
    // double rightRpm = rightEncoder.getVelocity();
    // double leftCurrent = leftRollerMotor.getOutputCurrent();
    // double rightCurrent = rightRollerMotor.getOutputCurrent();

    // SmartDashboard.putNumber("IntakeLeftRollerRPM", leftRpm);
    // SmartDashboard.putNumber("IntakeRightRollerRPM", rightRpm);
    // SmartDashboard.putNumber("IntakeLeftRollerCurrent", leftCurrent);
    // SmartDashboard.putNumber("IntakeRightRollerCurrent", rightCurrent);
  }
}
