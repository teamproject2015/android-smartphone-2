package de.unimannheim.loggingapp.touchlogger;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import de.unimannheim.loggingapp.BaseActivity;
import de.unimannheim.loggingapp.NavigationDrawerFragment;
import de.unimannheim.loggingapp.R;

/**
 * @author suryadevara
 *         Created on 15.06.2015
 *         <p/>
 *         TouchloggerActivity Class is used to record the Logger activities to train
 *         the keystoke for Keyboard
 */
public class TouchloggerActivity extends BaseActivity {


    private static final String CLASS_NAME = "TouchloggerActivity";


    /**
     * This method overrides the default onCreate method for Super Class Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchlogger);

        Toolbar toolbar = callToolBar();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationDrawerFragment drawerFragment
                = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.main_drawerLayout), toolbar);



        Button buttonContinue = (Button) findViewById(R.id.button_touchLoggerContinue);
        final Spinner layout = (Spinner) findViewById(R.id.spinner_layout);
        final Spinner orientation = (Spinner) findViewById(R.id.spinner_orientation);
        final TextView textViewLayout = (TextView) findViewById(R.id.textView_orientation);

        layout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(orientation!=null) {
                    if (position != 0) {
                        orientation.setVisibility(View.INVISIBLE);
                        textViewLayout.setVisibility(View.INVISIBLE);
                    } else {
                        orientation.setVisibility(View.VISIBLE);
                        textViewLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Layout values
                 * 0. Keyboard
                 * 1. Icon Grid
                 * 2. PIN code
                 */
                int layoutPosition = layout.getSelectedItemPosition();
                Log.d(CLASS_NAME,"------------"+layoutPosition+"------------------");
                if(layoutPosition==0) {
                    /**
                     * Orientation values
                     * 0. Left Orientation
                     * 1. Right Orientation
                     * 2. portrait
                     */
                    int orientationPosition = orientation.getSelectedItemPosition();
                    Log.d(CLASS_NAME,layoutPosition+" and "+ orientationPosition +" is selected");
                    if(orientationPosition==0) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    } else if(orientationPosition==1) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    } else if(orientationPosition==2) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    }
                    //setContentView(R.layout.activity_touchlogger_keyboard);
                    Intent i = new Intent(getApplicationContext(), KeyboardActivity.class);
                    startActivity(i);
                } else if(layoutPosition==1) {
                    Log.d(CLASS_NAME,layoutPosition+" is selected");
                    Intent i = new Intent(getApplicationContext(), IconGridActivity.class);
                    startActivity(i);
                } else if(layoutPosition==2) {
                    Log.d(CLASS_NAME,layoutPosition+" is selected");
                    Intent i = new Intent(getApplicationContext(), PinCodeActivity.class);
                    startActivity(i);
                }
            }
        });

    }


}