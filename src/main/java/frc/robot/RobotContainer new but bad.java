// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot;

// import frc.robot.Constants.OperatorConstants;
// import frc.robot.commands.FeederCommand;
// import frc.robot.commands.HopperRollersFeedCommand;
// import frc.robot.commands.HopperRollersStillCommand;
// import frc.robot.commands.IntakeExtendCommand;
// import frc.robot.commands.IntakeRetractCommand;
// import frc.robot.commands.IntakeRollersExtendCommand;
// import frc.robot.commands.IntakeRollersFeedCommand;
// import frc.robot.commands.IntakeRollersIntakeCommand;
// import frc.robot.commands.LauncherCommand;
// import frc.robot.subsystems.FeederSubsystem;
// import frc.robot.subsystems.HopperSubsystem;
// import frc.robot.subsystems.IntakeRollersSubsystem;
// import frc.robot.subsystems.LauncherSubsystem;
// import frc.robot.subsystems.SwerveSubsystem;
// import frc.robot.subsystems.IntakeDeploySubsystem;
// import swervelib.SwerveInputStream;

// import java.io.File;
// import java.util.function.Supplier;

// import com.pathplanner.lib.auto.NamedCommands;
// import com.pathplanner.lib.commands.PathPlannerAuto;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.kinematics.ChassisSpeeds;
// import edu.wpi.first.wpilibj.Filesystem;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
// import edu.wpi.first.wpilibj2.command.button.Trigger;

// public class RobotContainer {

//   final CommandXboxController driverController = new CommandXboxController(1);
//   final CommandXboxController operatorController = new CommandXboxController(0);

//   private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

//   private final IntakeDeploySubsystem intakeDeploy = new IntakeDeploySubsystem();
//   private final IntakeRollersSubsystem intakeRollers = new IntakeRollersSubsystem();
//   private final HopperSubsystem hopper = new HopperSubsystem();
//   private final FeederSubsystem feeder = new FeederSubsystem();
//   private final LauncherSubsystem launcher = new LauncherSubsystem();

//   // Intake Commands
//   Command intakeRollersIntake = new IntakeRollersIntakeCommand(intakeRollers, 0.50);
//   Command intakeRollersReverse = new IntakeRollersIntakeCommand(intakeRollers, -0.5);
//   Command intakeRollersFeed = new IntakeRollersFeedCommand(intakeRollers, 0.25);
//   Command intakeRollersPass = new IntakeRollersFeedCommand(intakeRollers, 0.25);
//   Command intakeRollersDeployIntake = new IntakeRollersExtendCommand(intakeRollers, -0.1);
//   Command intakeRollersStill = new IntakeRollersIntakeCommand(intakeRollers, 0.0);
//   Command intakeExtend = new IntakeExtendCommand(intakeDeploy);
//   Command intakeRetract = new IntakeRetractCommand(intakeDeploy);
//   Command intakeLaunchRetract = new IntakeRetractCommand(intakeDeploy);
//   Command intakePassRetract = new IntakeRetractCommand(intakeDeploy);
//   Command intakeLaunchRetractDelay = new WaitCommand(0.5);
//   Command intakePassRetractDelay = new WaitCommand(0.5);
//   Command intakeRollersIntakeAuto = new IntakeRollersIntakeCommand(intakeRollers, 0.5);
//   Command intakeRollersStillAuto = new IntakeRollersIntakeCommand(intakeRollers, 0.0);
//   Command intakeExtendAuto = new IntakeExtendCommand(intakeDeploy);
//   Command intakeRetractAuto = new IntakeRetractCommand(intakeDeploy);

//   // Hopper Commands
//   Command hopperFeed = new HopperRollersFeedCommand(hopper, 0.7);
//   Command hopperPass = new HopperRollersFeedCommand(hopper, 0.7);
//   Command hopperStill = new HopperRollersStillCommand(hopper);
//   Command hopperFeedAuto = new HopperRollersFeedCommand(hopper, 0.5);
//   Command hopperStillAuto = new HopperRollersStillCommand(hopper);

//   // Feeder Commands
//   Command feederFeed = new FeederCommand(feeder, 0.6);
//   Command feederPass = new FeederCommand(feeder, 0.8);
//   Command feederStill = new FeederCommand(feeder, 0.0);
//   Command feedDelay = new WaitCommand(0.2);
//   Command passDelay = new WaitCommand(0.2);
//   Command feederFeedAuto = new FeederCommand(feeder, 1.0);
//   Command feederStillAuto = new FeederCommand(feeder, 0.0);

