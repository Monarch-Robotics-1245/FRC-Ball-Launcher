package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.IO;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    private final XboxController controller;
    private final Intake intake;

    public IntakeCommand(Intake intakeImpl) {
        addRequirements(intakeImpl);
        this.intake = intakeImpl;
        this.controller = IO.getXboxController();
    }

    @Override
    public void execute() {
        if(controller.getBButtonPressed()){
            intake.toggleSpin();
        }

    }
}
