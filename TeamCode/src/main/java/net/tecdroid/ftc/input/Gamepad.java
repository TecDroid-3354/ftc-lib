package net.tecdroid.ftc.input;

import net.tecdroid.ftc.standards.GamepadStandards;

import static net.tecdroid.ftc.util.MathUtil.isInsideRange;

import org.joml.Vector2d;

import lombok.Getter;
import lombok.Setter;

public final class Gamepad {
    @Getter @Setter private int framesToConsiderHeld = 6;
    @Getter @Setter private int framesToReleaseTapped = 1;

    private final int[] framesPressed = new int[GamepadStandards.Button.values().length];
    private final int[] framesTapped = new int[framesPressed.length];

    private final com.qualcomm.robotcore.hardware.Gamepad gamepad;

    public Gamepad(com.qualcomm.robotcore.hardware.Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public void update() {
        for (GamepadStandards.Button button : GamepadStandards.Button.values()) {
            boolean pressed = getButtonPressed(button);
            int b = button.ordinal();

            if (pressed) {
                framesPressed[b] = Math.min(framesPressed[b] + 1, framesToConsiderHeld);
                continue;
            }

            // !pressed

            if (isInsideRange(framesPressed[b], 0, framesToConsiderHeld)) {
                // +1 because we have to compensate for this frame
                framesTapped[b] = framesToReleaseTapped + 1;
            }

            framesTapped[b] = Math.max(framesTapped[b] - 1, 0);
            framesPressed[b] = 0;
        }
    }

    // Generic Buttons

    boolean getButtonPressed(GamepadStandards.Button button) {
        switch (button) {
            case TOP_BUTTON:
                return gamepad.y;
            case LEFT_BUTTON:
                return gamepad.x;
            case BOTTOM_BUTTON:
                return gamepad.a;
            case RIGHT_BUTTON:
                return gamepad.b;
            case CENTER_LEFT_BUTTON:
                return gamepad.back;
            case CENTER_BUTTON:
                return gamepad.guide;
            case CENTER_RIGHT_BUTTON:
                return gamepad.start;
            case DPAD_UP:
                return gamepad.dpad_up;
            case DPAD_LEFT:
                return gamepad.dpad_left;
            case DPAD_DOWN:
                return gamepad.dpad_down;
            case DPAD_RIGHT:
                return gamepad.dpad_right;
            case LEFT_JOYSTICK_BUTTON:
                return gamepad.left_stick_button;
            case RIGHT_JOYSTICK_BUTTON:
                return gamepad.right_stick_button;
            case LEFT_BUMPER:
                return gamepad.left_bumper;
            case RIGHT_BUMPER:
                return gamepad.right_bumper;
        }

        return false;
    }

    // Every time I touch Java code, I wish I had C macros

    public boolean getButtonReleased(GamepadStandards.Button button) {
        return !getButtonPressed(button);
    }

    public boolean getButtonHeld(GamepadStandards.Button button) {
        // Ideally, we only need ==, but we'll use >= for safety's sake
        return framesPressed[button.ordinal()] >= framesToConsiderHeld;
    }

    public boolean getButtonTapped(GamepadStandards.Button button) {
        return framesTapped[button.ordinal()] > 0;
    }

    // D-Pad

    public boolean getDPadUpPressed(){
        return getButtonPressed(GamepadStandards.Button.DPAD_UP);
    }

    public boolean getDPadUpReleased(){
        return getButtonReleased(GamepadStandards.Button.DPAD_UP);
    }

    public boolean getDPadUpHeld(){
        return getButtonHeld(GamepadStandards.Button.DPAD_UP);
    }

    public boolean getDPadUpTapped(){
        return getButtonTapped(GamepadStandards.Button.DPAD_UP);
    }

    public boolean getDPadLeftPressed(){
        return getButtonPressed(GamepadStandards.Button.DPAD_LEFT);
    }

    public boolean getDPadLeftReleased(){
        return getButtonReleased(GamepadStandards.Button.DPAD_LEFT);
    }

    public boolean getDPadLeftHeld(){
        return getButtonHeld(GamepadStandards.Button.DPAD_LEFT);
    }

    public boolean getDPadLeftTapped(){
        return getButtonTapped(GamepadStandards.Button.DPAD_LEFT);
    }

    public boolean getDPadDownPressed(){
        return getButtonPressed(GamepadStandards.Button.DPAD_DOWN);
    }

    public boolean getDPadDownReleased(){
        return getButtonReleased(GamepadStandards.Button.DPAD_DOWN);
    }

    public boolean getDPadDownHeld(){
        return getButtonHeld(GamepadStandards.Button.DPAD_DOWN);
    }

    public boolean getDPadDownTapped(){
        return getButtonTapped(GamepadStandards.Button.DPAD_DOWN);
    }

    public boolean getDPadRightPressed(){
        return getButtonPressed(GamepadStandards.Button.DPAD_RIGHT);
    }

    public boolean getDPadRightReleased(){
        return getButtonReleased(GamepadStandards.Button.DPAD_RIGHT);
    }

    public boolean getDPadRightHeld(){
        return getButtonHeld(GamepadStandards.Button.DPAD_RIGHT);
    }

    public boolean getDPadRightTapped(){
        return getButtonTapped(GamepadStandards.Button.DPAD_RIGHT);
    }

    // Rhombus Buttons

    public boolean getTopButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.TOP_BUTTON);
    }

    public boolean getTopButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.TOP_BUTTON);
    }

    public boolean getTopButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.TOP_BUTTON);
    }

    public boolean getTopButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.TOP_BUTTON);
    }

    public boolean getLeftButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.LEFT_BUTTON);
    }

    public boolean getLeftButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.LEFT_BUTTON);
    }

    public boolean getLeftButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.LEFT_BUTTON);
    }

    public boolean getLeftButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.LEFT_BUTTON);
    }

    public boolean getRightButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.RIGHT_BUTTON);
    }

    public boolean getRightButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.RIGHT_BUTTON);
    }

    public boolean getRightButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.RIGHT_BUTTON);
    }

    public boolean getRightButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.RIGHT_BUTTON);
    }

    public boolean getBottomButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.BOTTOM_BUTTON);
    }

    public boolean getBottomButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.BOTTOM_BUTTON);
    }

    public boolean getBottomButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.BOTTOM_BUTTON);
    }

    public boolean getBottomButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.BOTTOM_BUTTON);
    }

    // Joystick Buttons

    public boolean getLeftJoystickButtonButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.LEFT_JOYSTICK_BUTTON);
    }

    public boolean getLeftJoystickButtonButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.LEFT_JOYSTICK_BUTTON);
    }

    public boolean getLeftJoystickButtonButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.LEFT_JOYSTICK_BUTTON);
    }

    public boolean getLeftJoystickButtonButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.LEFT_JOYSTICK_BUTTON);
    }

    public boolean getRightJoystickButtonButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.RIGHT_JOYSTICK_BUTTON);
    }

    public boolean getRightJoystickButtonButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.RIGHT_JOYSTICK_BUTTON);
    }

    public boolean getRightJoystickButtonButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.RIGHT_JOYSTICK_BUTTON);
    }

    public boolean getRightJoystickButtonButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.RIGHT_JOYSTICK_BUTTON);
    }

    // Bumpers

    public boolean getLeftBumperButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.LEFT_BUMPER);
    }

    public boolean getLeftBumperButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.LEFT_BUMPER);
    }

    public boolean getLeftBumperButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.LEFT_BUMPER);
    }

    public boolean getLeftBumperButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.LEFT_BUMPER);
    }

    public boolean getRightBumperButtonPressed(){
        return getButtonPressed(GamepadStandards.Button.RIGHT_BUMPER);
    }

    public boolean getRightBumperButtonReleased(){
        return getButtonReleased(GamepadStandards.Button.RIGHT_BUMPER);
    }

    public boolean getRightBumperButtonHeld(){
        return getButtonHeld(GamepadStandards.Button.RIGHT_BUMPER);
    }

    public boolean getRightBumperButtonTapped(){
        return getButtonTapped(GamepadStandards.Button.RIGHT_BUMPER);
    }

    // Joysticks

    public double getLeftJoystickX() {
        return gamepad.left_stick_x * GamepadStandards.LEFT_STICK_X_FACTOR;
    }

    public double getLeftJoystickY() {
        return gamepad.left_stick_y * GamepadStandards.LEFT_STICK_Y_FACTOR;
    }

    public double getRightJoystickX() {
        return gamepad.right_stick_x * GamepadStandards.RIGHT_STICK_X_FACTOR;
    }

    public double getRightJoystickY() {
        return gamepad.right_stick_y * GamepadStandards.RIGHT_STICK_Y_FACTOR;
    }

    public Vector2d getLeftJoystickPosition() {
        return new Vector2d(
                getLeftJoystickX(),
                getLeftJoystickY()
        );
    }

    public Vector2d getRightJoystickPosition() {
        return new Vector2d(
                getRightJoystickX(),
                getRightJoystickY()
        );
    }

    // Triggers

    public double getLeftTriggerDepth() {
        return gamepad.left_trigger;
    }

    public double getRightTriggerDepth() {
        return gamepad.right_trigger;
    }

}
