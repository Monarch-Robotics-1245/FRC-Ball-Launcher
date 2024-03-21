package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveCommand;
import com.revrobotics.CANSparkLowLevel;
import frc.robot.subsystems.MonarchMecanumDrive;

public class DriveTrain extends SubsystemBase{
    private final MonarchMecanumDrive mecanumDrive;
    private boolean isInverted = false;
    public DriveTrain() {

//        frontLeftMotor = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
//        backLeftMotor = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
//        frontRightMotor = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
//        backRightMotor = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);

        CANSparkMax frontRightMotor = new CANSparkMax(1, CANSparkLowLevel.MotorType.kBrushless);
        frontRightMotor.setInverted(!true);
        CANSparkMax backLeftMotor = new CANSparkMax(4, CANSparkLowLevel.MotorType.kBrushless);
        backLeftMotor.setInverted(!false);
        CANSparkMax frontLeftMotor = new CANSparkMax(3, CANSparkLowLevel.MotorType.kBrushless);
        frontLeftMotor.setInverted(!false);
        CANSparkMax backRightMotor = new CANSparkMax(2, CANSparkLowLevel.MotorType.kBrushless);
        backRightMotor.setInverted(!true);


        //  RelativeEncoder frontLeftEncoder = frontLeftMotor.getEncoder();

        setDefaultCommand(new DriveCommand(this));

        //    private final CANEncoder frontLeftEncoder = frontLeftMotor.getEncoder();
        //    private  final CANEncoder backLeftEncoder = backLeftMotor.getEncoder();
        //    private  final CANEncoder frontRightEncoder = frontRightMotor.getEncoder();
        //    private final CANEncoder backRightEncoder = backRightMotor.getEncoder();


        this.mecanumDrive = new MonarchMecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    }

    public void drive(double y, double x, double rotation) {

        if(isInverted) {
            x = x;
            y = -y;
        }else{
            x = -x;
            y = y;
        }
        mecanumDrive.regularDrive(y*.8, x*.8, rotation*.7);
        mecanumDrive.feed();
    }

    public void invert() {
        isInverted = !isInverted;
    }

    public double intakeAdjust(double scalar){
        double value = 0;
        double value2 = 0;
        // while(visionPort.getBytesReceived() == 0);

        value2 = value*scalar;
        return value2;
    }
}

