package net.tecdroid.ftc.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import net.tecdroid.ftc.standards.HardwareStandards;
import net.tecdroid.ftc.subsystems.LoggableEntity;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Following: WitherLabs 3-Dimensional Translation Axis Convention
 * So, you might see yaw and pitch axes inverted according to the docs
 */
public class IMUWrapper extends LoggableEntity {
    private final IMU imu;

    public IMUWrapper(HardwareMap hardwareMap, Telemetry telemetry) {
        super("IMU", telemetry);
        this.imu = hardwareMap.get(IMU.class, HardwareStandards.IMU_NAME);

        this.addEntry("X", this::getXAxisAngle);
        this.addEntry("Y", this::getYAxisAngle);
        this.addEntry("Z", this::getZAxisAngle);
    }

    public double getZAxisAngle() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public double getXAxisAngle() {
        return imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.RADIANS);
    }

    public double getYAxisAngle() {
        return imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.RADIANS);
    }

    public void resetZAxisAngle() {
        imu.resetYaw();
    }

}
