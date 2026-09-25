package org.example.util;

import org.example.model.Location;

public class GeoUtils {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public static double distance(Location first, Location second) {

        double lat1 = Math.toRadians(first.getLatitude());
        double lat2 = Math.toRadians(second.getLatitude());

        double lon1 = Math.toRadians(first.getLongitude());
        double lon2 = Math.toRadians(second.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(lat1) * Math.cos(lat2)
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(
                Math.sqrt(a),
                Math.sqrt(1 - a)
        );

        return EARTH_RADIUS_KM * c;
    }
}