package net.tecdroid.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.LED;

import net.tecdroid.ftc.hardware.IMU;
import net.tecdroid.ftc.input.Gamepad;
import net.tecdroid.ftc.subsystems.LoggableEntityManager;
import net.tecdroid.ftc.subsystems.drivetrain.MecanumDrivetrain;
import net.tecdroid.ftc.util.MathUtil;

import org.joml.Vector2d;

@TeleOp(name = "Test")
public class TestOpMode extends OpMode {

    private MecanumDrivetrain drivetrain = null;
    private Gamepad gamepad = null;
    private IMU imu = null;
    private LED signal0Led = null;
    private LED signal1Led = null;

    @Override
    public void init() {
        drivetrain = new MecanumDrivetrain(hardwareMap);
        imu = new IMU(hardwareMap);
        gamepad = new Gamepad(gamepad1);

        signal0Led = hardwareMap.led.get("signal0");
        signal1Led = hardwareMap.led.get("signal1");

        drivetrain.setLoggingEnabled(true);
        imu.setLoggingEnabled(true);
    }

    @Override
    public void loop() {
        LoggableEntityManager.getInstance().logSubsystems(telemetry);
        gamepad.update();

        final Vector2d linearVelocity = MathUtil.swapVectorComponents(gamepad.getLeftJoystickPosition());
        final double angularVelocity = gamepad.getRightJoystickX();

        final double heading = imu.getZAxisAngle();
        final Vector2d rotatedVelocity = MathUtil.rotateVector(linearVelocity, heading);

        drivetrain.drive(rotatedVelocity, angularVelocity);

        if (gamepad.getTopButtonTapped()) {
            signal0Led.enable(true);
        } else
        if (gamepad.getTopButtonHeld()) {
            signal1Led.enable(true);
        } else if (gamepad.getTopButtonReleased()) {
            signal0Led.enable(false);
            signal1Led.enable(false);
        }


    }
}
