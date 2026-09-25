package org.example.util;

import org.example.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoUtilsTest {

    @Test
    void shouldReturnZeroForSameLocation() {
        Location london = new Location(
                "LON",
                "London",
                51.5074,
                -0.1278
        );

        double distance = GeoUtils.distance(london, london);

        assertEquals(0.0, distance, 0.001);
    }

    @Test
    void shouldCalculateDistanceBetweenLocations() {
        Location london = new Location(
                "LON",
                "London",
                51.5074,
                -0.1278
        );

        Location paris = new Location(
                "PAR",
                "Paris",
                48.8566,
                2.3522
        );

        double distance = GeoUtils.distance(london, paris);

        assertEquals(343.6, distance, 1.0);
    }

    @Test
    void shouldReturnSameDistanceInBothDirections() {
        Location london = new Location(
                "LON",
                "London",
                51.5074,
                -0.1278
        );

        Location paris = new Location(
                "PAR",
                "Paris",
                48.8566,
                2.3522
        );

        double londonToParis = GeoUtils.distance(london, paris);
        double parisToLondon = GeoUtils.distance(paris, london);

        assertEquals(londonToParis, parisToLondon, 0.001);
    }
}