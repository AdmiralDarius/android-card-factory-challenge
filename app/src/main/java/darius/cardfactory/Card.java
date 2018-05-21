package darius.cardfactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Random;

public class Card {
    private int code;
    int roll; // card with highest roll will be shown
    private String title,description,tag,sound,image;
    Random rand = new Random();

    Card(int code,String title,String description,String tag){
        this.code=code;
        this.title=title;
        this.description=description;
        this.tag=tag;
        this.roll=rand.nextInt(100);
    }
    public void show_card(TextView titleText, TextView descriptionText, ImageView cardImage){
        titleText.setText(this.title);
        descriptionText.setText(this.description);
        if(this.code==0){
            try{
                URL url = new URL(this.image);
                Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(url.openStream()));
                cardImage.setImageBitmap(bmp);
            }catch (Exception e){

            }
        }
        this.roll-=100; // push the card to the end of random stack
    }
    public void set_sound(String sound){
        this.sound=sound;
    }
    public void set_image(String image){
        this.image=image;
    }
    public static Card choose_card(Card cards[]){ //returns card that must be shown
        Card chosen_card=cards[0];
        for(int i=1;i<cards.length;i++)
            if(chosen_card.roll<cards[i].roll)
                chosen_card=cards[i];
        return chosen_card;
    }

}
