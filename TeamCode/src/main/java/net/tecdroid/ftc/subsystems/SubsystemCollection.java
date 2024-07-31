package net.tecdroid.ftc.subsystems;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubsystemCollection {
    @Getter @NonNull
    private final static SubsystemCollection instance = new SubsystemCollection();
    private final HashMap<String, Subsystem> subsystems = new HashMap<>();

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
