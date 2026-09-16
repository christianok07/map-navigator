package org.example.graph;

import org.example.model.Location;
import org.example.model.Road;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void shouldAddLocation() {

        Graph graph = new Graph();

        Location budapest =
                new Location("BUD", "Budapest", 47.4979, 19.0402);

        graph.addLocation(budapest);

        assertTrue(graph.getLocations().contains(budapest));
    }

    @Test
    void shouldAddRoad() {

        Graph graph = new Graph();

        Location budapest =
                new Location("BUD", "Budapest", 47.4979, 19.0402);

        Location vienna =
                new Location("VIE", "Vienna", 48.2082, 16.3738);

        Road road = new Road(budapest, vienna, 243.0);

        graph.addRoad(road);

        assertTrue(graph.getNeighbors(budapest).contains(road));
    }

    @Test
    void shouldRemoveRoad() {

        Graph graph = new Graph();

        Location budapest =
                new Location("BUD", "Budapest", 47.4979, 19.0402);

        Location vienna =
                new Location("VIE", "Vienna", 48.2082, 16.3738);

        Road road = new Road(budapest, vienna, 243.0);

        graph.addRoad(road);
        graph.removeRoad(road);

        assertFalse(graph.getNeighbors(budapest).contains(road));
    }

    @Test
    void shouldRemoveLocationAndRelatedRoads() {

        Graph graph = new Graph();

        Location budapest =
                new Location("BUD", "Budapest", 47.4979, 19.0402);

        Location vienna =
                new Location("VIE", "Vienna", 48.2082, 16.3738);

        Road road = new Road(budapest, vienna, 243.0);

        graph.addRoad(road);

        graph.removeLocation(vienna);

        assertFalse(graph.getLocations().contains(vienna));
        assertFalse(graph.getNeighbors(budapest).contains(road));
    }

    @Test
    void shouldNotAddDuplicateLocation() {

        Graph graph = new Graph();

        Location budapest =
                new Location("BUD", "Budapest", 47.4979, 19.0402);

        graph.addLocation(budapest);
        graph.addLocation(budapest);

        assertEquals(1, graph.getLocations().size());
    }

    @Test
    void shouldReturnEmptyNeighborsForUnknownLocation() {

        Graph graph = new Graph();

        Location budapest =
                new Location("BUD", "Budapest", 47.4979, 19.0402);

        assertTrue(graph.getNeighbors(budapest).isEmpty());
    }
}