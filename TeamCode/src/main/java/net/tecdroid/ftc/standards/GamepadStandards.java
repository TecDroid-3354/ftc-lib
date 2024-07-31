package net.tecdroid.ftc.standards;

import lombok.Getter;

public class GamepadStandards {

    // Following: WitherLabs 3-Dimensional Translation Axis Convention
    // Such that:
    //
    //      +1           +x
    //   +1    -1     +y    -y
    //      -1           -x
    //

    public static final int RIGHT_STICK_X_FACTOR = -1;
    public static final int RIGHT_STICK_Y_FACTOR = -1;

    public static final int LEFT_STICK_X_FACTOR = -1;
    public static final int LEFT_STICK_Y_FACTOR = -1;


    @Getter
    public enum Button {
        TOP_BUTTON,
        LEFT_BUTTON,
        BOTTOM_BUTTON,
        RIGHT_BUTTON,
        CENTER_LEFT_BUTTON,
        CENTER_RIGHT_BUTTON,
        CENTER_BUTTON,
        DPAD_UP,
        DPAD_LEFT,
        DPAD_DOWN,
        DPAD_RIGHT,
        LEFT_JOYSTICK_BUTTON,
        RIGHT_JOYSTICK_BUTTON,
        LEFT_BUMPER,
        RIGHT_BUMPER,
    }
}
