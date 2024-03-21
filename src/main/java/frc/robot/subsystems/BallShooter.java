package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ShootCommand;

public class BallShooter extends SubsystemBase{
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public boolean isShooting = false;
    public BallShooter() {

        this.leftMotor = new CANSparkMax(24, CANSparkLowLevel.MotorType.kBrushless);
        this.rightMotor = new CANSparkMax(25, CANSparkLowLevel.MotorType.kBrushless);

        SparkPIDController leftPIDController = this.leftMotor.getPIDController();
        SparkPIDController rightPIDController = this.rightMotor.getPIDController();

        kP = 0;
        kI = 0;
        kD = 0;
        kIz = 0;
        kFF = 0;
        kMaxOutput = 1;
        kMinOutput = -1;
        maxRPM = 5700;

        leftPIDController.setP(kP);
        rightPIDController.setP(kP);
        leftPIDController.setI(kI);
        rightPIDController.setI(kI);
        leftPIDController.setFF(kFF);
        rightPIDController.setFF(kFF);
        leftPIDController.setOutputRange(kMinOutput, kMaxOutput);
        rightPIDController.setOutputRange(kMinOutput, kMaxOutput);

        setDefaultCommand(new ShootCommand(this));
    }

    @Override
    public void periodic() {}

    public void turnShooterOn(double speed) {
        leftMotor.set(speed);
        rightMotor.set(-speed);
        isShooting = true;
    }

    public void turnShooterOff() {
        leftMotor.stopMotor();
        rightMotor.stopMotor();
        isShooting = false;
    }

    public void toggleShoot(double speed) {
        if(!isShooting) {
            turnShooterOn(speed);
        }else{
            turnShooterOff();
        }
    }

    public void lessPowerfulShoot() {
        if(!isShooting) {
            turnShooterOn(1);
        }else{
            turnShooterOff();
        }
    }

}

