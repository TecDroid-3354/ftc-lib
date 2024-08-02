package net.tecdroid.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.LED;

import net.tecdroid.ftc.command.Command;
import net.tecdroid.ftc.command.CommandScheduler;
import net.tecdroid.ftc.command.TriggeredCommand;
import net.tecdroid.ftc.hardware.IMU;
import net.tecdroid.ftc.input.CommandGamepad;
import net.tecdroid.ftc.input.Gamepad;
import net.tecdroid.ftc.subsystems.LoggableEntityManager;
import net.tecdroid.ftc.subsystems.drivetrain.MecanumDrivetrain;
import net.tecdroid.ftc.util.MathUtil;

import org.joml.Vector2d;

@TeleOp(name = "Test")
public class TestOpMode extends OperationMode {

    private MecanumDrivetrain drivetrain = null;
    private IMU imu = null;
    private LED signal0Led = null;
    private LED signal1Led = null;

    @Override
    public void onInit() {
        drivetrain = new MecanumDrivetrain(hardwareMap);
        imu = new IMU(hardwareMap);

        signal0Led = hardwareMap.led.get("signal0");
        signal1Led = hardwareMap.led.get("signal1");

        TriggeredCommand cmd = controllerOne.topButton();

        cmd.setOnTrue(new Command(() -> {
            signal0Led.enable(true);
        }));

        cmd.setOnFalse(new Command(() -> {
            signal0Led.enable(false);
        }));

        triggeredCommandCycle.addTrigger(cmd);

        drivetrain.setLoggingEnabled(true);
        imu.setLoggingEnabled(true);
    }

    @Override
    public void onInitLoop() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onMainLoop() {
        LoggableEntityManager.getInstance().logSubsystems(telemetry);

        final Vector2d linearVelocity = MathUtil.swapVectorComponents(controllerOne.getLeftJoystickPosition());
        final double angularVelocity = controllerOne.getRightJoystickX();

        final double heading = imu.getZAxisAngle();
        final Vector2d rotatedVelocity = MathUtil.rotateVector(linearVelocity, heading);

        drivetrain.drive(rotatedVelocity, angularVelocity);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onGlobalStart() {

    }

    @Override
    public void onGlobalLoop() {

    }
}
