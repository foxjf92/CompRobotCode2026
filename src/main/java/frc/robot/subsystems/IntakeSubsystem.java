// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import com.revrobotics.spark.SparkFlex;
// import com.revrobotics.spark.config.SparkFlexConfig;
// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.config.SparkMaxConfig;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
// import com.revrobotics.spark.SparkBase.ResetMode;
// import com.revrobotics.spark.SparkBase.PersistMode;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class IntakeSubsystem extends SubsystemBase {
//   /** Creates a new ExampleSubsystem. */

//   private SparkFlex intakeLeftVortex; 
//   private SparkFlex intakeRightVortex; 
//   private SparkFlexConfig intakeVortexConfig;
//   private SparkMax leftIntakeOut;
//   private SparkMax rightIntakeOut;
//   private SparkMaxConfig intakeNeoConfig;
//   private double rightIntakeOutEncoder;
//   // private double leftIntakeOutEncoder;
//   public double currentPosition;

//   public IntakeSubsystem() {

//     intakeVortexConfig = new SparkFlexConfig();

//     intakeVortexConfig
//         .smartCurrentLimit(10)
//         .idleMode(IdleMode.kBrake)
//         .voltageCompensation(12);

//     intakeLeftVortex = new SparkFlex(17, MotorType.kBrushless);
//     intakeLeftVortex.configure(intakeVortexConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

//     intakeRightVortex = new SparkFlex(18, MotorType.kBrushless);
//     intakeRightVortex.configure(intakeVortexConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

//     intakeNeoConfig = new SparkMaxConfig();
//     intakeNeoConfig
//         .smartCurrentLimit(20)
//         .idleMode(IdleMode.kBrake);

//     leftIntakeOut = new SparkMax(0, MotorType.kBrushless);
//     leftIntakeOut.configure(intakeNeoConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
//     rightIntakeOut = new SparkMax(1, MotorType.kBrushless);
//     rightIntakeOut.configure(intakeNeoConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
//   }


//   /**
//    * Example command factory method.
//    *
//    * @return a command
//    */
//   public void spinIntake(double speed) {
//     intakeLeftVortex.set(speed);
//     intakeRightVortex.set(-speed);
//   }

//     public Command intakeRetract() {
//     // Inline construction of command goes here.
//     // Subsystem::RunOnce implicitly requires `this` subsystem.

//     return runOnce(
//         () -> {
//           /* one-time action goes here */
//         });
//   }

//     public Command intakeExtend() {
//     // Inline construction of command goes here.
//     // Subsystem::RunOnce implicitly requires `this` subsystem.
//     return runOnce(
//         () -> {
//           /* one-time action goes here */
//         });
//   }


//   /**
//    * An example method querying a boolean state of the subsystem (for example, a digital sensor).
//    *
//    * @return value of some boolean subsystem state, such as a digital sensor.
//    */
//   public boolean exampleCondition() {
//     // Query some boolean state, such as a digital sensor.
//     return false;
//   }

//   public void moveIntake(double intakeSpeed) {
//     rightIntakeOut.set(intakeSpeed);
//     leftIntakeOut.set(-intakeSpeed);
//   }


//   @Override
//   public void periodic() {
//     // update encoder readings
//     try {
//       rightIntakeOutEncoder = rightIntakeOut.getEncoder().getPosition();
//       leftIntakeOutEncoder = leftIntakeOut.getEncoder().getPosition();
//     } catch (Exception e) {
//       // ignore sensor errors in periodic
//     }
//     currentPosition = rightIntakeOutEncoder;
//     SmartDashboard.putNumber("IntakePosition", currentPosition);
//     // This method will be called once per scheduler run
//   }

//   @Override
//   public void simulationPeriodic() {
//     // This method will be called once per scheduler run during simulation
//   }
// }
