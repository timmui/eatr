package nofluxgiven.science.eatr;

import android.location.Location;

import java.lang.reflect.Array;
import java.util.Dictionary;

/**
 * Created by Tim on 6/14/2015.
 */
public class Restaurant {
    private static String name = null;
    private static String imageUrl = null;
    private static String mobileUrl = null;
    private static double rating = 0;
    private static String phone = null;
    private static double distance = -1;
    private static String ratingImageUrl = null;
    private static String snippetText = null;
    private static String address = null;
    private static double latitude = 0;
    private static double longitude = 0;
    private static boolean isClosed = true;
    private static String id  = null;
    private static int rank = 0;

    public Restaurant (){

    }

    public static String getAddress() {
        return address;
    }

    public static int getRank() {
        return rank;
    }

    public static void setRank(int rank) {
        Restaurant.rank = rank;
    }

    public static void setAddress(String address) {
        Restaurant.address = address;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        Restaurant.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        Restaurant.longitude = longitude;
    }

    public static String getName() {
        return name;
    }

    public static double getRating() {
        return rating;
    }

    public static void setRating(double rating) {
        Restaurant.rating = rating;
    }

    public static void setName(String name) {
        Restaurant.name = name;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    public static void setImageUrl(String imageUrl) {
        Restaurant.imageUrl = imageUrl;
    }

    public static String getMobileUrl() {
        return mobileUrl;
    }

    public static void setMobileUrl(String mobileUrl) {
        Restaurant.mobileUrl = mobileUrl;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Restaurant.phone = phone;
    }

    public static double getDistance() {
        return distance;
    }

    public static void setDistance(double lat, double lon) {
        float[] result = new float[1];
        Location.distanceBetween(lat,lon,latitude,longitude,result);
        Restaurant.distance = (double) result[0];
    }

    public static String getRatingImageUrl() {
        return ratingImageUrl;
    }

    public static void setRatingImageUrl(String ratingImageUrl) {
        Restaurant.ratingImageUrl = ratingImageUrl;
    }

    public static String getSnippetText() {
        return snippetText;
    }

    public static void setSnippetText(String snippetText) {
        Restaurant.snippetText = snippetText;
    }

    public static boolean isClosed() {
        return isClosed;
    }

    public static void setIsClosed(boolean isClosed) {
        Restaurant.isClosed = isClosed;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Restaurant.id = id;
    }

}
