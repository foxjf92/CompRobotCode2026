package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.IntakeDeployConstants;
import frc.robot.subsystems.IntakeDeploySubsystem;

public class IntakeExtendCommand extends Command{
    private final IntakeDeploySubsystem m_intakeDeploy;
    private int targetPosition; //Symbolic arm position where 1 = ground intake, 2 = amp position, 3 = launch position
    public static double wristSetpoint = Constants.IntakeDeployConstants.intakeExtendPosition; // Encoder position value that corresponds to arm position

    public final double kP = 0.04; //2nd 
    public final double kI = 0.0004; //4th
    public final double kD = 0.004; //3rd
    public final double arbFF = 0.04; // Start Here
    
    private PIDController m_wristPID = new PIDController(kP,kI,kD); // look @ profiled PID maybe?

    public IntakeExtendCommand(IntakeDeploySubsystem intakeDeploy){
        m_intakeDeploy = intakeDeploy; 
        // targetPosition = m_position;
        addRequirements(m_intakeDeploy);
    }


    @Override
    public void initialize(){}

    @Override
    public void execute(){

        double controlEffort = -arbFF + m_wristPID.calculate(m_intakeDeploy.leftEncoder.getPosition(), wristSetpoint); // adds FF input to fight gravity, subtract PID output due to encoder inversion

        // double controlEffort = -arbFF;
        m_intakeDeploy.moveIntake(controlEffort);
        SmartDashboard.putNumber("Extend Control effort", controlEffort);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}