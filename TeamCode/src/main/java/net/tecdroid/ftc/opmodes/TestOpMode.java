package net.tecdroid.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;

import net.tecdroid.ftc.standards.SensorStandards;
import net.tecdroid.ftc.subsystems.SubsystemCollection;
import net.tecdroid.ftc.subsystems.drivetrain.MecanumDrivetrain;
import net.tecdroid.ftc.util.MathUtil;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.joml.Vector2d;

public class TestOpMode extends OpMode {

    private MecanumDrivetrain drivetrain = null;
    private IMU imu = null;

    @Override
    public void init() {
        drivetrain = new MecanumDrivetrain(hardwareMap, telemetry);
        imu = hardwareMap.get(IMU.class, SensorStandards.IMU_NAME);
    }

    @Override
    public void loop() {
        SubsystemCollection.getInstance().logSubsystems();

        final Vector2d linearVelocity = new Vector2d(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x
        );

        final double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        final Vector2d rotatedVelocity = MathUtil.rotateVector(linearVelocity, -heading);

        final double angularVelocity = -gamepad1.right_stick_x;

        drivetrain.drive(rotatedVelocity, angularVelocity);
    }
}
