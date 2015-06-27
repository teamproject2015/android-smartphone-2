package de.unimannheim.loggingapp.touchlogger;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

import de.unimannheim.loggingapp.MainActivity;
import de.unimannheim.loggingapp.R;


public class KeyboardActivity extends SensorManagerActivity {

    private static final String FILENAME = "KeyboardTouchLogger.csv";

    private static final String CLASS_NAME = KeyboardActivity.class.getName();;

    private static String ALPHANUMERIC_RANDOMLETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchlogger_keyboard);
        generateRandomKey(ALPHANUMERIC_RANDOMLETTERS);
        callToolBar();

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
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
                    generateRandomKey(ALPHANUMERIC_RANDOMLETTERS);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            };
            keyValue.addTextChangedListener(tw);
            keyValue.setText("");
            keyValue.removeTextChangedListener(tw);
            return true;
        }
        return super.dispatchTouchEvent(event);
    }
}
