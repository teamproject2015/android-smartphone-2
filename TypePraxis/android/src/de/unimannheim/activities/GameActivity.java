package de.unimannheim.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.unimannheim.game.AndroidView;
import de.unimannheim.game.ApplicationBundle;
import de.unimannheim.game.TypePraxisGame;

public class GameActivity extends AndroidApplication implements TypePraxisGame.MyGameCallback {

    private static final String FILENAME = "KeyboardInputLogging";

    private View rootView;
    private AndroidView androidView;
    private int width, height;
    AndroidApplicationConfiguration config;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        config = new AndroidApplicationConfiguration();


        rootView = this.getWindow().getDecorView().getRootView();
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        width = rect.width();
        height = rect.height();
        androidView = new AndroidView(width, height);

        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right,
                                       int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                Rect rect = new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);

                if (!(width == rect.width() && height == rect.height())) {
                    width = rect.width();
                    height = rect.height();
                    androidView.onSizeChange(width, height);
                }
            }

        });

        initialize(new TypePraxisGame(new ApplicationBundle(androidView),this), config);
    }

    //@Override
    /*public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downTime = System.nanoTime();
            int pointerIndex = event.getActionIndex();
            recordTouchEvent(event.getX(pointerIndex), event.getY(pointerIndex), keyValue.getText().toString(), downTime, 0);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {

            //case MotionEvent.ACTION_UP
            //this is the time in nanoseconds
            long upTime = System.nanoTime();
            recordTouchEvent(0, 0, "", downTime, upTime);

            count++;
            if (count == KEYSTROKE_COUNT) {
                SaveCSVFile saveCSVFile = new SaveCSVFile();
                saveCSVFile.execute(logValues.toString());
                logValues.setLength(0);
                count = 0;
            }
        }

        return false;
    }*/



    @Override
    public void onResume() {
        super.onResume();
        initialize(new TypePraxisGame(new ApplicationBundle(androidView), this), config);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
