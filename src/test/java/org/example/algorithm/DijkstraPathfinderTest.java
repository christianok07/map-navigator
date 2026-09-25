package org.example.algorithm;

import org.example.graph.Graph;
import org.example.model.Location;
import org.example.model.Road;
import org.example.model.Route;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraPathfinderTest {

    private final DijkstraPathfinder pathfinder = new DijkstraPathfinder();

    private Location location(String id) {
        return new Location(id, id, 0.0, 0.0);
    }

    @Test
    void shouldFindShortestPath() {
        Graph graph = new Graph();

        Location a = location("A");
        Location b = location("B");
        Location c = location("C");
        Location d = location("D");

        graph.addRoad(new Road(a, b, 5));
        graph.addRoad(new Road(a, c, 2));
        graph.addRoad(new Road(c, d, 2));
        graph.addRoad(new Road(b, d, 2));

        Route route = pathfinder.findRoute(graph, a, d);

        assertNotNull(route);
        assertEquals(List.of(a, c, d), route.getPath());
        assertEquals(4.0, route.getTotalDistance());
    }

    @Test
    void shouldFindDirectRoute() {
        Graph graph = new Graph();

        Location a = location("A");
        Location b = location("B");

        graph.addRoad(new Road(a, b, 10));

        Route route = pathfinder.findRoute(graph, a, b);

        assertNotNull(route);
        assertEquals(List.of(a, b), route.getPath());
        assertEquals(10.0, route.getTotalDistance());
    }

    @Test
    void shouldChooseShortestOfMultipleRoutes() {
        Graph graph = new Graph();

        Location a = location("A");
        Location b = location("B");
        Location c = location("C");
        Location d = location("D");

        graph.addRoad(new Road(a, b, 2));
        graph.addRoad(new Road(b, d, 10));

        graph.addRoad(new Road(a, c, 4));
        graph.addRoad(new Road(c, d, 3));

        graph.addRoad(new Road(a, d, 20));

        Route route = pathfinder.findRoute(graph, a, d);

        assertNotNull(route);
        assertEquals(List.of(a, c, d), route.getPath());
        assertEquals(7.0, route.getTotalDistance());
    }

    @Test
    void shouldReturnNullWhenDestinationIsUnreachable() {
        Graph graph = new Graph();

        Location a = location("A");
        Location b = location("B");
        Location c = location("C");

        graph.addRoad(new Road(a, b, 5));
        graph.addLocation(c);

        Route route = pathfinder.findRoute(graph, a, c);

        assertNull(route);
    }

    @Test
    void shouldReturnRouteWhenStartEqualsDestination() {
        Graph graph = new Graph();

        Location a = location("A");
        graph.addLocation(a);

        Route route = pathfinder.findRoute(graph, a, a);

        assertNotNull(route);
        assertEquals(List.of(a), route.getPath());
        assertEquals(0.0, route.getTotalDistance());
    }

    @Test
    void shouldHandleEmptyGraph() {
        Graph graph = new Graph();

        Location a = location("A");

        Route route = pathfinder.findRoute(graph, a, a);

        assertNull(route);
    }

    @Test
    void shouldHandleInvalidDestination() {
        Graph graph = new Graph();

        Location a = location("A");
        Location b = location("B");
        Location invalid = location("X");

        graph.addRoad(new Road(a, b, 5));

        Route route = pathfinder.findRoute(graph, a, invalid);

        assertNull(route);
    }
}