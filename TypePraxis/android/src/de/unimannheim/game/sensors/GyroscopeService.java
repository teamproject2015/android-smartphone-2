package de.unimannheim.game.sensors;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

import de.unimannheim.game.BaseActivity;
import de.unimannheim.game.utils.Constants;

public class GyroscopeService extends Service implements
		SensorEventListener {

	private ResultReceiver mReceiver;
	private SensorManager sensorManager;

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
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
				BaseActivity.SENSOR_DELAY);
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


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
			// send something?
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
			float[] values = event.values;
			// Movement
			float x = values[0];
			float y = values[1];
			float z = values[2];

//			Log.d(TAG, "x: " + x);
//			Log.d(TAG, "y: " + y);
//			Log.d(TAG, "z: " + z);

			if (mReceiver != null) {
				Bundle result = new Bundle();
				result.putInt(SensorResultReceiver.SENSOR_TYPE, event.sensor.getType());
				result.putFloat(SensorResultReceiver.EXTRA_X, x);
				result.putFloat(SensorResultReceiver.EXTRA_Y, y);
				result.putFloat(SensorResultReceiver.EXTRA_Z, z);
				mReceiver.send(SensorResultReceiver.RESULTCODE_UPDATE,
						result);
			}
		}
	}

}
