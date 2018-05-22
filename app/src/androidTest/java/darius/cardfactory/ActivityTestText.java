/* TODO
package darius.cardfactory;


import android.app.Activity;
import android.content.Intent;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;

import darius.cardfactory.MainActivity;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ActivityTestText extends ActivityTestRule<MainActivity>{

    public ActivityTestText() {
        super(MainActivity.class);
    }

    @Test
    public void TestText(){
        final Activity mainActivity=this.getActivity();
        mainActivity.runOnUiThread(
                new Runnable() {
                    public void run() {
                        TextView curTextView=(TextView)mainActivity.findViewById(R.id.description);
                        String curText=curTextView.getText().toString();
                        mainActivity.findViewById(R.id.try_button).callOnClick();
                        curTextView=(TextView)mainActivity.findViewById(R.id.description);
                        assert (curText.compareTo(curTextView.getText().toString())!=0);
                    }
                }
        );
        mainActivity.finish();
    }
}
*/