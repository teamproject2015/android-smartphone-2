package de.unimannheim.loggingapp.touchlogger;

import android.content.pm.ActivityInfo;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import de.unimannheim.loggingapp.R;
import de.unimannheim.loggingapp.sensors.SensorManagerActivity;
import de.unimannheim.loggingapp.session.SessionManager;


public class KeyboardActivity extends SensorManagerActivity {

    private static final String FILENAME = "KeyboardTouchLogger";

    private static final String CLASS_NAME = KeyboardActivity.class.getName();

    private static String ALPHANUMERIC_RANDOMLETTERS = "abcdefghijklmnopqrstuvwxyz";

    private int count;
    private boolean mCapsLock;
    private long mLastShiftTime;

    private Keyboard mQwertyKeyboard;
    private Keyboard mSymbolsKeyboard;
    private Keyboard mSymbolsShiftedKeyboard;
    private KeyboardView mKeyboardView;
    private TextView typedKeyTextView;
    private long downTime;

    private String csvFileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchlogger_keyboard);
        generateRandomKey(ALPHANUMERIC_RANDOMLETTERS);
        callToolBar();

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        int orientation = bundle.getInt("orientationPosition");
        typedKeyTextView = (TextView) findViewById(R.id.textView_typedKey);


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


        Log.d(CLASS_NAME, "orientation-->" + orientation);
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        }

        View view = this.findViewById(android.R.id.content);

        // Create the Keyboard
        mQwertyKeyboard = new Keyboard(this, R.xml.customkeyboard);
        mSymbolsKeyboard = new Keyboard(this, R.xml.customkeyboard_symbols);
        mSymbolsShiftedKeyboard = new Keyboard(this, R.xml.customkeyboard_shift);

        mKeyboardView = callCustomKeyboard(view, mQwertyKeyboard, R.id.keyboardview);
        // Install the key handler
        mKeyboardView.setOnKeyboardActionListener(new KeyboardActionListener() {

            @Override
            public void onPress(int primaryCode) {
                //Here check the primaryCode to see which key is pressed
                //based on the android:codes property
                View focusCurrent = KeyboardActivity.this.getWindow().getCurrentFocus();
                if (focusCurrent == null) return;
                EditText edittext = (EditText) focusCurrent;
                Editable editable = edittext.getText();
                int start = edittext.getSelectionStart();


                if (primaryCode == Keyboard.KEYCODE_DELETE) {
                    handleBackspace(editable, start);
                } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                    handleShift();
                } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE
                        && mKeyboardView != null) {
                    Keyboard current = mKeyboardView.getKeyboard();
                    if (current == mSymbolsKeyboard || current == mSymbolsShiftedKeyboard) {
                        current = mQwertyKeyboard;
                    } else {
                        current = mSymbolsKeyboard;
                    }
                    mKeyboardView.setKeyboard(current);
                    if (current == mSymbolsKeyboard) {
                        current.setShifted(false);
                    }
                } else {
                    handleCharacter(primaryCode, editable, start);
                }
            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {

            }

        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    private void handleBackspace(Editable editable, int start) {
        if (editable == null)
            return;
        if (start > 0) {
            editable.delete(start - 1, start);
            typedKeyTextView.setText(editable.toString());
        }
    }

    private void handleShift() {
        if (mKeyboardView == null) {
            return;
        }
        Keyboard currentKeyboard = mKeyboardView.getKeyboard();
        if (mQwertyKeyboard == currentKeyboard) {
            // Alphabet keyboard
            checkToggleCapsLock();
            mKeyboardView.setShifted(mCapsLock || !mKeyboardView.isShifted());
        } else if (currentKeyboard == mSymbolsKeyboard) {
            mSymbolsKeyboard.setShifted(true);
            mKeyboardView.setKeyboard(mSymbolsShiftedKeyboard);
            mSymbolsShiftedKeyboard.setShifted(true);
        } else if (currentKeyboard == mSymbolsShiftedKeyboard) {
            mSymbolsShiftedKeyboard.setShifted(false);
            mKeyboardView.setKeyboard(mSymbolsKeyboard);
            mSymbolsKeyboard.setShifted(false);
        }
    }

    private void checkToggleCapsLock() {
        long now = System.currentTimeMillis();
        if (mLastShiftTime + 800 > now) {
            mCapsLock = !mCapsLock;
            mLastShiftTime = 0;
        } else {
            mLastShiftTime = now;
        }
    }

    private void handleCharacter(int primaryCode, Editable editable, int start) {

        if (mKeyboardView.isShifted()) {
            primaryCode = Character.toUpperCase(primaryCode);
        }

        editable.insert(start, Character.toString((char) primaryCode));
        typedKeyTextView.setText(editable.toString());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


    private class SaveCSVFile extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
           // SessionManager session = new SessionManager(getApplicationContext());
            return writeToFile(getBundleData(), params[0], csvFileName+".csv");
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
                SaveCSVFile saveCSVFile = new SaveCSVFile();
                saveCSVFile.execute(logValues.toString());
                logValues.setLength(0);
                count = 0;
            }

            //final EditText textMessage = (EditText) findViewById(R.id.editText_key);
            //Log.i(CLASS_NAME, "generatedKey value = " + generatedKey);
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

        }

        return true;
    }


}
