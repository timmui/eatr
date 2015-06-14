package nofluxgiven.science.eatr;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;


public class SecondActivity extends ActionBarActivity {
    private static Restaurant restaurantInfo;
    public void giveRestaurantInfo(Restaurant restaurants)
    {
        restaurantInfo = restaurants;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        View decorView = getWindow().getDecorView();

        //Setting the picture
        ImageView restaurantpicture = (ImageView) findViewById(R.id.like);
        Bitmap bmap = getImageBitmap(restaurantInfo.getImageUrl());
        restaurantpicture.setImageBitmap(bmap);

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(restaurantInfo.getAddress().replaceAll("([|])"," ") + "\n" + restaurantInfo.getPhone() + "\n" + "Distance" + restaurantInfo.getDistance());

        ImageView rating = (ImageView) findViewById(R.id.Rating);
        Bitmap bitmap2 = getImageBitmap(restaurantInfo.getRatingImageUrl());
        rating.setImageBitmap(bitmap2);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(restaurantInfo.getName());

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        ImageView exit = (ImageView) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("image clicked...");
                Intent intent = new Intent
                        (SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static Bitmap getImageBitmap (String link){
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
