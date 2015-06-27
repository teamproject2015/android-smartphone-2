package de.unimannheim.loggingapp.touchlogger;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import de.unimannheim.loggingapp.R;

public class PinCodeActivity extends SensorManagerActivity {

    private Keyboard mKeyboard;
    private KeyboardView mKeyboardView;
    private static final String FILENAME = "PinCodeTouchLogger.csv";

    private static final String CLASS_NAME = PinCodeActivity.class.getName();;

    private static String NUMERIC_RANDOMLETTERS = "0123456789";

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchlogger_pincode);
        generateRandomKey(NUMERIC_RANDOMLETTERS);
        callToolBar();

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //input.setRawInputType(Configuration.KEYBOARD_NOKEYS);

        // Create the Keyboard
        mKeyboard = new Keyboard(this, R.xml.pinkeyboard);

        // Lookup the KeyboardView
        mKeyboardView = (KeyboardView) findViewById(R.id.pinkeyboardview);
        // Attach the keyboard to the view
        mKeyboardView.setKeyboard(mKeyboard);

        mKeyboardView.setVisibility(View.VISIBLE);
        mKeyboardView.setEnabled(true);

        View view = this.findViewById(android.R.id.content);

        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), 0);


        // Install the key handler
        mKeyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {

            EditText keyValue = (EditText) findViewById(R.id.editText_key);
            String previousValue = keyValue.getText().toString();
            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                //Here check the primaryCode to see which key is pressed
                //based on the android:codes property
                /*if (primaryCode == 1) {
                */
                Log.i("Key", "You just pressed "+primaryCode+" button");
                keyValue.setText(previousValue + primaryCode);
                //}
            }

            @Override
            public void onPress(int arg0) {
            }

            @Override
            public void onRelease(int primaryCode) {
            }

            @Override
            public void onText(CharSequence text) {
            }

            @Override
            public void swipeDown() {
            }

            @Override
            public void swipeLeft() {
            }

            @Override
            public void swipeRight() {
            }

            @Override
            public void swipeUp() {
            }
        });

    }


    /**
     * When the User press/ touch the screen the event from OnTouchevent will be
     * triggering the onTouch method, the method will save the x,y coordinates
     * and accelerometer and orientation coordinates
     *
     * @param event keyevent
     * @return boolean value
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        EditText keyValue = (EditText) findViewById(R.id.editText_key);
        Toast.makeText(getApplicationContext(), "keyValue-->" + keyValue.getText().toString(), Toast.LENGTH_SHORT).show();
        if (keyValue.getText() != null
                && !"".equals(keyValue.getText().toString())
                && generatedKey != null
                && generatedKey.equalsIgnoreCase(keyValue.getText().toString())) {

            recordTouchEvent(event);

            count++;
            if (count == 40) {
                writeToFile(logValues, FILENAME);
                Toast.makeText(getApplicationContext(),
                        "Key Stocks Saved",
                        Toast.LENGTH_SHORT).show();
                count = 0;
            }

            //final EditText textMessage = (EditText) findViewById(R.id.editText_key);
            Log.i(CLASS_NAME, "generatedKey value = " + generatedKey);
            TextWatcher tw = new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    generateRandomKey(NUMERIC_RANDOMLETTERS);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            };
            keyValue.addTextChangedListener(tw);

            //  generateRandomKey();
            keyValue.setText("");
            keyValue.removeTextChangedListener(tw);
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

}
