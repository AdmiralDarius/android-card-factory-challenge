package darius.cardfactory;

import android.content.Context;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    Card cards[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initialize_cards();
        show_another_card(null);
    }

    private void initialize_cards() {
        String mainurl = "http://static.pushe.co/challenge/json";
        String response="";
        try {
            URL url = new URL(mainurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (Exception ignored) {

        }
        try {
            JSONObject reader = new JSONObject(response);
            JSONArray cards = reader.getJSONArray("cards");
            this.cards = new Card[cards.length()];
            Log.d("tag", cards.length() + "");
            for (int i = 0; i < cards.length(); i++) {
                JSONObject card = cards.getJSONObject(i);
                int code = card.getInt("code");
                String title = card.getString("title");
                String description = card.getString("description");
                String tag = card.getString("tag");
                this.cards[i] = new Card(code, title, description, tag);
                if (code == 0)
                    this.cards[i].set_image(card.getString("image"));
                if (code == 2)
                    this.cards[i].set_sound(card.getString("sound"));
            }
        } catch (JSONException ignored) {

        }
    }

    public void show_another_card(View view){ // function for "try again " button
        TextView titleText=(TextView) findViewById(R.id.title);
        TextView descriptionText =(TextView) findViewById(R.id.description);
        ImageView cardImage =(ImageView) findViewById(R.id.card_image);
        Card.choose_card(cards).show_card(this,titleText,descriptionText,cardImage);
    }

    public void main_vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Log.d("tag","has vibrator ?"+v.hasVibrator());
        v.vibrate(2000);
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
