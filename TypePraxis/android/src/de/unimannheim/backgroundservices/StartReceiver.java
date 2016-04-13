package de.unimannheim.backgroundservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Saimadhav S on 16.03.2016.
 */
public class StartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("StartReceiver", "StartReceiver started");
        Intent service = new Intent(context, BackgroundService.class);
        context.startService(service);
    }
}
