package de.unimannheim.loggingapp.sensors;

/**
 * Created by Saimadhav S on 06.12.2015.
 * <p/>
 * AccelerometerService which extends service class
 */

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

import de.unimannheim.loggingapp.BaseActivity;
import de.unimannheim.loggingapp.utils.Constants;


public class AccelerometerService extends Service implements
        SensorEventListener {

    /*
     * time smoothing constant for low-pass filter
     * 0 ≤ α ≤ 1 ; a smaller value basically means more smoothing
     * See: http://en.wikipedia.org/wiki/Low-pass_filter#Discrete-time_realization
     */
    private static final float ALPHA = 0.2f;
    private ResultReceiver mReceiver;
    private SensorManager sensorManager;
    private float[] values;

    /**
     * The system calls this method when another component, such as an activity,
     * requests that the service be started, by calling startService().
     * once this method is implemented, we need to stop the service
     * when its work is done, by calling stopSelf() or stopService() methods.
     *
     * @param intent intent
     * @param flags flags
     * @param startId startid
     * @return integer value
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mReceiver = intent.getParcelableExtra(Constants.EXTRA_RECEIVER);
        return START_STICKY;
    }

    /**
     * The system calls this method when the service is first
     * created using onStartCommand() or onBind().
     * This call is required to perform one-time set-up.
     * Activating the sensormanager for accelerometer
     */
    @Override
    public void onCreate() {
        // Setup and start collecting
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                BaseActivity.SENSOR_DELAY);
        //lastUpdate = System.currentTimeMillis();
        super.onCreate();
    }

    /**
     * The system calls this method when the service is no longer used and is being destroyed.
     * unregister all the sensorManager listeners and clean up
     * if any resources available such as threads, registered listeners, receivers, etc.
     */
    @Override
    public void onDestroy() {
        // Stop collecting and tear down
        sensorManager.unregisterListener(this);
        super.onDestroy();
    }

    /**
     * The system calls this method when another component wants to bind with the service by calling bindService().
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /**
     * onsensor change the method is called and binding is performed in mReceiver.send
     *
     * @param event sensor event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //values = lowPass( event.values, values );

            values = event.values;

            /*long now = System.currentTimeMillis();
            if ((now - lastUpdate) > 50){
                lastUpdate = now;*/

            // Movement
            /*float x = values[0];
            float y = values[1];
            float z = values[2];*/

            //Log.d("AccelerometerService", "x=" + x + ",y=" + y + ",z=" + z+ ",timestamp=" + event.timestamp);

            if (mReceiver != null) {
                Bundle result = new Bundle();
                result.putInt(SensorResultReceiver.SENSOR_TYPE, event.sensor.getType());
                result.putLong(SensorResultReceiver.SENSOR_TIMESTAMP, event.timestamp);
                result.putFloat(SensorResultReceiver.EXTRA_X, values[0]);
                result.putFloat(SensorResultReceiver.EXTRA_Y, values[1]);
                result.putFloat(SensorResultReceiver.EXTRA_Z, values[2]);
                mReceiver.send(SensorResultReceiver.RESULTCODE_UPDATE, result);
            }
//        }
        }
    }


    /**
     * This method provides the lospass filters for the sensor data
     *
     * @see http://en.wikipedia.org/wiki/Low-pass_filter#Algorithmic_implementation
     * @see http://en.wikipedia.org/wiki/Low-pass_filter#Simple_infinite_impulse_response_filter
     */
    protected float[] lowPass(float[] input, float[] output) {
        if (output == null) return input;

        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }

}

