// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.FeederCommand;
import frc.robot.commands.HopperRollersFeedCommand;
import frc.robot.commands.HopperRollersStillCommand;
import frc.robot.commands.IntakeExtendCommand;
import frc.robot.commands.IntakeRetractCommand;
import frc.robot.commands.IntakeRollersFeedCommand;
import frc.robot.commands.IntakeRollersIntakeCommand;
import frc.robot.commands.LauncherCommand;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeRollersSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.IntakeDeploySubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  final CommandXboxController driverXbox = new CommandXboxController(1);
  final CommandXboxController operatorXbox = new CommandXboxController(0);
  
  private final IntakeDeploySubsystem intakeDeploy = new IntakeDeploySubsystem();
  private final IntakeRollersSubsystem intakeRollers = new IntakeRollersSubsystem();
  private final HopperSubsystem hopper = new HopperSubsystem();
  private final FeederSubsystem feeder = new FeederSubsystem();
  private final LauncherSubsystem launcher = new LauncherSubsystem();

  // Intake Commands
  Command intakeRollersIntake = new IntakeRollersIntakeCommand(intakeRollers, 0.01);
  Command intakeRollersFeed = new IntakeRollersFeedCommand(intakeRollers, 0.01);
  Command intakeExtend = new IntakeExtendCommand(intakeDeploy);
  Command intakeRetract = new IntakeRetractCommand(intakeDeploy);
  
  // Hopper Commands
  Command hopperFeed = new HopperRollersFeedCommand(hopper, 0.01);
  Command hopperStill = new HopperRollersStillCommand(hopper);

  // Feeder Commands
  Command feederFeed = new FeederCommand(feeder,0.01);

  // Launcher Commands
  Command launcherLaunch = new LauncherCommand(launcher, 0.01);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
