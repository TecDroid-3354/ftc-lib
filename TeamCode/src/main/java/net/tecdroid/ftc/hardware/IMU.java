package net.tecdroid.ftc.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import net.tecdroid.ftc.standards.HardwareStandards;
import net.tecdroid.ftc.subsystems.LoggableEntity;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Following: WitherLabs 3-Dimensional Translation Axis Convention
 * So, you might see yaw and pitch axes inverted according to the docs
 */
public class IMU extends LoggableEntity {
    private final com.qualcomm.robotcore.hardware.IMU imu;

    public IMU(HardwareMap hardwareMap) {
        super("IMU");
        this.imu = hardwareMap.get(com.qualcomm.robotcore.hardware.IMU.class, HardwareStandards.IMU_NAME);

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
