package de.unimannheim.loggingapp.touchlogger;

import android.inputmethodservice.KeyboardView;

/**
 * Created by suryadevara on 02-07-2015.
 */
public abstract class KeyboardActionListener implements KeyboardView.OnKeyboardActionListener {


    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    public abstract void onKey(int primaryCode, int[] keyCodes);

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeUp() {
    }
}
