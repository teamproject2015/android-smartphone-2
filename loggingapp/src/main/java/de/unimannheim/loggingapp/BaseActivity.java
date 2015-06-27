package de.unimannheim.loggingapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
public class BaseActivity extends AppCompatActivity {

    private static final String CLASS_NAME = "BaseActivity";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public Toolbar callToolBar() {
        Toolbar tlBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tlBar);
        return tlBar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            //Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
            //startActivity(i);
            //setContentView(R.layout.avtivity_next);
            Toast.makeText(getApplicationContext(), "under progress, working on it", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_touchlogger) {
            Intent i = new Intent(getApplicationContext(),TouchloggerActivity.class);
            startActivity(i);
            //setContentView(R.layout.activity_touchlogger);
        }*/

        if(id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Writing the Data to File to ExternalStorageDirectory
     *
     * @param data data which contains the Logger values
     */
    protected void writeToFile(String data,String fileName) {
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            Log.i(CLASS_NAME, "sdCard-->" + sdCard.getAbsolutePath());
            File directory = new File(sdCard.getAbsolutePath() + "/TouchLogger");
            directory.mkdirs();
            File file = new File(directory, fileName);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file, true);
                Log.d(CLASS_NAME, "Data is append to the File "+fileName);
            } catch (FileNotFoundException fileNotFoundException) {
                fos = new FileOutputStream(file);
                Log.d(CLASS_NAME, "File "+fileName+" is created with new data");
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);
            outputStreamWriter.close();


        } catch (IOException e) {
            Log.e(CLASS_NAME, "File write failed: " + e.toString());
        }

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
