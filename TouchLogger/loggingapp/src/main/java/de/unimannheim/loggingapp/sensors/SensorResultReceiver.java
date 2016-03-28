package de.unimannheim.loggingapp.sensors;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

public class SensorResultReceiver extends ResultReceiver {

    public static final int RESULTCODE_ERROR = -1;
    public static final String EXTRA_ERRORMSG = "SensorResultReceiver.ERRORMSG";

    public static final int RESULTCODE_UPDATE = 1;
    public static final String SENSOR_TYPE = "SensorResultReceiver.SENSORTYPE";
    public static final String EXTRA_X = "SensorResultReceiver.X";
    public static final String EXTRA_Y = "SensorResultReceiver.Y";
    public static final String EXTRA_Z = "SensorResultReceiver.Z";

    private Receiver mReceiver;

    public SensorResultReceiver(Handler handler) {
        super(handler);
    }

    public interface Receiver {
        public void newEvent(int SensorType, float x, float y, float z);
        public void error(String error);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        //Log.i("Receiver", "entering the onReceiveResult ");
        if (mReceiver != null) {
            if (resultCode == RESULTCODE_ERROR) {
                mReceiver.error(resultData.getString(EXTRA_ERRORMSG));
            } else {
                int sensorType = resultData.getInt(SENSOR_TYPE);
                float x = resultData.getFloat(EXTRA_X);
                float y = resultData.getFloat(EXTRA_Y);
                float z = resultData.getFloat(EXTRA_Z);
                //Log.i("AcceReceiver", "sensorType=" + sensorType + "x=" + x + ",y=" + y + ",z=" + z);
                mReceiver.newEvent(sensorType,x, y, z);
            }
        }
        //Log.i("Receiver", "exiting the onReceiveResult ");
    }

}
