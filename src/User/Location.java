package User;

import java.util.List;
import java.util.Map;

/**
 *
 * @author
 */
public class Location {
    // somewhere near the top of your App.java

    public static final Map<String, List<String>> REGION_MAP = Map.of(
            "East", List.of("Howick", "Botany", "Pakuranga"),
            "West", List.of("Henderson", "New Lynn", "Glen Eden"),
            "North", List.of("Albany", "Takapuna", "Devonport"),
            "South", List.of("Manukau", "Papakura", "Otahuhu")
    );

// Rough distances in km from Auckland CBD
    public static final Map<String, Integer> DISTANCE_KM = Map.ofEntries(
            Map.entry("Howick", 20),
            Map.entry("Botany", 22),
            Map.entry("Pakuranga", 18),
            Map.entry("Henderson", 15),
            Map.entry("New Lynn", 14),
            Map.entry("Glen Eden", 16),
            Map.entry("Albany", 25),
            Map.entry("Takapuna", 10),
            Map.entry("Devonport", 12),
            Map.entry("Manukau", 25),
            Map.entry("Papakura", 32),
            Map.entry("Otahuhu", 12)
    );
    
    public static final double BASE_FARE   = 2.0;
    public static final double RATE_PER_KM = 0.75;
    public static final double MAX_FARE    = 40.0;
    
    
    // gets all the regions
    public static String[] regions() {
        return REGION_MAP.keySet().toArray(new String[0]);
    }

    // gets all the locations for a region
    public static String[] locationsFor(String region) {
        return REGION_MAP
                .getOrDefault(region, List.of())
                .toArray(new String[0]);
    }
}
