package de.unimannheim.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends Activity {

    private TypePraxisGame typePraxisGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        CustomFont.getInstance(getApplicationContext());
        CustomFontHelper.applyFont(findViewById(R.id.fullscreen_content_controls), getApplicationContext());

        Button startButton = (Button)findViewById(R.id.start_button);
        startButton.setBackgroundColor(getResources().getColor(R.color.accent));

        findViewById(R.id.start_button).setOnClickListener(mStartTouchListener);
        findViewById(R.id.exit_button).setOnClickListener(mExitTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnClickListener mStartTouchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Intent localIntent = new Intent(this, GameActivityLandscape.class);
            Button button = (Button) findViewById(R.id.start_button);
            button.setBackgroundColor(Color.parseColor("#B58905"));
            Intent intent =  new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
            //button.setBackgroundColor(Color.parseColor("#FFC107"));
        }
    };


    View.OnClickListener mExitTouchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Intent localIntent = new Intent(this, GameActivityLandscape.class);
            Button button = (Button) findViewById(R.id.exit_button);
            button.setBackgroundColor(Color.parseColor("#B58905"));
            exit();
        }
    };

    /**
     * When on click the exit button, the following methos is called
     */
    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure, Do you want to Exit?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Overriding the back button pressed event
     */
    @Override
    public void onBackPressed() {
        exit();
    }


}
