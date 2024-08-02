package net.tecdroid.ftc.command;

import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class TriggeredCommand {
    private final Supplier<Boolean> predicate;
    @Setter private Command onTrue = Command.noop();
    @Setter private Command onFalse = Command.noop();
    @Setter private Command whileTrue = Command.noop();
    @Setter private Command whileFalse = Command.noop();

    private boolean status;

    public void update(CommandScheduler scheduler) {
        boolean state = predicate.get();

        if (state) {
            if (status) {
                scheduler.scheduleCommand(onTrue);
            } else {
                scheduler.scheduleCommand(whileTrue);
            }
        } else {
            if (!status) {
                scheduler.scheduleCommand(onFalse);
            } else {
                scheduler.scheduleCommand(whileFalse);
            }

        }

        status = state;
    }

}
