package org.example.graph;
import org.example.model.Location;
import org.example.model.Road;

import java.util.*;

public class Graph {

    private final Map<Location, List<Road>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addLocation(Location location) {
        adjacencyList.putIfAbsent(location, new ArrayList<>());
    }

    public void addRoad(Road road) {
        addLocation(road.getFrom());
        addLocation(road.getTo());

        adjacencyList.get(road.getFrom()).add(road);
    }

    public List<Road> getNeighbors(Location location) {
        return adjacencyList.getOrDefault(location, Collections.emptyList());
    }

    public Set<Location> getLocations() {
        return Collections.unmodifiableSet(adjacencyList.keySet());
    }

    public void removeRoad(Road road) {
        List<Road> roads = adjacencyList.get(road.getFrom());

        if (roads != null) {
            roads.remove(road);
        }
    }

    public void removeLocation(Location location) {
        adjacencyList.remove(location);

        for (List<Road> roads : adjacencyList.values()) {
            roads.removeIf(road -> road.getTo().equals(location));
        }
    }
}