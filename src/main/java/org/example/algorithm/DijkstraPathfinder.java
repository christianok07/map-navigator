package org.example.algorithm;

import org.example.graph.Graph;
import org.example.model.Location;
import org.example.model.Route;
import org.example.model.Road;
import java.util.*;

public class DijkstraPathfinder {

    private static class Node {
        Location location;
        double distance;

        Node(Location location, double distance) {
            this.location = location;
            this.distance = distance;
        }
    }

    public Route findRoute(
            Graph graph,
            Location start,
            Location destination
    ) {

        if (!graph.getLocations().contains(start) ||
                !graph.getLocations().contains(destination)) {
            return null;
        }

        Map<Location, Double> distances = new HashMap<>();
        Map<Location, Location> previous = new HashMap<>();
        Set<Location> visited = new HashSet<>();

        for (Location location : graph.getLocations()) {
            distances.put(location, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);

        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingDouble(node -> node.distance)
        );

        queue.add(new Node(start, 0.0));

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            if (visited.contains(current.location)) {
                continue;
            }

            visited.add(current.location);

            if (current.location.equals(destination)) {
                break;
            }

            for (Road road : graph.getNeighbors(current.location)) {

                Location neighbor = road.getTo();

                double newDistance =
                        distances.get(current.location) + road.getDistance();

                if (newDistance < distances.get(neighbor)) {

                    distances.put(neighbor, newDistance);

                    previous.put(neighbor, current.location);

                    queue.add(new Node(neighbor, newDistance));
                }
            }
        }

        if (distances.get(destination) == Double.POSITIVE_INFINITY) {
            return null;
        }

        List<Location> path = new ArrayList<>();

        Location current = destination;

        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }

        Collections.reverse(path);

        return new Route(
                path,
                distances.get(destination),
                visited.size()
        );
    }

}