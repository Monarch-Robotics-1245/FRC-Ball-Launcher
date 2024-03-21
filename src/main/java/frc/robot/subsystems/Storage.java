package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.StorageCommand;


public class Storage extends SubsystemBase{

    private final TalonSRX motor;
    private boolean isOn = false;


    public Storage() {

        this.motor = new TalonSRX(6);
        setDefaultCommand(new StorageCommand(this));
    }

    public void toggleSpin() {
        if(!isOn) {
            motor.set(TalonSRXControlMode.PercentOutput, -0.6);
            isOn = true;
        }else{
            motor.set(TalonSRXControlMode.PercentOutput, 0);
            isOn = false;
        }
    }
    public boolean isRunning(){
        return isOn;
    }
}