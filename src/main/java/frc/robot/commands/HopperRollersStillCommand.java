// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.HopperSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class HopperRollersStillCommand extends Command{
  private final HopperSubsystem m_hopper;

  public HopperRollersStillCommand(HopperSubsystem hopper){
    m_hopper = hopper;
    addRequirements(m_hopper);
  }
  
  @Override
  public void initialize(){}

  @Override
  public void execute(){
    m_hopper.spinHopper(0);
  }

  @Override
  public boolean isFinished(){
      return false;
  }
}