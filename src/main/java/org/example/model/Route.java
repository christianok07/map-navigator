package org.example.model;

import java.util.List;

public class Route {

    private final List<Location> path;
    private final double totalDistance;
    private final int nodesExplored;

    public Route(List<Location> path, double totalDistance, int nodesExplored) {
        this.path = path;
        this.totalDistance = totalDistance;
        this.nodesExplored = nodesExplored;
    }

    public List<Location> getPath() {
        return path;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public int getNodesExplored() {
        return nodesExplored;
    }

    @Override
    public String toString() {
        return "Route{" +
                "path=" + path +
                ", totalDistance=" + totalDistance +
                ", nodesExplored=" + nodesExplored +
                '}';
    }
}