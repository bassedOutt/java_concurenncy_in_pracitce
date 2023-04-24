package org.example.publishing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations
                = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap
                = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id))
            throw new IllegalArgumentException(
                    "invalid vehicle name: " + id);
        locations.get(id).set(x, y);
    }

    public static void main(String[] args) {
        Map<String, SafePoint> map = new HashMap<>();
        map.put("vehicle1", new SafePoint(1, 2));
        PublishingVehicleTracker vehicleTracker = new PublishingVehicleTracker(map);
        vehicleTracker.setLocation("vehicle1", 2, 2);
        System.out.println(vehicleTracker.getLocation("vehicle1"));
        System.out.println(vehicleTracker.getLocations().get("vehicle1"));
    }
}