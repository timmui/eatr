package nofluxgiven.science.eatr;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class MainActivity extends Activity {

    /**
     * This variable is the container that will host our cards
     */
    private CardContainer mCardContainer;
    private static final String CONSUMER_KEY = Credentials.CONSUMER_KEY;
    private static final String CONSUMER_SECRET = Credentials.CONSUMER_SECRET;
    private static final String TOKEN = Credentials.TOKEN;
    private static final String TOKEN_SECRET = Credentials.TOKEN_SECRET;
    private static HashMap <String, Restaurant> restaurants = new HashMap();
    private static double latitude = 0;
    private static double longitude = 0;
    private static String postalCode = "M5V0G6";
    private static String[] restaurantIds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(lm.getBestProvider(new Criteria(), true));
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        Log.d("yelp", latitude+" "+longitude);

        try {
            Geocoder geocoder = new Geocoder(getApplicationContext());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            postalCode = addresses.get(0).getPostalCode();
        } catch (IOException e){
            e.printStackTrace();
        }

        restaurantIds =  makeRestaurants();
        Log.d("yelp", restaurantIds[0]);
        Log.d("yelp", restaurants.get(restaurantIds[0]).getId());
        Log.d("yelp", restaurants.get(restaurantIds[0]).getName());

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        makeCards(adapter, r);

        CardModel cardModel = new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1));

        cardModel.setOnClickListener(new CardModel.OnClickListener() {//pass in as an array
            @Override
            public void OnClickListener() {
                Log.i( "Swipeable Cards", "I am pressing the card");
            }
        });

        cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {//entry of MainActivity
            @Override
            public void onLike() {
                Log.i("Swipeable Cards", "I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards", "I dislike the card");
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);
    }

    public JSONObject queryYelp (){
        YelpAPI.YelpAPICLI yelpApiCli = new YelpAPI.YelpAPICLI();
        new JCommander(yelpApiCli);

        YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET, postalCode);
        JSONObject response = YelpAPI.queryAPI(yelpApi, yelpApiCli);
        Log.d("yelp", response.toString());
        return response;
    }

    public String[] makeRestaurants (){
        //Query Yelp
        JSONObject simpleResponse = queryYelp();
        try {
            org.json.JSONObject response = new org.json.JSONObject(simpleResponse.toJSONString());
            org.json.JSONArray JSONrestaurants = response.getJSONArray("businesses");

            Log.d ("yelp", "Making "+ JSONrestaurants.length() +" Stores");
            for (int idx = 0; idx < JSONrestaurants.length(); idx++){
                org.json.JSONObject JSONrestaurant = JSONrestaurants.getJSONObject(idx);

                // parse json to object
                String id = JSONrestaurant.getString("id");
                Double rating = JSONrestaurant.getDouble("rating");
                String imageUrl = JSONrestaurant.getString("image_url");
                Boolean isClosed = JSONrestaurant.getBoolean("is_closed");
                String mobileUrl = JSONrestaurant.getString("mobile_url");
                String name = JSONrestaurant.getString("name");
                String phone = JSONrestaurant.getString("phone");
                String ratingImageUrl = JSONrestaurant.getString("rating_img_url");
                String snippetText = JSONrestaurant.getString("snippet_text");
                String address = JSONrestaurant.getJSONObject("location").getJSONArray("display_address").toString().replaceAll("([|]|\")", "");
                double lat = JSONrestaurant.getJSONObject("location").getJSONObject("coordinate").getDouble("latitude");
                double lon = JSONrestaurant.getJSONObject("location").getJSONObject("coordinate").getDouble("longitude");

                Log.d("yelp", "\n----------------------------------------------\n"+id+"\n"+rating+"\n"+imageUrl+"\n"+isClosed+"\n"+mobileUrl+"\n"+name+"\n"+phone+"\n"+ratingImageUrl+"\n"+snippetText+"\n"+address+"\n"+lat+"\n"+lon+"\n----------------------------------------------\n");

                restaurants.put(id, new Restaurant(id, rating, imageUrl, isClosed, mobileUrl, name, phone, ratingImageUrl, snippetText, address, lat, lon));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        // Return IDs of restaurants
        String[] keys = new String[restaurants.size()];
        Log.d("yelp", restaurants.values().toString());
        restaurants.keySet().toArray(keys);
        return keys;
    }

    static void makeCards (SimpleCardStackAdapter adapter, Resources r){
        Set<String> keys = restaurants.keySet();
        Iterator<String> keyIter = keys.iterator();

        while (keyIter.hasNext()) {
            String id = keyIter.next();
            Restaurant rest = restaurants.get(id);
            Bitmap bmp = getImageBitmap(rest.getImageUrl());
            String name = rest.getName();
            Double rating = rest.getRating();
            adapter.add(new CardModel(name, rating + "", new BitmapDrawable(r, bmp)));
            Log.d("yelp", id + " " +name);
        }
    }

    static Bitmap getImageBitmap (String link){
        Bitmap bmp = null;
        try {
            URL url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
        return bmp;
    }
}
