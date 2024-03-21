package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

import static java.util.Objects.requireNonNull;

public class MonarchMecanumDrive extends RobotDriveBase {

    private final CANSparkMax frontLeftMotor;
    private final CANSparkMax frontRightMotor;
    private final CANSparkMax backLeftMotor;
    private final CANSparkMax backRightMotor;
    private final SparkPIDController frontLeftSparkPID;
    private final SparkPIDController frontRightSparkPID;
    private final SparkPIDController backLeftSparkPID;
    private final SparkPIDController backRightSparkPID;

    ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;


    public MonarchMecanumDrive(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax backLeft, CANSparkMax backRight) {

        new TalonSRX(0);

        gyro.reset();

        requireNonNull(frontLeft, "Front-Left motor cannot be null");
        requireNonNull(frontRight, "Front-Right motor cannot be null");
        requireNonNull(backLeft, "Back-Left motor cannot be null");
        requireNonNull(backRight, "Back-Right motor cannot be null");
        this.frontLeftMotor = frontLeft;
        this.frontRightMotor = frontRight;
        this.backLeftMotor = backLeft;
        this.backRightMotor = backRight;

        kP = 0;
        kI = 0;
        kD = 0;
        kIz = 0;
        kFF = 0;
        kMaxOutput = 1;
        kMinOutput = -1;
        maxRPM = 5700;



        this.frontLeftSparkPID = frontLeftMotor.getPIDController();
        this.frontRightSparkPID = frontRightMotor.getPIDController();
        this.backLeftSparkPID = backLeftMotor.getPIDController();
        this.backRightSparkPID = backRightMotor.getPIDController();

        frontLeftSparkPID.setP(kP);
        frontRightSparkPID.setP(kP);
        backLeftSparkPID.setP(kP);
        backRightSparkPID.setP(kP);

        frontLeftSparkPID.setI(kI);
        frontRightSparkPID.setI(kI);
        backLeftSparkPID.setI(kI);
        backRightSparkPID.setI(kI);

        frontLeftSparkPID.setFF(kFF);
        frontRightSparkPID.setFF(kFF);
        backLeftSparkPID.setFF(kFF);
        backRightSparkPID.setFF(kFF);

        frontLeftSparkPID.setOutputRange(kMinOutput, kMaxOutput);
        frontRightSparkPID.setOutputRange(kMinOutput, kMaxOutput);
        backLeftSparkPID.setOutputRange(kMinOutput, kMaxOutput);
        backRightSparkPID.setOutputRange(kMinOutput, kMaxOutput);

        frontLeftMotor.setOpenLoopRampRate(0.5);
        frontRightMotor.setOpenLoopRampRate(0.5);
        backLeftMotor.setOpenLoopRampRate(0.5);
        backRightMotor.setOpenLoopRampRate(0.5);


    }

    //auto rotate field centric driving
    //robot centric driving
    //x is going forward/backward
    //y is going left/right
    public void regularDrive(double y, double x, double rotation) {

        double frontLeftSpeed = y + x - rotation;
        double backLeftSpeed = y - x + rotation;
        double frontRightSpeed = y - x - rotation;
        double backRightSpeed = y + x + rotation;

        double[] wheelSpeeds = {frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed};

        normalize(wheelSpeeds);

        frontLeftSparkPID.setReference(wheelSpeeds[0], CANSparkMax.ControlType.kDutyCycle);
        frontRightSparkPID.setReference(wheelSpeeds[1], CANSparkMax.ControlType.kDutyCycle);
        backLeftSparkPID.setReference(wheelSpeeds[2], CANSparkMax.ControlType.kDutyCycle);
        backRightSparkPID.setReference(wheelSpeeds[3], CANSparkMax.ControlType.kDutyCycle);

        feed();
    }

    @Override
    public void stopMotor() {
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
        backLeftMotor.stopMotor();
        backRightMotor.stopMotor();
        feed();
    }

    @Override
    public String getDescription() {
        return "Monarch Mecanum Drive";
    }

}
