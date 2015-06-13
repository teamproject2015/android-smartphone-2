package de.unimannheim.loggingapp.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by suryadevara on 13-06-2015.
 */
public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserName(String userName) {
        prefs.edit().putString("userName", userName).commit();
    }

    public String getUserName() {
        return prefs.getString("userName","");
    }
}
