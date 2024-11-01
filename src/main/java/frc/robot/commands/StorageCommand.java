package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.IO;
import frc.robot.subsystems.Storage;

public class StorageCommand extends Command {
    private final XboxController controller;
    private final Storage storage;

    public StorageCommand(Storage storageImpl) {
        addRequirements(storageImpl);
        this.storage = storageImpl;
        this.controller = IO.getXboxController();
    }

    @Override
    public void execute() {
        if(controller.getYButtonPressed()){
            storage.toggleSpin();
        }
        if(storage.isRunning() && !controller.getYButton())
            storage.toggleSpin();

    }

}
