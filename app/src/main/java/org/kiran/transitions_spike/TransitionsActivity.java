package org.kiran.transitions_spike;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;


public class TransitionsActivity extends Activity {

    //scenes to transition
    private Scene scene1, scene2;
    //transition to move between scenes
    private Transition transition;
    //flag to swap between scenes
    private boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitions_start);

        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);

        ViewGroup startViews = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.activity_transitions_start, baseLayout, false);

        ViewGroup endViews = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.activity_transitions_end, baseLayout, false);

        //create the two scenes
        scene1 = new Scene(baseLayout, startViews);
        scene2 = new Scene(baseLayout, endViews);

        //create transition, set properties
        transition = new AutoTransition();
        transition.setDuration(5000);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        //initialize flag
        start = true;
    }

    public void changeScene(View v) {

        //check flag
        if (start) {
            TransitionManager.go(scene2, transition);
            start = false;
        } else {
            TransitionManager.go(scene1, transition);
            start = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transitions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
