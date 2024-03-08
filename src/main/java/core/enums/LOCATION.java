package core.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum LOCATION {
    GLOBAL_ENGLISH("Global (English)"),
    CZECH_REPUBLIC("Česká Republika (Čeština)"),
    CZECH_REPUBLIC_ENGLISH("Czech Republic (English)"),
    DACH("DACH (Deutsch)"),
    HUNGARY("Hungary (English)"),
    INDIA("India (English)"),
    JAPAN("日本 (日本語)"),
    POLAND("Polska (Polski)"),
    RUSSIA("Україна (Українська)"),
    CHINA("中国 (中文)");

    private String location;

    LOCATION(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public static List<String> getLocationList() {
        List<String> locations = EnumSet.allOf(LOCATION.class).stream()
                .map(LOCATION::getLocation)
                .collect(Collectors.toList());
        return locations;
    }
}
