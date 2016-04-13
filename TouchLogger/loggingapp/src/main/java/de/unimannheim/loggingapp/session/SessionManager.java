package de.unimannheim.loggingapp.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Saimadhav S on 13-06-2015.
 * SessionManager is used to handle the session of Application
 */
public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public String getUserName() {
        return prefs.getString("userName", "");
    }

    public void setUserName(String userName) {
        prefs.edit().putString("userName", userName).apply();
    }
}
