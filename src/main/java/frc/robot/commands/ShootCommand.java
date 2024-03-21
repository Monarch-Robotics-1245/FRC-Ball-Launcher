package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.BallShooterConstants;
import frc.robot.IO;
import frc.robot.subsystems.BallShooter;

public class ShootCommand extends Command {
    private final XboxController controller;
    private final BallShooter ballShooter;




    public ShootCommand(BallShooter ballShooterImpl) {
        addRequirements(ballShooterImpl);
        this.ballShooter = ballShooterImpl;
        this.controller = IO.getXboxController();
    }

    @Override
    public void execute() {

//
//           if(controller.getAButton()){
//               ballShooter.shootDefault(0.2);
//           }

        if(controller.getAButtonPressed()) {
            ballShooter.toggleShoot(BallShooterConstants.speed);
        }

        if(controller.getBackButtonPressed()) {
            ballShooter.lessPowerfulShoot();
        }

        // ballShooter.shootDefault(0.25);
    }
}
