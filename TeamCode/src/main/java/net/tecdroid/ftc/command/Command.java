package net.tecdroid.ftc.command;

import java.util.function.Supplier;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Command {
    private final Runnable onExecute;

    public void execute() {
        onExecute.run();
    }

    public static Command noop() {
        return new Command(() -> {});
    }

}
