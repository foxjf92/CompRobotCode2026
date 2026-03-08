// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeRollersSubsystem;

public class IntakeRollersIntakeCommand extends Command{
  private final IntakeRollersSubsystem m_intakeRollers;
  public double intakeSpeed; 

  public IntakeRollersIntakeCommand(IntakeRollersSubsystem intakeRollers, double speed){
    m_intakeRollers = intakeRollers;
    intakeSpeed = speed; 
    addRequirements(m_intakeRollers);
  }

  @Override
  public void initialize(){}

  @Override
  public void execute(){
    m_intakeRollers.spinRollersIntake(intakeSpeed);
  }
}