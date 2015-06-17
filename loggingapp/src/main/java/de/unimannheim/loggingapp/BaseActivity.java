package de.unimannheim.loggingapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by suryadevara on 13-06-2015.
 */
public class BaseActivity extends AppCompatActivity {

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
}
