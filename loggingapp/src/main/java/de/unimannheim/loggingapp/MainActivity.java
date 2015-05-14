package de.unimannheim.loggingapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;


public class MainActivity extends Activity
        implements SensorEventListener {

    private static final String TAG = MainActivity.class.getName();
    private static final String FILENAME = "myLogFile.txt";
    private static final float NS2S = 1.0f / 1000000000.0f;
    private static final float EPSILON = 0.000001f;
    // In this example, alpha is calculated as t / (t + dT),
    // where t is the low-pass filter's time-constant and
    // dT is the event delivery rate.
    final float alpha = 0.8f;
    private final double[] deltaRotationVector = new double[4];
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mTemperature;
    private Sensor mPressure;
    private Sensor mLight;
    private Sensor mMagneticField;
    private Sensor mGravity;
    private Sensor mGyroscope;
    private float[] gravity;
    private float[] magnitude;
    private float[] linear_acceleration;
    private float celsius;
    private float lux;
    private float hPa;
    private float[] orientation;
    private float results[] = new float[3];
    private float timestamp;
    private float[] downTime;
    private float[] upTime;


    /**
     * Value giving the total velocity of the gyroscope (will be high, when the device is moving fast and low when
     * the device is standing still). This is usually a value between 0 and 10 for normal motion. Heavy shaking can
     * increase it to about 25. Keep in mind, that these values are time-depended, so changing the sampling rate of
     * the sensor will affect this value!
     */
    private double gyroscopeRotationVelocity = 0;
    private float RTmp[] = new float[9];
    private float Rot[] = new float[9];
    private float I[] = new float[9];


    private boolean mInitialized;
    private int count;
    private String logValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        registerSensors();


        final EditText textMessage = (EditText) findViewById(R.id.editText_key);
        textMessage.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                generateRandomKey();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        generateRandomKey();
    }

    /**
     * Registering all the required Sensors to the App
     */
    private void registerSensors() {
        //List<Sensor> sensorsList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);

        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);

        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);

        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);

        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * This method generates the Key to press on the screen to monitor
     * the key stocks
     */
    private void generateRandomKey() {
        Random rnd = new Random();
        int numLetters = 36;

        String randomLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        TextView generateKeyForTextView = (TextView) findViewById(R.id.textView_key);
        generateKeyForTextView.setText(String.valueOf(randomLetters.charAt(rnd.nextInt(randomLetters.length()))));
    }

    /**
     * When the User press/ touch the screen the event from OnTouchevent will be
     * triggering the onTouch method, the method will save the x,y coordinates
     * and accelerometer and orientation coordinates
     *
     * @param event
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        EditText keyValue = (EditText) findViewById(R.id.editText_key);

        if (keyValue.getText() != null
                && keyValue.getText().toString() != null
                && !"".equals(keyValue.getText().toString())) {

            StringBuilder keyStroke = new StringBuilder();

            if (logValues != null
                    && !"".equals(logValues)) {
                keyStroke.append(logValues);
            }

            //multi touch pointers
            // get pointer index from the event object
            int pointerIndex = event.getActionIndex();

            // get masked (not specific to a pointer) action
            int maskedAction = event.getActionMasked();

            switch (maskedAction) {

                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN: {
                    // We have a new pointer. Lets add it to the list of pointers
                    keyStroke.append(event.getX(pointerIndex)+","+event.getY(pointerIndex));
                    keyStroke.append(",downTime:"+event.getDownTime());
                    //Testing the Value of pointers by toast
                    Toast.makeText(MainActivity.this,
                            "X:" + event.getX(pointerIndex) + "Y:" + event.getY(pointerIndex), Toast.LENGTH_SHORT).show();
                    break;
                }

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL: {
                    keyStroke.append(",upTime:").append(event.getEventTime());
                    break;
                }
            }

            keyStroke.append("gravity:" + gravity[0] + "," + gravity[1] + "," + gravity[2] + ",");
            keyStroke.append("magnitude:" + magnitude[0] + "," + magnitude[1] + "," + magnitude[2] + ",");
            keyStroke.append("Accelerometer:" + linear_acceleration[0] + "," + linear_acceleration[1] + "," + linear_acceleration[2] + ",");
            keyStroke.append("orientation:" + orientation[0] + "," + orientation[1] + "," + orientation[2] + ",");
            keyStroke.append("Temperature:" + celsius + ",");
            keyStroke.append("Light:" + lux + ",");
            keyStroke.append("pressure:" + hPa + ",");

            logValues = keyStroke.toString();
            if (count == 50) {
                writeToFile(logValues);
                Toast.makeText(MainActivity.this,
                        "Key Stocks Saved",
                        Toast.LENGTH_SHORT).show();
                count = 0;
            } else {
                count++;
            }
        }
        generateRandomKey();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // Isolate the force of gravity with the low-pass filter.
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            // Remove the gravity contribution with the high-pass filter.
            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];

        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // Isolate the force of gravity with the low-pass filter.
            magnitude[0] = alpha * magnitude[0] + (1 - alpha) * event.values[0];
            magnitude[1] = alpha * magnitude[1] + (1 - alpha) * event.values[1];
            magnitude[2] = alpha * magnitude[2] + (1 - alpha) * event.values[2];

        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            // This timestep's delta rotation to be multiplied by the current rotation
            // after computing it from the gyro sample data.
            if (timestamp != 0) {
                final float dT = (event.timestamp - timestamp) * NS2S;
                // Axis of the rotation sample, not normalized yet.
                float axisX = event.values[0];
                float axisY = event.values[1];
                float axisZ = event.values[2];

                // Calculate the angular speed of the sample
                gyroscopeRotationVelocity = Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

                // Normalize the rotation vector if it's big enough to get the axis
                if (gyroscopeRotationVelocity > EPSILON) {
                    axisX /= gyroscopeRotationVelocity;
                    axisY /= gyroscopeRotationVelocity;
                    axisZ /= gyroscopeRotationVelocity;
                }

                // Integrate around this axis with the angular speed by the timestep
                // in order to get a delta rotation from this sample over the timestep
                // We will convert this axis-angle representation of the delta rotation
                // into a quaternion before turning it into the rotation matrix.
                double thetaOverTwo = gyroscopeRotationVelocity * dT / 2.0f;
                double sinThetaOverTwo = Math.sin(thetaOverTwo);
                double cosThetaOverTwo = Math.cos(thetaOverTwo);
                deltaRotationVector[0] = sinThetaOverTwo * axisX;
                deltaRotationVector[1] = sinThetaOverTwo * axisY;
                deltaRotationVector[2] = sinThetaOverTwo * axisZ;
                deltaRotationVector[3] = cosThetaOverTwo;
            }
            timestamp = event.timestamp;

        } else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            celsius = event.values[0];
        } else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            hPa = event.values[0];
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lux = event.values[0];
        } /*else if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            //Gravity is already measured in the Accelerometer
        } */ else {
            return;
        }

        if (gravity != null && magnitude != null) {
            SensorManager.getRotationMatrix(RTmp, I, gravity, magnitude);
            Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int rotation = display.getRotation();

            if (rotation == 1) {
                SensorManager.remapCoordinateSystem(RTmp, SensorManager.AXIS_X, SensorManager.AXIS_MINUS_Z, Rot);
            } else {
                SensorManager.remapCoordinateSystem(RTmp, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_Z, Rot);
            }

            SensorManager.getOrientation(Rot, results);
            //Azimuth is angle between the positive Y-axis and magnetic north
            // and its range is between 0 and 360 degrees.
            orientation[0] = (float) (((results[0] * 180) / Math.PI) + 180); //azimuth
            //Positive Roll is defined when the phone starts by laying flat
            // on a table and the positive Z-axis begins to tilt towards the positive X-axis.
            orientation[1] = (float) (((results[1] * 180 / Math.PI)) + 90); //pitch
            //Positive Pitch is defined when the phone starts by laying flat
            // on a table and the positive Z-axis begins to tilt towards the positive Y-axis.
            orientation[2] = (float) (((results[2] * 180 / Math.PI))); //roll
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();

        } catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }

    }

    private String readFromFile() {
        String ret = "";
        try {
            InputStream inputStream = openFileInput(FILENAME);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }

        return ret;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
