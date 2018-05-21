package darius.cardfactory;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Vibrator;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Random;

public class Card {
    private int code;
    private int roll; // card with highest roll will be shown
    private String title,description,tag,sound,image;
    Random rand = new Random();

    Card(int code,String title,String description,String tag){
        this.code=code;
        this.title=title;
        this.description=description;
        this.tag=tag;
        this.roll=rand.nextInt(100);
    }
    public void show_card(MainActivity mainActivity,TextView titleText, TextView descriptionText, ImageView cardImage){
        titleText.setText(this.title);
        descriptionText.setText(this.description);
        if(this.code==0){
            try{
                URL url = new URL(this.image);
                Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(url.openStream()));
                cardImage.setImageBitmap(bmp);
            }catch (Exception e){
                Log.d("tag",""+e);
            }
        }else
            cardImage.setImageDrawable(null);
        if(this.code==1)
            mainActivity.main_vibrate();
        if(this.code==2){
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(this.sound);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }catch (Exception e){

            }
        }
        change_theme(titleText,descriptionText,this.tag);
        this.roll-=100; // push the card to the end of random stack
    }

    private void change_theme(TextView titleText, TextView descriptionText,String tag) {
        switch (tag) {
            case "fun":
                titleText.setHighlightColor(Color.BLUE);
                titleText.setTextColor(Color.BLUE);
                descriptionText.setHighlightColor(Color.MAGENTA);
                descriptionText.setTextColor(Color.MAGENTA);
                break;
            case "sport":
                titleText.setTextColor(Color.RED);
                titleText.setTextColor(Color.RED);
                descriptionText.setTextColor(Color.GRAY);
                descriptionText.setTextColor(Color.GRAY);
                break;
            default:
                titleText.setTextColor(Color.GREEN);
                titleText.setTextColor(Color.GREEN);
                descriptionText.setTextColor(Color.DKGRAY);
                descriptionText.setTextColor(Color.DKGRAY);
                break;
        }

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
