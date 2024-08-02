package net.tecdroid.ftc.command;

import java.util.LinkedList;
import java.util.List;

public class TriggeredCommandCycle {
    private final List<TriggeredCommand> triggeredCommands = new LinkedList<>();

    public void update(CommandScheduler scheduler) {
        for (TriggeredCommand trigger : triggeredCommands) {
            trigger.update(scheduler);
        }
    }

    public void addTrigger(TriggeredCommand triggeredCommand) {
        triggeredCommands.add(triggeredCommand);
    }

}
