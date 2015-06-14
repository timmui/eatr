package nofluxgiven.science.eatr;

import android.app.Activity;
import android.content.res.Resources;
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

import java.util.HashMap;


public class MainActivity extends Activity {

    /**
     * This variable is the container that will host our cards
     */
    private CardContainer mCardContainer;
    private static final String CONSUMER_KEY = Credentials.CONSUMER_KEY;
    private static final String CONSUMER_SECRET = Credentials.CONSUMER_SECRET;
    private static final String TOKEN = Credentials.TOKEN;
    private static final String TOKEN_SECRET = Credentials.TOKEN_SECRET;
    private static HashMap <String, Restaurant> restaurants = new HashMap <String, Restaurant>();
    private static double latitude = 0;
    private static double longitude = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title6", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title5", "Description goes here", r.getDrawable(R.drawable.picture2)));

        CardModel cardModel = new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1));
        cardModel.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
            }
        });

        cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
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
        String [] ids =  makeRestaurants();
        Log.d("yelp", ids[0]);
        Log.d("yelp", restaurants.get(ids[0]).getId());
        Log.d("yelp", restaurants.get(ids[0]).getName());
        Log.d("yelp", restaurants.get(ids[0]).getDistance()+"");
    }

    public JSONObject queryYelp (){
        YelpAPI.YelpAPICLI yelpApiCli = new YelpAPI.YelpAPICLI();
        new JCommander(yelpApiCli);

        YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
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
                Restaurant restaurant = new Restaurant();
                // parse json to object
                restaurant.setId(JSONrestaurant.getString("id"));
                restaurant.setRating(JSONrestaurant.getDouble("rating"));
                restaurant.setImageUrl(JSONrestaurant.getString("image_url"));
                restaurant.setIsClosed(JSONrestaurant.getBoolean("is_closed"));
                restaurant.setMobileUrl(JSONrestaurant.getString("mobile_url"));
                restaurant.setName(JSONrestaurant.getString("name"));
                restaurant.setPhone(JSONrestaurant.getString("phone"));
                restaurant.setRatingImageUrl(JSONrestaurant.getString("rating_img_url"));
                restaurant.setSnippetText(JSONrestaurant.getString("snippet_text"));
                restaurant.setAddress(JSONrestaurant.getJSONObject("location").getJSONArray("display_address").toString());
                restaurant.setLatitude(JSONrestaurant.getJSONObject("location").getJSONObject("coordinate").getDouble("latitude"));
                restaurant.setLongitude(JSONrestaurant.getJSONObject("location").getJSONObject("coordinate").getDouble("longitude"));
                restaurant.setDistance(latitude,longitude);

                restaurants.put(restaurant.getId(), restaurant);
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
}
