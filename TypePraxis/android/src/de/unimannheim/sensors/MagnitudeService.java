package de.unimannheim.sensors;

/**
 * Created by Saimadhav S on 06.12.2015.
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

import de.unimannheim.backgroundservices.BackgroundService;
import de.unimannheim.utils.Constants;


public class MagnitudeService extends Service implements
        SensorEventListener {

    private ResultReceiver mReceiver;
    private SensorManager sensorManager;
    private float[] values;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mReceiver = intent.getParcelableExtra(Constants.EXTRA_RECEIVER);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        // Setup and start collecting
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                BackgroundService.SENSOR_DELAY);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // Stop collecting and tear down
        sensorManager.unregisterListener(this);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final String TAG = MagnitudeService.class.getSimpleName();

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            values = event.values;


            if (mReceiver != null) {
                Bundle result = new Bundle();
                result.putInt(SensorResultReceiver.SENSOR_TYPE, event.sensor.getType());
                result.putLong(SensorResultReceiver.SENSOR_TIMESTAMP, event.timestamp);
                result.putFloat(SensorResultReceiver.EXTRA_X, values[0]);
                result.putFloat(SensorResultReceiver.EXTRA_Y, values[1]);
                result.putFloat(SensorResultReceiver.EXTRA_Z, values[2]);
                mReceiver.send(SensorResultReceiver.RESULTCODE_UPDATE,
                        result);
            }
        }
    }

}

