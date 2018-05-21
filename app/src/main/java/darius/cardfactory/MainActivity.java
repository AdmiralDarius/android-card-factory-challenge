package darius.cardfactory;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Card cards[]=new Card[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize_cards();
    }

    private void initialize_cards() {
        //TODO read from http://static.pushe.co/challenge/json
        cards[0]=new Card(1,"Exercise","Exercise on a regular basis.","sport");

        cards[1]=new Card(0,"Painting","Look at this beautiful painting","art");
        cards[1].set_image("http://static.pushe.co/challenge/sky.jpg");

        cards[2]=new Card(2,"Let's have fun","Listen to the music","fun");
        cards[2].set_sound("http://static.pushe.co/challenge/sound.mp3");

        cards[3]=new Card(1,"Hey!","Have you called your parents lately!","fun");

        cards[4]=new Card(0,"Sports","Have you ever played one of theses sports?.","sport");
        cards[4].set_image("http://static.pushe.co/challenge/sport.jpg");
    }

    public void show_another_card(View view){ // function for "try again " button
        TextView titleText=(TextView) findViewById(R.id.title);
        TextView descriptionText =(TextView) findViewById(R.id.description);
        ImageView cardImage =(ImageView) findViewById(R.id.card_image);
        Card.choose_card(cards).show_card(titleText,descriptionText,cardImage);
    }
}
