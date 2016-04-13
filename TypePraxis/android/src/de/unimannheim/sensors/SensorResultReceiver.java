package de.unimannheim.sensors;

/**
 * Created by suryadevara on 18-06-2015.
 * <p/>
 * SensorResultReceiver extends the resultreceiver class
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class SensorResultReceiver extends ResultReceiver {

    // constant which are used to get the data
    public static final int RESULTCODE_ERROR = -1;
    public static final String EXTRA_ERRORMSG = "SensorResultReceiver.ERRORMSG";
    public static final int RESULTCODE_UPDATE = 1;
    public static final String SENSOR_TYPE = "SensorResultReceiver.SENSORTYPE";
    public static final String SENSOR_TIMESTAMP = "SensorResultReceiver.SENSORTIMESTAMP";
    public static final String EXTRA_X = "SensorResultReceiver.X";
    public static final String EXTRA_Y = "SensorResultReceiver.Y";
    public static final String EXTRA_Z = "SensorResultReceiver.Z";

    private Receiver mReceiver;

    public SensorResultReceiver(Handler handler) {
        super(handler);
    }

    /**
     * interface Receiver is used by other class to get the data from sensor services
     */
    public interface Receiver {
        void newEvent(int SensorType, long timestamp, float x, float y, float z);
        void error(String error);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    /**
     * When the results are received the onReceiveResult gets bundle ans sets it to the mReceiver
     *
     * @param resultCode result code
     * @param resultData result data
     */
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
                long timestamp = resultData.getLong(SENSOR_TIMESTAMP);

                //Log.i("AcceReceiver", "sensorType=" + sensorType + "x=" + x + ",y=" + y + ",z=" + z);
                mReceiver.newEvent(sensorType, timestamp, x, y, z);
            }
        }
        //Log.i("Receiver", "exiting the onReceiveResult ");
    }

}
