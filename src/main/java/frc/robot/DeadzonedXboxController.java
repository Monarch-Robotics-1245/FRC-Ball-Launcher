package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class DeadzonedXboxController extends XboxController {

    private final double deadZoneRange;
    private final double slowModeMultiplier= 0.1f;

    public DeadzonedXboxController(int port, double deadZoneVal) {
        super(port);
        deadZoneRange = deadZoneVal;
    }

    @Override
    public double getLeftX() {
        double leftX = super.getLeftX();
        return deadZone(leftX);
    }

    @Override
    public double getRightX() {
        double rightX = super.getRightX();
        return deadZone(rightX);
    }

    @Override
    public double getLeftY() {
        double leftY = super.getLeftY();
        return deadZone(leftY);
    }

    @Override
    public double getRightY() {
        double rightY = super.getRightY();
        return deadZone(rightY);
    }

    public double deadZone(double val) {
        if(Math.abs(val) < deadZoneRange) {
            return 0;
        }
        return val;
    }
}
