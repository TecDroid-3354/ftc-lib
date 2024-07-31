package net.tecdroid.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import net.tecdroid.ftc.hardware.IMUWrapper;
import net.tecdroid.ftc.input.GamepadWrapper;
import net.tecdroid.ftc.standards.HardwareStandards;
import net.tecdroid.ftc.subsystems.LoggableEntityManager;
import net.tecdroid.ftc.subsystems.drivetrain.MecanumDrivetrain;
import net.tecdroid.ftc.util.MathUtil;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.joml.Vector2d;

@TeleOp(name = "Test")
public class TestOpMode extends OpMode {

    private MecanumDrivetrain drivetrain = null;
    private GamepadWrapper gamepad = null;
    private IMUWrapper imu = null;

    @Override
    public void init() {
        drivetrain = new MecanumDrivetrain(hardwareMap, telemetry);
        gamepad = new GamepadWrapper(gamepad1, "Gamepad", telemetry);
        imu = new IMUWrapper(hardwareMap, telemetry);

        drivetrain.setLoggingEnabled(true);
        gamepad.setLoggingEnabled(true);
        imu.setLoggingEnabled(true);
    }

    @Override
    public void loop() {
        LoggableEntityManager.getInstance().logSubsystems();
        gamepad.update();

        final Vector2d linearVelocity = MathUtil.swapVectorComponents(gamepad.getLeftJoystickPosition());
        final double angularVelocity = gamepad.getRightJoystickX();

        final double heading = imu.getZAxisAngle();
        final Vector2d rotatedVelocity = MathUtil.rotateVector(linearVelocity, heading);

        drivetrain.drive(rotatedVelocity, angularVelocity);

        if (gamepad.getTopButtonPressed()) {
            imu.resetZAxisAngle();
        }
    }
}
