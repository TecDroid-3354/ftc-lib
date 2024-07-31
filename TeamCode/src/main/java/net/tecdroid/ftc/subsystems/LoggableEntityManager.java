package net.tecdroid.ftc.subsystems;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggableEntityManager {
    @Getter @NonNull
    private final static LoggableEntityManager instance = new LoggableEntityManager();
    private final HashMap<String, LoggableEntity> entities = new HashMap<>();

    public void addEntity(LoggableEntity loggableEntity) {
        String name = loggableEntity.getName();
        LoggableEntity entity = entities.get(name);

        if (entity == null) {
            entities.put(name, loggableEntity);
            return;
        }

        // We want to compare refs
        if (entity == loggableEntity) {
            return;
        }

        entities.put(modifyDuplicateMarker(name), loggableEntity);

    }

    public void logSubsystems() {
        for (LoggableEntity loggableEntity : entities.values()) {
            loggableEntity.log();
        }
    }

    private String modifyDuplicateMarker(String value) {
        Pattern pattern = Pattern.compile("(.*) \\(([0-9]+)\\)$");
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            return matcher.group(0) + "(" + (Integer.parseInt(Objects.requireNonNull(matcher.group(1))) + 1) + ")";
        }

        return value + " (1)";
    }
}
