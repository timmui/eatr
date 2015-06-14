package nofluxgiven.science.eatr;

import android.location.Location;

import java.lang.reflect.Array;
import java.util.Dictionary;

/**
 * Created by Tim on 6/14/2015.
 */
public class Restaurant {
    private   final double RATING_WEIGHT = 0.7;
    private   final double DIST_WEIGHT = 0.3;
    private   String name = null;
    private   String imageUrl = null;
    private   String mobileUrl = null;
    private   double rating = 0;
    private   String phone = null;
    private   double distance = -1;
    private   String ratingImageUrl = null;
    private   String snippetText = null;
    private   String address = null;
    private   double latitude = 0;
    private   double longitude = 0;
    private   boolean isClosed = true;
    private   String id  = null;
    private   double rank = 0;

    public Restaurant(String id, double rating, String imageUrl, Boolean isClosed, String mobileUrl, String name, String phone, String ratingImageUrl, String snippetText, String address, double lat, double lon) {
        setId(id);
        setRating(rating);
        setImageUrl(imageUrl);
        setIsClosed(isClosed);
        setMobileUrl(mobileUrl);
        setName(name);
        setPhone(phone);
        setRatingImageUrl(ratingImageUrl);
        setSnippetText(snippetText);
        setAddress(address);
        setLatitude(lat);
        setLongitude(lon);
        setDistance(latitude,longitude);
        setRank();
    }

    public   String getAddress() {
        return address;
    }

    public   double getRank() {
        return rank;
    }

    public   void setRank() {
        rank = rating*2*RATING_WEIGHT + distance*DIST_WEIGHT;
    }

    public   void setAddress(String address) {
        this.address = address;
    }

    public   double getLatitude() {
        return latitude;
    }

    public   void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public   double getLongitude() {
        return longitude;
    }

    public   void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public   String getName() {
        return name;
    }

    public   double getRating() {
        return rating;
    }

    public   void setRating(double rating) {
        this.rating = rating;
    }

    public   void setName(String name) {
        this.name = name;
    }

    public   String getImageUrl() {
        return imageUrl;
    }

    public   void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public   String getMobileUrl() {
        return mobileUrl;
    }

    public   void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public   String getPhone() {
        return phone;
    }

    public   void setPhone(String phone) {
        this.phone = phone;
    }

    public   double getDistance() {
        return distance;
    }

    public   void setDistance(double lat, double lon) {
        float[] result = new float[1];
        Location.distanceBetween(lat,lon,latitude,longitude,result);
        distance = (double) result[0];
    }

    public   String getRatingImageUrl() {
        return ratingImageUrl;
    }

    public   void setRatingImageUrl(String ratingImageUrl) {
        this.ratingImageUrl = ratingImageUrl;
    }

    public   String getSnippetText() {
        return snippetText;
    }

    public   void setSnippetText(String snippetText) {
        this.snippetText = snippetText;
    }

    public   boolean isClosed() {
        return isClosed;
    }

    public   void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public   String getId() {
        return id;
    }

    public   void setId(String id) {
        this.id = id;
    }

}
