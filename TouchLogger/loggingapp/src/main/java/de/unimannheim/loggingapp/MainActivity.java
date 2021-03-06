package de.unimannheim.loggingapp;

/**
 * @author Saimadhav S
 * Created on 15.06.2015
 *
 * When Application is called it first calls the MainActivity as default launching activiy
 */

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.unimannheim.loggingapp.session.SessionManager;


public class MainActivity extends BaseActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appbar);
        Toolbar toolbar = callToolBar();

        session = new SessionManager(getApplicationContext());
        final Button buttonContinue = (Button) findViewById(R.id.button_continue);
        final EditText userName = (EditText) findViewById(R.id.editText_personName);
        TextView nameLabel = (TextView) findViewById(R.id.textView_enterName);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);

        if (session.getUserName() != null && !"".equals(session.getUserName())) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            NavigationDrawerFragment drawerFragment
                    = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
            drawerFragment.setUp(R.id.fragment_navigation_drawer, mDrawerLayout , toolbar);

            nameLabel.setText(getString(R.string.label_welcome)
                    + " " +session.getUserName() + "\n\n" + getString(R.string.navigate_message));
            nameLabel.setGravity(Gravity.CENTER_HORIZONTAL);
            nameLabel.setTextAppearance(this,android.R.style.TextAppearance_Medium);
            buttonContinue.setVisibility(View.INVISIBLE);
            userName.setVisibility(View.INVISIBLE);

        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            buttonContinue.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // Perform action on click
                    Log.i("MainActivity", "buttonContinue is pressed");
                    session.setUserName(userName.getText().toString());
                    buttonContinue.setEnabled(false);
                    //startActivity(getIntent());
                    recreate();
                }
            });
        }


    }

}
