package net.tecdroid.ftc.subsystems;

import java.util.ArrayList;
import java.util.HashMap;

public class SubsystemCollection {
    private static SubsystemCollection INSTANCE = null;
    private final HashMap<String, Subsystem> subsystems;

    private SubsystemCollection() {
        this.subsystems = new HashMap<>();
    }

    public static SubsystemCollection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SubsystemCollection();
        }
        return INSTANCE;
    }

    public void addSubsystem(Subsystem subsystem) {
        if (subsystems.containsKey(subsystem.getName())) {
            return;
        }

        subsystems.put(subsystem.getName(), subsystem);
    }

    public void logSubsystems() {
        for (Subsystem subsystem : subsystems.values()) {
            subsystem.log();
        }
    }
}
