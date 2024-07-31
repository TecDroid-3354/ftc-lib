package net.tecdroid.ftc.standards;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Contains constants which reflect the standard configuration for drivetrains
 */
public class DrivetrainStandards {

    public static final String FRONT_RIGHT_MOTOR_NAME = "frontRight";
    public static final String FRONT_LEFT_MOTOR_NAME  = "frontLeft";
    public static final String BACK_LEFT_MOTOR_NAME   = "backLeft";
    public static final String BACK_RIGHT_MOTOR_NAME  = "backRight";

    public static final DcMotorSimple.Direction FRONT_RIGHT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction FRONT_LEFT_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction BACK_LEFT_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction BACK_RIGHT_DIRECTION = DcMotorSimple.Direction.FORWARD;

    public static final DcMotor.RunMode FRONT_RIGHT_MODE = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final DcMotor.RunMode FRONT_LEFT_MODE = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final DcMotor.RunMode BACK_LEFT_MODE = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final DcMotor.RunMode BACK_RIGHT_MODE = DcMotor.RunMode.RUN_WITHOUT_ENCODER;

}
