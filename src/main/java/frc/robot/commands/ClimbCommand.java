/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.IO;
import frc.robot.subsystems.Climb;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;


/**
 * The Pneumatic climbing system
 */
public class ClimbCommand extends Command {
    private final Climb climb;
    private final XboxController controller;


    public ClimbCommand(Climb climbImpl) {
        addRequirements(climbImpl);
        this.climb = climbImpl;
        this.controller = IO.getXboxController();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        if (controller.getStartButtonPressed()) {
            System.out.println("Pressing button");
            if (Climb.getValue() != DoubleSolenoid.Value.kForward) {
                climb.extendClimb();
            }
            else {
                climb.retractClimb();
            }
        }
    }



    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}