package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    @Test
    void shouldCreateRoute() {

        Location budapest =
                new Location("BUD", "Budapest", 47.4979, 19.0402);

        Location vienna =
                new Location("VIE", "Vienna", 48.2082, 16.3738);

        List<Location> path = List.of(budapest, vienna);

        Route route = new Route(path, 243.0, 2);

        assertEquals(path, route.getPath());
        assertEquals(243.0, route.getTotalDistance());
        assertEquals(2, route.getNodesExplored());
    }
}