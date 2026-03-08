package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeDeploySubsystem;

public class IntakeRetractCommand extends Command{
    private final IntakeDeploySubsystem m_intakeDeploy;
    // private int targetPosition; //Symbolic intake position where 1 = intake retracted, 2 = intake deployed, 3 = launch position? If needed
    public static double targetSetpoint; // Encoder position value that corresponds to arm position

    // // Starting point for PID control if needed
    // public final double kP = 0.04; //2nd 
    // public final double kI = 0.0; //4th
    // public final double kD = 0.0; //3rd
    // public final double arbFF = 0.0; // Start Here
    // private PIDController m_IntakeDeployPID = new PIDController(kP,kI,kD); // look @ profiled PID maybe?

    public IntakeRetractCommand(IntakeDeploySubsystem intakeDeploy){
        m_intakeDeploy = intakeDeploy; 
        // targetPosition = target;
        addRequirements(m_intakeDeploy);
    }

    @Override
    public void initialize(){
        targetSetpoint = Constants.IntakeConstants.intakeRetractedPosition;        
    }

    @Override
    public void execute(){
        if(m_intakeDeploy.currentPosition > targetSetpoint)
        {
            m_intakeDeploy.retractIntake();
        }
        else
        {
            m_intakeDeploy.holdUpIntake();
        }

        // double controlEffort = m_IntakeDeployPID.calculate(m_intakeDeploy.intakeEncoder.getPosition(), targetSetpoint); // adds FF input to fight gravity, subtract PID output due to encoder inversion
        // m_intakeDeploy.moveIntake(controlEffort);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}