package de.unimannheim.backgroundservices;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Saimadhav S on 16.03.2016.
 */
public class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {

    static String currentGestureDetected;

    @Override
    public boolean onDown(MotionEvent ev) {
        currentGestureDetected=ev.toString();

        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        currentGestureDetected=e1.toString()+ "  "+e2.toString();
        return true;
    }
}
