package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ShootCommand;

public class BallShooter extends SubsystemBase{

    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;

    private final SparkPIDController leftPIDController;
    private final SparkPIDController rightPIDController;

    private final RelativeEncoder leftEncoder;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);



    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

    public boolean isShooting = false;

    private NetworkTableInstance table;

    public BallShooter() {

        this.leftMotor = new CANSparkMax(24, CANSparkLowLevel.MotorType.kBrushless);
        this.rightMotor = new CANSparkMax(25, CANSparkLowLevel.MotorType.kBrushless);

        this.leftEncoder = leftMotor.getEncoder();

        leftPIDController = this.leftMotor.getPIDController();
        rightPIDController = this.rightMotor.getPIDController();

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
    public void periodic() {
        int proximity = colorSensor.getProximity();

//        if(proximity > 800) {
//            turnShooterOn(0.35);
//        }
    }

    public void shootDefault(double speed){
        // leftMotor.set(speed);
        // rightMotor.set(-speed);

        leftPIDController.setReference(speed * maxRPM, CANSparkMax.ControlType.kVelocity);
        rightPIDController.setReference(-speed * maxRPM, CANSparkMax.ControlType.kVelocity);
    }

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

