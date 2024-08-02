package net.tecdroid.ftc.input;

import net.tecdroid.ftc.command.TriggeredCommand;

import lombok.AllArgsConstructor;

public class CommandGamepad extends Gamepad {

    public CommandGamepad(com.qualcomm.robotcore.hardware.Gamepad gamepad) {
        super(gamepad);
    }

    public TriggeredCommand topButton() {
        return new TriggeredCommand(this::getTopButtonPressed);
    }

}
