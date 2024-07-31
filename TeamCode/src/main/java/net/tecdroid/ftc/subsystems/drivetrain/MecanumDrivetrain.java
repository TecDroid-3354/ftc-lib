package net.tecdroid.ftc.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import net.tecdroid.ftc.standards.DrivetrainStandards;
import net.tecdroid.ftc.subsystems.LoggableEntity;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.joml.Vector2d;

/**
 * Represents a 4-wheel mecanum drive. Requires 4 motors to work.
 */
public class MecanumDrivetrain extends LoggableEntity implements Drivetrain {

    private final DcMotorEx frontRight;
    private final DcMotorEx frontLeft;
    private final DcMotorEx backLeft;
    private final DcMotorEx backRight;

    public MecanumDrivetrain(HardwareMap hardwareMap, Telemetry telemetry) {
        super("Mecanum Drivetrain", telemetry);
        this.frontRight = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.FRONT_RIGHT_MOTOR_NAME);
        this.frontLeft  = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.FRONT_LEFT_MOTOR_NAME);
        this.backLeft   = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.BACK_LEFT_MOTOR_NAME);
        this.backRight  = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.BACK_RIGHT_MOTOR_NAME);

        this.frontRight.setDirection(DrivetrainStandards.FRONT_RIGHT_DIRECTION);
        this.frontLeft.setDirection(DrivetrainStandards.FRONT_LEFT_DIRECTION);
        this.backLeft.setDirection(DrivetrainStandards.BACK_LEFT_DIRECTION);
        this.backRight.setDirection(DrivetrainStandards.BACK_RIGHT_DIRECTION);

        this.frontRight.setMode(DrivetrainStandards.FRONT_RIGHT_MODE);
        this.frontLeft.setMode(DrivetrainStandards.FRONT_LEFT_MODE);
        this.backLeft.setMode(DrivetrainStandards.BACK_LEFT_MODE);
        this.backRight.setMode(DrivetrainStandards.BACK_RIGHT_MODE);

        this.addEntry("FR Power", frontRight::getPower);
        this.addEntry("FL Power", frontLeft::getPower);
        this.addEntry("BL Power", backLeft::getPower);
        this.addEntry("BR Power", backRight::getPower);
    }

    @Override
    public void drive(Vector2d linearVelocity, double angularVelocity) {
        double x  = linearVelocity.x;
        double y  = linearVelocity.y;

        final double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(angularVelocity), 1.0);

        this.frontRight.setPower((x + y + angularVelocity) / denominator);
        this.frontLeft.setPower((x - y - angularVelocity) / denominator);
        this.backLeft.setPower((x + y - angularVelocity) / denominator);
        this.backRight.setPower((x - y + angularVelocity) / denominator);
    }
}
