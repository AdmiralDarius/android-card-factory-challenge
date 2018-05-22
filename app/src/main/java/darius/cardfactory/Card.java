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

/**
 * @author      Aliakbar Nafar DariusSafavy@gmail.com
 */

public class Card {
    /**
     * card with code :0 has image, 1 vibarates , 2 has sound
     */
    private int code;
    /**
     * card with highest roll will be shown.rolss are sat randomly between 0 and 99 and
     * each time a card is shown , its roll is reduced by 100
     */
    private int roll;
    private String title,description,tag,sound,image;
    /**
     * to fill the rolls
     */
    Random rand = new Random();

    /**
     * initiallzer give a random number to roll between 0 and 99
     */

    Card(int code,String title,String description,String tag){
        this.code=code;
        this.title=title;
        this.description=description;
        this.tag=tag;
        this.roll=rand.nextInt(100);
    }

    /**
     * shows a card and it's components ( image , sound ... )
     * @param mainActivity is required for vibration for code 1 cards
     * @param titleText textView in UI to show title of card
     * @param descriptionText textView in UI to show description of card
     * @param cardImage textView in UI to show image of code 0 cards
     */

    public void show_card(MainActivity mainActivity,TextView titleText, TextView descriptionText, ImageView cardImage){
        titleText.setText(this.title);
        descriptionText.setText(this.description);
        if(this.code==0)
            showImage(this.image,cardImage);
        else
            cardImage.setImageDrawable(null);
        if(this.code==1)
            mainActivity.main_vibrate();
        if(this.code==2)
            playSound(this.sound);
        change_theme(titleText,descriptionText,this.tag);
        this.roll-=100; // push the card to the end of random stack
    }

    /**
     * downloads and shows the image of the card
     */

    private void showImage(String image,ImageView cardImage) {
        try{
            URL url = new URL(image);
            Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(url.openStream()));
            cardImage.setImageBitmap(bmp);
        }catch (Exception e){
            Log.d("tag",""+e);
        }
    }

    /**
     * downloads and plays sound of the card
     */

    private void playSound(String sound) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(sound);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception ignored){

        }
    }

    /**
     * changes text color for each tag theme
     *
     */


    private void change_theme(TextView titleText, TextView descriptionText,String tag) {
        switch (tag) {
            case "fun": // tag fun
                titleText.setHighlightColor(Color.BLUE);
                titleText.setTextColor(Color.BLUE);
                descriptionText.setHighlightColor(Color.MAGENTA);
                descriptionText.setTextColor(Color.MAGENTA);
                break;
            case "sport": // tag sport
                titleText.setTextColor(Color.RED);
                titleText.setTextColor(Color.RED);
                descriptionText.setTextColor(Color.GRAY);
                descriptionText.setTextColor(Color.GRAY);
                break;
            default:  // tag art
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

    /**
     * returns card that must be shown ( with highest roll )
     * @param cards the cards that are read from jsons
     */
    public static Card choose_card(Card cards[]){ //
        Card chosen_card=cards[0];
        for(int i=1;i<cards.length;i++)
            if(chosen_card.roll<cards[i].roll)
                chosen_card=cards[i];
        return chosen_card;
    }

    /**
     * a funtion to change random roll of the cards for testing
     * @param roll desired roll to be set
     */
    public void setRoll(int roll){
        this.roll=roll;
    }
}
