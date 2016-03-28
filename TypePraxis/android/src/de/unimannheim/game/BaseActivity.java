package de.unimannheim.game;

import android.app.Activity;
import android.hardware.SensorManager;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.badlogic.gdx.backends.android.AndroidApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by suryadevara on 13-06-2015.
 */
public class BaseActivity extends AndroidApplication {

    private static final String CLASS_NAME = "BaseActivity";

    protected static final int KEYSTROKE_COUNT = 40;

    public static final int SENSOR_DELAY = SensorManager.SENSOR_DELAY_GAME;



    /**
     * Writing the Data to File to ExternalStorageDirectory
     *
     * @param data data which contains the Logger values
     */
    protected boolean writeToFile(String userDetails,String data, String fileName) {
        //Log.i(CLASS_NAME,"data--------->"+data);
        boolean isNewFile = false;
        boolean isFinished = false;
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            Log.i(CLASS_NAME, "sdCard-->" + sdCard.getAbsolutePath());
            File directory = new File(sdCard.getAbsolutePath() + "/TouchLogger");
            directory.mkdirs();
            File file = new File(directory, fileName);
            FileOutputStream fos;
            if(!file.exists()) {
                isNewFile = true;
            }
            try {
                fos = new FileOutputStream(file, true);
                Log.d(CLASS_NAME, "Data is append to the File " + fileName);
            } catch (FileNotFoundException fileNotFou0ndException) {
                fos = new FileOutputStream(file);
                Log.d(CLASS_NAME, "File " + fileName + " is created with new data");
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);

            if(isNewFile) {
                outputStreamWriter.write(userDetails+ getString(R.string.file_headerfields)+"\r\n"+data);
            } else {
                outputStreamWriter.write(data);
            }
            outputStreamWriter.close();
            isFinished = true;

        } catch (IOException e) {
            Log.e(CLASS_NAME, "File write failed: " + e.toString());
        }
        return isFinished;
    }



    protected String readFromFile(String fileName) {
        String ret = "";
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e(CLASS_NAME, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Can not read file: " + e.toString());
        }

        return ret;
    }

}
