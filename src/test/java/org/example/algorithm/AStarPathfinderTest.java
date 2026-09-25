package org.example.algorithm;

import org.example.graph.Graph;
import org.example.model.Location;
import org.example.model.Road;
import org.example.model.Route;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AStarPathfinderTest {

    private final AStarPathfinder pathfinder = new AStarPathfinder();
    private final DijkstraPathfinder dijkstra = new DijkstraPathfinder();

    private Location location(String id, double latitude, double longitude) {
        return new Location(id, id, latitude, longitude);
    }

    @Test
    void shouldFindShortestPath() {
        Graph graph = new Graph();

        Location a = location("A", 0.0, 0.0);
        Location b = location("B", 0.0, 0.05);
        Location c = location("C", 0.02, 0.0);
        Location d = location("D", 0.02, 0.02);

        graph.addRoad(new Road(a, b, 5));
        graph.addRoad(new Road(a, c, 2));
        graph.addRoad(new Road(c, d, 2));
        graph.addRoad(new Road(b, d, 10));

        Route route = pathfinder.findRoute(graph, a, d);

        assertNotNull(route);
        assertEquals(List.of(a, c, d), route.getPath());
        assertEquals(4.0, route.getTotalDistance());
    }

    @Test
    void shouldFindDirectRoute() {
        Graph graph = new Graph();

        Location a = location("A", 51.5074, -0.1278);
        Location b = location("B", 52.5074, -0.1278);

        graph.addRoad(new Road(a, b, 10));

        Route route = pathfinder.findRoute(graph, a, b);

        assertNotNull(route);
        assertEquals(List.of(a, b), route.getPath());
        assertEquals(10.0, route.getTotalDistance());
    }

    @Test
    void shouldChooseShortestOfMultipleRoutes() {
        Graph graph = new Graph();

        Location a = location("A", 0.0, 0.0);
        Location b = location("B", 0.0, 0.01);
        Location c = location("C", 0.01, 0.0);
        Location d = location("D", 0.02, 0.01);

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

        Location a = location("A", 51.5074, -0.1278);
        Location b = location("B", 52.5074, -0.1278);
        Location c = location("C", 53.5074, -0.1278);

        graph.addRoad(new Road(a, b, 5));
        graph.addLocation(c);

        Route route = pathfinder.findRoute(graph, a, c);

        assertNull(route);
    }

    @Test
    void shouldReturnRouteWhenStartEqualsDestination() {
        Graph graph = new Graph();

        Location a = location("A", 51.5074, -0.1278);
        graph.addLocation(a);

        Route route = pathfinder.findRoute(graph, a, a);

        assertNotNull(route);
        assertEquals(List.of(a), route.getPath());
        assertEquals(0.0, route.getTotalDistance());
    }

    @Test
    void shouldHandleEmptyGraph() {
        Graph graph = new Graph();

        Location a = location("A", 51.5074, -0.1278);

        Route route = pathfinder.findRoute(graph, a, a);

        assertNull(route);
    }

    @Test
    void shouldHandleInvalidDestination() {
        Graph graph = new Graph();

        Location a = location("A", 51.5074, -0.1278);
        Location b = location("B", 52.5074, -0.1278);
        Location invalid = location("X", 53.5074, -0.1278);

        graph.addRoad(new Road(a, b, 5));

        Route route = pathfinder.findRoute(graph, a, invalid);

        assertNull(route);
    }

    @Test
    void shouldProduceSameResultAsDijkstra() {
        Graph graph = new Graph();

        Location a = location("A", 0.0, 0.0);
        Location b = location("B", 0.0, 0.01);
        Location c = location("C", 0.01, 0.0);
        Location d = location("D", 0.02, 0.01);

        graph.addRoad(new Road(a, b, 2));
        graph.addRoad(new Road(b, d, 10));

        graph.addRoad(new Road(a, c, 4));
        graph.addRoad(new Road(c, d, 3));

        graph.addRoad(new Road(a, d, 20));

        Route dijkstraRoute = dijkstra.findRoute(graph, a, d);
        Route aStarRoute = pathfinder.findRoute(graph, a, d);

        assertNotNull(dijkstraRoute);
        assertNotNull(aStarRoute);

        assertEquals(
                dijkstraRoute.getPath(),
                aStarRoute.getPath()
        );

        assertEquals(
                dijkstraRoute.getTotalDistance(),
                aStarRoute.getTotalDistance(),
                0.001
        );
    }
}