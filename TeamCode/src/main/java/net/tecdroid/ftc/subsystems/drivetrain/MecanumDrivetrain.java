package net.tecdroid.ftc.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import net.tecdroid.ftc.standards.DrivetrainStandards;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.joml.Vector2d;

public class MecanumDrivetrain implements Drivetrain {

    private final DcMotorEx frontRight;
    private final DcMotorEx frontLeft;
    private final DcMotorEx backLeft;
    private final DcMotorEx backRight;

    private final Telemetry telemetry;

    public MecanumDrivetrain(HardwareMap hardwareMap, Telemetry telemetry) {
        this.frontRight = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.FRONT_RIGHT_MOTOR_NAME);
        this.frontLeft  = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.FRONT_LEFT_MOTOR_NAME);
        this.backLeft   = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.BACK_LEFT_MOTOR_NAME);
        this.backRight  = hardwareMap.get(DcMotorEx.class, DrivetrainStandards.BACK_RIGHT_MOTOR_NAME);

        this.telemetry = telemetry;
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
