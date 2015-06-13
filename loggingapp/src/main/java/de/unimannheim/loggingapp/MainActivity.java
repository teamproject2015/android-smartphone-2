package de.unimannheim.loggingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        setContentView(R.layout.activity_main);
        callToolBar();
        session = new SessionManager(getApplicationContext());
        final Button buttonContinue = (Button) findViewById(R.id.button_continue);
        final EditText userName = (EditText) findViewById(R.id.editText_personName);
        TextView nameLabel = (TextView) findViewById(R.id.textView_enterName);

        if (session.getUserName() == null || "".equals(session.getUserName())) {
            buttonContinue.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // Perform action on click

                    session.setUserName(userName.getText().toString());
                    Intent activityChangeIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(activityChangeIntent);
                    //setContentView(R.layout.activity_main);
                }
            });
        } else {
            nameLabel.setText(getString(R.string.label_welcome)
                    + session.getUserName() + "\n"+ getString(R.string.navigate_message));
            buttonContinue.setVisibility(View.INVISIBLE);
            userName.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (session.getUserName() == null || "".equals(session.getUserName())) {
            MenuItem menuTouchLogger = menu.findItem(R.id.action_touchlogger);
            menuTouchLogger.setEnabled(false);
            return false;
        }
        return true;
    }

}
