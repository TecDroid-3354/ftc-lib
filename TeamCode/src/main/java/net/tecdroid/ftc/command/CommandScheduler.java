package net.tecdroid.ftc.command;

import java.util.LinkedList;
import java.util.Queue;

public class CommandScheduler {
    public final Queue<Command> commands = new LinkedList<>();

    public void runScheduledCommands() {
        while (!commands.isEmpty()) {
            Command command = commands.poll();
            if (command != null) {
                command.execute();
            }
        }
    }

    public void scheduleCommand(Command command) {
        commands.add(command);
    }

}
