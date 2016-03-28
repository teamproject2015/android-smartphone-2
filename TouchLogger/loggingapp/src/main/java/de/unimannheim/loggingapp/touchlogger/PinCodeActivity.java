package de.unimannheim.loggingapp.touchlogger;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import de.unimannheim.loggingapp.R;
import de.unimannheim.loggingapp.sensors.SensorManagerActivity;
import de.unimannheim.loggingapp.session.SessionManager;

public class PinCodeActivity extends SensorManagerActivity {

    private static final String FILENAME = "PinCodeTouchLogger";
    private static final String CLASS_NAME = PinCodeActivity.class.getName();
    private static String NUMERIC_RANDOMLETTERS = "0123456789";
    private int count;
    private long downTime;
    private String csvFileName;
    private TextView typedKeyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchlogger_pincode);
        generateRandomKey(NUMERIC_RANDOMLETTERS);
        callToolBar();

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/TouchLogger");

        if(directory.exists()) {
            String[] files = directory.list();
            int count = 0;
            for (int i=0; i<files.length; i++) {
                if(files[i].contains(FILENAME)) {
                    count++;
                }
            }
            csvFileName = FILENAME +count;
        }

        View view = this.findViewById(android.R.id.content);
        Keyboard mKeyboard = new Keyboard(this, R.xml.pinkeyboard);
        typedKeyTextView = (TextView) findViewById(R.id.textView_typedKey);

        KeyboardView mKeyboardView = callCustomKeyboard(view, mKeyboard, R.id.keyboardview);
        // Install the key handler
        mKeyboardView.setOnKeyboardActionListener(new KeyboardActionListener() {

            @Override
            public void onPress(int primaryCode) {
                //Here check the primaryCode to see which key is pressed
                //based on the android:codes property
                View focusCurrent = PinCodeActivity.this.getWindow().getCurrentFocus();
                if (focusCurrent == null) return;
                EditText edittext = (EditText) focusCurrent;
                Editable editable = edittext.getText();
                int start = edittext.getSelectionStart();

                if (primaryCode == Keyboard.KEYCODE_DELETE) {
                    if (editable != null && start > 0)
                        editable.delete(start - 1, start);
                       typedKeyTextView.setText(editable.toString());
                } else {
                    //Log.i(CLASS_NAME, "You just pressed " + primaryCode + " button");
                    editable.insert(start, Character.toString((char) primaryCode));
                    typedKeyTextView.setText(editable.toString());
                }
            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {

            }

        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
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
        super.dispatchTouchEvent(event);

        EditText keyValue = (EditText) findViewById(R.id.editText_key);

        if ("".equals(keyValue.getText().toString())) {
            return true;
        }

        //Log.i("Record", "Key=" + keyValue.getText().toString());
        //
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downTime = System.nanoTime();
            int pointerIndex = event.getActionIndex();

            //Log.i("TOUCHDOWN", "Key=" + keyValue.getText().toString() + ",coordinateX=" + event.getX(pointerIndex) + ",coordinateY=" + event.getY(pointerIndex));
            recordTouchEvent(event.getX(pointerIndex), event.getY(pointerIndex), keyValue.getText().toString(), downTime, 0);
        }


        if (event.getAction() == MotionEvent.ACTION_UP) {

            //case MotionEvent.ACTION_UP
            //this is the time in milliseconds
            long upTime = System.nanoTime();

            recordTouchEvent(0, 0, "", downTime, upTime);

            count++;
            if (count == KEYSTROKE_COUNT) {
                    /*writeToFile(logValues.toString(), FILENAME);
                    Toast.makeText(getApplicationContext(),
                            "Key Stocks Saved",
                            Toast.LENGTH_SHORT).show();*/
                SavePinCodeCSVFile saveCSVFile = new SavePinCodeCSVFile();
                saveCSVFile.execute(logValues.toString());
                logValues.setLength(0);
                count = 0;
            }

            //final EditText textMessage = (EditText) findViewById(R.id.editText_key);
            //Log.i(CLASS_NAME, "generatedKey value = " + generatedKey);
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
            keyValue.setText("");
            keyValue.removeTextChangedListener(tw);

        }

        return true;
    }

    private class SavePinCodeCSVFile extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            SessionManager session = new SessionManager(getApplicationContext());
            return writeToFile(getBundleData(), params[0], csvFileName + ".csv");
        }

        @Override
        protected void onPostExecute(Boolean isFinished) {
            if (isFinished) {
                Toast.makeText(getApplicationContext(),
                        "Key Stocks Saved",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Key Stocks not saved",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
