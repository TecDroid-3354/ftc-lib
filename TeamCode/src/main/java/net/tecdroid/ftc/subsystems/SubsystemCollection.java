package net.tecdroid.ftc.subsystems;

import java.util.ArrayList;
import java.util.HashMap;

public class SubsystemCollection {
    private static SubsystemCollection INSTANCE = null;
    private static HashMap<String, Subsystem> subsystems;

    public static SubsystemCollection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SubsystemCollection();
        }
        return INSTANCE;
    }

    public static void addSubsystem(Subsystem subsystem) {
        if (subsystems.containsKey(subsystem.getName())) {
            return;
        }

        subsystems.put(subsystem.getName(), subsystem);
    }

    public static void logSubsystems() {
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.log();
        }
    }
}
