package com.company.util;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

public class DistanceUtil {


    /**
     * cal distance between two given coordinate in meters
     */
    public static Double distanceBetweenLatLong(Double firstLat, Double secondLat, Double firstLong, Double secondLong){
        //EarthCalc gives distance in meters
        return EarthCalc.gcdDistance(getPointFromLatitudeAndLongitude(firstLat, firstLong), getPointFromLatitudeAndLongitude(secondLat, secondLong));
    }


    /**
     * creates a Point object from a given latitude and longitude
     * @param latitude
     * @param longitude
     * @return
     */
    private static Point getPointFromLatitudeAndLongitude(Double latitude, Double longitude) {
        Coordinate lat = Coordinate.fromDegrees(latitude);
        Coordinate lng = Coordinate.fromDegrees(longitude);
        return Point.at(lat, lng);
    }

}