//   // Launcher Commands
//   Command launcherLaunch = new LauncherCommand(launcher, 0.48);
//   Command launcherPass = new LauncherCommand(launcher, 0.8);
//   Command launcherStill = new LauncherCommand(launcher, 0.0);
//   Command launcherLaunchAuto = new LauncherCommand(launcher, 0.5);
//   Command launcherStillAuto = new LauncherCommand(launcher, 0.0);

//   // Swerve - base angular velocity stream (used as fallback when no snap button held)
//   SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
//                                                                 () -> driverController.getLeftY(),
//                                                                 () -> driverController.getLeftX())
//                                                             .withControllerRotationAxis(driverController::getRightX)
//                                                             .deadband(OperatorConstants.DEADBAND)
//                                                             .scaleTranslation(0.8)
//                                                             .allianceRelativeControl(false);

//   /**
//    * Returns the cardinal snap angle if any snap button is held, or null if none are.
//    * Y = away from driver (field forward, 0°)
//    * A = toward driver (field backward, 180°)
//    * B = right from driver perspective (270°)
//    * X = left from driver perspective (90°)
//    * Adjust degrees here if your field orientation differs.
//    */
//   private Rotation2d getSnapAngle() {
//     if (driverController.getHID().getYButton()) return Rotation2d.fromDegrees(0);
//     if (driverController.getHID().getAButton()) return Rotation2d.fromDegrees(180);
//     if (driverController.getHID().getBButton())  return Rotation2d.fromDegrees(270);
//     if (driverController.getHID().getXButton())  return Rotation2d.fromDegrees(90);
//     return null;
//   }

//   // Unified drive supplier: snaps to cardinal when a face button is held,
//   // otherwise falls back to standard angular velocity control.
//   Supplier<ChassisSpeeds> unifiedDriveSupplier = () -> {
//     Rotation2d snap = getSnapAngle();
//     if (snap != null) {
//       double x = MathUtil.applyDeadband(driverController.getLeftY(), OperatorConstants.DEADBAND);
//       double y = MathUtil.applyDeadband(driverController.getLeftX(), OperatorConstants.DEADBAND);
//       return drivebase.getTargetSpeeds(x, y, snap);
//     }
//     return driveAngularVelocity.get();
//   };

//   Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(unifiedDriveSupplier);

//   public RobotContainer() {

//     // PathPlanner named commands
//     NamedCommands.registerCommand("intakeDeployAuto", intakeExtendAuto);
//     NamedCommands.registerCommand("intakeRetractAuto", intakeRetractAuto);
//     NamedCommands.registerCommand("intakeRollersIntakeAuto", intakeRollersIntakeAuto);
//     NamedCommands.registerCommand("intakeRollersStillAuto", intakeRollersStillAuto);
//     NamedCommands.registerCommand("feederFeedAuto", feederFeedAuto);
//     NamedCommands.registerCommand("feederStillAuto", feederStillAuto);
//     NamedCommands.registerCommand("hopperFeedAuto", hopperFeedAuto);
//     NamedCommands.registerCommand("hopperStillAuto", hopperStillAuto);
//     NamedCommands.registerCommand("launcherLaunchAuto", launcherLaunchAuto);
//     NamedCommands.registerCommand("launcherStillAuto", launcherStillAuto);

//     configureBindings();

//     // Default commands
//     drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);
//     intakeRollers.setDefaultCommand(intakeRollersStill);
//     hopper.setDefaultCommand(hopperStill);
//     feeder.setDefaultCommand(feederStill);
//     launcher.setDefaultCommand(launcherStill);
//     intakeDeploy.setDefaultCommand(intakeRetract);
//   }

//   private void configureBindings() {
//     // Driver bindings
//     driverController.leftBumper().onTrue(new InstantCommand(drivebase::zeroGyro));

//     // Operator bindings
//     operatorController.rightBumper().onTrue(intakeExtend);
//     operatorController.leftBumper().onTrue(intakeRetract);
//     operatorController.rightTrigger().whileTrue(intakeRollersIntake);
//     operatorController.leftTrigger().whileTrue(intakeRollersReverse);

//     operatorController.a().whileTrue(launcherLaunch
//                                       .alongWith(feedDelay.andThen(feederFeed
//                                                             .alongWith(hopperFeed)
//                                                             .alongWith(intakeRollersFeed))));

//     operatorController.b().whileTrue(launcherPass
//                                       .alongWith(passDelay.andThen(feederPass
//                                                             .alongWith(hopperPass)
//                                                             .alongWith(intakeRollersPass)
//                                                             .alongWith(intakePassRetractDelay
//                                                               .andThen(intakePassRetract)))));
//   }

//   public Command getAutonomousCommand() {
//     return new PathPlannerAuto("MIDDLE AUTO");
//   }
// }