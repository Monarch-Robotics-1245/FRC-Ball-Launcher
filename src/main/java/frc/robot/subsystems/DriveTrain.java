package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveCommand;

public class DriveTrain extends SubsystemBase{
    private final MonarchMecanumDrive mecanumDrive;
    private boolean isInverted = false;
    public DriveTrain() {

        CANSparkMax frontRightMotor = new CANSparkMax(1, CANSparkLowLevel.MotorType.kBrushless);
        frontRightMotor.setInverted(false);
        CANSparkMax backLeftMotor = new CANSparkMax(4, CANSparkLowLevel.MotorType.kBrushless);
        backLeftMotor.setInverted(true);
        CANSparkMax frontLeftMotor = new CANSparkMax(3, CANSparkLowLevel.MotorType.kBrushless);
        frontLeftMotor.setInverted(true);
        CANSparkMax backRightMotor = new CANSparkMax(2, CANSparkLowLevel.MotorType.kBrushless);
        backRightMotor.setInverted(false);


        //  RelativeEncoder frontLeftEncoder = frontLeftMotor.getEncoder();

        setDefaultCommand(new DriveCommand(this));

        this.mecanumDrive = new MonarchMecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    }

    public void drive(double y, double x, double rotation) {

        if(isInverted) {
            y = -y;
        }else{
            x = -x;
        }
        mecanumDrive.regularDrive(y*.8, x*.8, rotation*.7);
        mecanumDrive.feed();
    }

    public void invert() {
        isInverted = !isInverted;
    }
}

