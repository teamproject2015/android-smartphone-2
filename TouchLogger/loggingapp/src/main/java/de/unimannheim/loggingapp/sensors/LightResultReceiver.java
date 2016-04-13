package de.unimannheim.loggingapp.sensors;

/**
 * Created by Saimadhav S on 06.12.2015.
 * <p/>
 * GyroscopeService which extends service class
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class LightResultReceiver extends ResultReceiver {

    public static final int RESULTCODE_ERROR = -1;
    public static final String EXTRA_ERRORMSG = "LightResultReceiver.ERRORMSG";

    public static final int RESULTCODE_UPDATE = 1;
    public static final String EXTRA_X = "LightResultReceiver.X";
    public static final String EXTRA_Y = "LightResultReceiver.Y";
    public static final String EXTRA_Z = "LightResultReceiver.Z";

    private Receiver mReceiver;

    public LightResultReceiver(Handler handler) {
        super(handler);
    }

    public interface Receiver {
        void newEvent(float x, float y, float z);

        void error(String error);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            if (resultCode == RESULTCODE_ERROR) {
                mReceiver.error(resultData.getString(EXTRA_ERRORMSG));
            } else {
                float x = resultData.getFloat(EXTRA_X);
                float y = resultData.getFloat(EXTRA_Y);
                float z = resultData.getFloat(EXTRA_Z);
                mReceiver.newEvent(x, y, z);
            }
        }
    }

}
