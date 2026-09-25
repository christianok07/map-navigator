package org.example.algorithm;

import org.example.graph.Graph;
import org.example.model.Location;
import org.example.model.Route;
import org.example.model.Road;
import org.example.util.GeoUtils;

import java.util.*;

public class AStarPathfinder {

    private static class Node {
        Location location;
        double gScore;
        double fScore;

        Node(Location location, double gScore, double fScore) {
            this.location = location;
            this.gScore = gScore;
            this.fScore = fScore;
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

        Map<Location, Double> gScores = new HashMap<>();
        Map<Location, Location> previous = new HashMap<>();

        for (Location location : graph.getLocations()) {
            gScores.put(location, Double.POSITIVE_INFINITY);
        }

        gScores.put(start, 0.0);

        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingDouble(node -> node.fScore)
        );

        double startHeuristic = GeoUtils.distance(start, destination);

        queue.add(
                new Node(
                        start,
                        0.0,
                        startHeuristic
                )
        );

        Set<Location> visited = new HashSet<>();

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

                double newGScore =
                        gScores.get(current.location) + road.getDistance();

                if (newGScore < gScores.get(neighbor)) {

                    gScores.put(neighbor, newGScore);

                    previous.put(neighbor, current.location);

                    double hScore = GeoUtils.distance(
                            neighbor,
                            destination
                    );

                    double fScore = newGScore + hScore;

                    queue.add(
                            new Node(
                                    neighbor,
                                    newGScore,
                                    fScore
                            )
                    );
                }
            }
        }

        if (gScores.get(destination) == Double.POSITIVE_INFINITY) {
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
                gScores.get(destination),
                visited.size()
        );
    }
}