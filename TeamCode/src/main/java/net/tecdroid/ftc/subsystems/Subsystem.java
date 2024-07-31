package net.tecdroid.ftc.subsystems;

import android.util.Pair;

import org.firstinspires.ftc.robotcore.external.Supplier;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

/**
 * Base class for all subsystems in the library
 */
public class Subsystem {
    private boolean loggingIsEnabled = false;

    private final String name;
    private final Telemetry telemetry;
    private final ArrayList<Pair<String, Supplier<Object>>> logItems;

    protected Subsystem(String subsystemName, Telemetry telemetry) {
        this.name = subsystemName;
        this.telemetry = telemetry;
        this.logItems = new ArrayList<>();

        SubsystemCollection.getInstance().addSubsystem(this);
    }

    public String getName() {
        return name;
    }

    /**
     * Adds an entity that can be logged whenever logging is enabled for this subsystem.
     * @param name The name with which this entry will be presented
     * @param supplier A supplier which should return the value to be tracked
     */
    public final void registerLoggableEntity(String name, Supplier<Object> supplier) {
        logItems.add(new Pair<>(name, supplier));
    }

    /**
     * Logs all registered entities
     */
    public final void log() {
        if (!this.loggingIsEnabled) {
            return;
        }

        if (logItems.isEmpty()) {
            telemetry.addData(name, "No entries.");
            return;
        }

        this.telemetry.addLine(String.format("%c %s", '\u250C', name));

        for (int i = 0; i < logItems.size(); ++i) {
            Pair<String, Supplier<Object>> item = logItems.get(i);
            char separator = '\u251C';

            if (i == logItems.size() - 1) {
                separator = '\u2514';
            }

            telemetry.addData(String.format("%s %s", separator, item.first), item.second.get());
        }
    }

    /**
     * Enable logging for this subsystem
     */
    public final void enableLogging() {
        this.loggingIsEnabled = true;
    }

    /**
     * Disable logging for this subsystem
     */
    public final void disableLogging() {
        this.loggingIsEnabled = false;
    }

}
