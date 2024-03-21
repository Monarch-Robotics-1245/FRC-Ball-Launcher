// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.StorageCommand;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Climb;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain;
  private final BallShooter ballShooter;
  private final Climb climb;
  private final Intake intake;
  private final Storage storage;
  private final DriveCommand driveCommand;
  private final ShootCommand shootCmd;
  private final IntakeCommand intakeCommand;
  private final StorageCommand storageCommand;
  private final ClimbCommand climbCommand;

  public RobotContainer() {
    // Configure the button bindings
    driveTrain = new DriveTrain();
    ballShooter = new BallShooter();
    intake = new Intake();
    storage = new Storage();
    climb = new Climb();
    driveCommand = new DriveCommand(driveTrain);
    shootCmd = new ShootCommand(ballShooter);
    intakeCommand = new IntakeCommand(intake);
    storageCommand = new StorageCommand(storage);
    climbCommand = new ClimbCommand(climb);
    configureButtonBindings();
  }

  private void configureButtonBindings() {}
}
