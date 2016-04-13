package de.unimannheim.backgroundservices;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.unimannheim.game.R;
import de.unimannheim.sensors.AccelerometerService;
import de.unimannheim.sensors.GravityService;
import de.unimannheim.sensors.GyroscopeService;
import de.unimannheim.sensors.LightResultReceiver;
import de.unimannheim.sensors.LightService;
import de.unimannheim.sensors.MagnitudeService;
import de.unimannheim.sensors.OrientationService;
import de.unimannheim.sensors.PressureResultReceiver;
import de.unimannheim.sensors.PressureService;
import de.unimannheim.sensors.RotationVectorService;
import de.unimannheim.sensors.SensorResultReceiver;
import de.unimannheim.utils.Constants;

/**
 * Created by Saimadhav S on 06.03.2016.
 * <p/>
 * This IntentService class runs in background for logging
 */
public class BackgroundService extends Service implements View.OnTouchListener {

    public static final int SENSOR_DELAY = SensorManager.SENSOR_DELAY_GAME;
    public static final int LEG_MOVEMENT_NONE = 0;
    public static final int LEG_MOVEMENT_WALKING = 1;
    protected static final float LEG_THRSHOLD_AMPLITUDE = 1.0f;
    protected static final int LEG_THRSHOLD_INACTIVITY = 10;
    private static final String CLASS_NAME = "BackgroundService";
    private static final String FILENAME = "KeyboardInputLogging";

    private final IBinder mBinder = new MyBinder();
    protected SensorResultReceiver accelerometerResultReceiver;
    protected SensorResultReceiver orientationResultReceiver;
    protected SensorResultReceiver gyroscopeResultReceiver;
    protected SensorResultReceiver gravityResultReceiver;
    protected PressureResultReceiver pressureResultReceiver;
    protected LightResultReceiver lightResultReceiver;
    protected SensorResultReceiver magnitudeResultReceiver;
    protected SensorResultReceiver rotationVectorResultReceiver;
    protected String pressedKey;
    protected String csvFileName;
    protected int id = 0;
    ArrayList<Double> x_gyroscopeCordinate = new ArrayList<>();
    ArrayList<Double> y_gyroscopeCordinate = new ArrayList<>();
    ArrayList<Double> z_gyroscopeCordinate = new ArrayList<>();
    double x_mean, x_variance, x_minValue, x_maxValue;
    double y_mean, y_variance, y_minValue, y_maxValue;
    double z_mean, z_variance, z_minValue, z_maxValue;
    private WindowManager mWindowManager;
    private View myView;
    private long lastSystemTime;
    private boolean toBeRecorded;
    private float mLastZ;
    private int mInactiveMovementCount = 0;
    private int mLastMovement = LEG_MOVEMENT_NONE;
    private ScalarKalmanFilter[] mFiltersCascade = new ScalarKalmanFilter[3];

    /**
     * get the means for the given Arraylist
     *
     * @param data arraylist
     * @return double
     */
    private static double getMean(ArrayList<Double> data) {
        int size = data.size();
        double sum = 0;

        if (size == 1) {
            return data.iterator().next();
        } else {
            for (double a : data) {
                sum += a;
            }
            return sum / size;
        }

    }

    /**
     * get the variancee for the given Arraylist
     *
     * @param data arraylist
     * @param mean mean value which is generated
     * @return double
     */
    private static double getVariance(ArrayList<Double> data, double mean) {
        // double mean = getMean();
        int size = data.size();
        double temp = 0;

        if (size == 1) {
            double a = data.iterator().next();
            temp = (mean - a) * (mean - a);
        } else {
            for (double a : data) {
                temp += (mean - a) * (mean - a);
            }
        }
        return temp / size;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.i("BackgroundService", "BackgroundService started");

        pressedKey = "";
        csvFileName = FILENAME;

        lastSystemTime = System.nanoTime();

        //registering the sensors
        registerSensors();

        //Starting the services by using startservice method
        startServices();

        //isKeyboardOpen();

        return START_STICKY;
    }


    private void isKeyboardOpen() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams")
        final View activityRootView = inflater.inflate(R.layout.overlay, null);

        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                activityRootView.getWindowVisibleDisplayFrame(r);
                Log.i("Keyboard--->", String.valueOf(activityRootView.getRootView().getHeight()) + "get height " + (r.bottom - r.top));
                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
                    Log.i("isKeyboardOpen--->", "Keyboard opened");
                } else {
                    Log.i("isKeyboardOpen--->", "Keyboard closed");
                }
            }
        });
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Log.i("Ontouchevent","Screen is touch, event.action="+event.getAction());
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            //Log.i("Ontouchevent","System time="+System.nanoTime());
            if (!toBeRecorded) {
                lastSystemTime = System.nanoTime();
            }
            toBeRecorded = true;
        }
        return false;
    }


    /**
     * Registering all the required Sensors to the App
     */
    protected void registerSensors() {

        Handler handler = new Handler();

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < sensorList.size(); i++) {
            int type = sensorList.get(i).getType();
            if (type == Sensor.TYPE_ACCELEROMETER) {
                accelerometerResultReceiver = new SensorResultReceiver(handler);
                accelerometerResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_GYROSCOPE) {
                gyroscopeResultReceiver = new SensorResultReceiver(handler);
                gyroscopeResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
                magnitudeResultReceiver = new SensorResultReceiver(handler);
                magnitudeResultReceiver.setReceiver(new SensorDataReceiver());

            } else if (type == Sensor.TYPE_ROTATION_VECTOR) {
                rotationVectorResultReceiver = new SensorResultReceiver(handler);
                rotationVectorResultReceiver.setReceiver(new SensorDataReceiver());
            }
        }

    }


    public void startServices() {

        // start accelerator service
        Intent accIntent = new Intent(this, AccelerometerService.class);
        accIntent.putExtra(Constants.EXTRA_RECEIVER, accelerometerResultReceiver);
        startService(accIntent);

        // start gyroscope service
        Intent gyroscopeIntent = new Intent(this, GyroscopeService.class);
        gyroscopeIntent.putExtra(Constants.EXTRA_RECEIVER, gyroscopeResultReceiver);
        startService(gyroscopeIntent);

        // start magnitude service
        Intent magnitudeIntent = new Intent(this, MagnitudeService.class);
        magnitudeIntent.putExtra(Constants.EXTRA_RECEIVER, magnitudeResultReceiver);
        startService(magnitudeIntent);

        // start Rotation Vector service
        Intent rotationVectorIntent = new Intent(this, RotationVectorService.class);
        rotationVectorIntent.putExtra(Constants.EXTRA_RECEIVER, rotationVectorResultReceiver);
        startService(rotationVectorIntent);
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

        Log.i("onCreate---->", "entered");
        //mask that cover the entire screen takes the touch events
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        myView = inflater.inflate(R.layout.overlay, null);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                1, /* width */
                1, /* height */
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.START | Gravity.TOP;

        myView.setOnTouchListener(this);
        mWindowManager.addView(myView, params);

        //for sampling the noise the accelerometer
        mFiltersCascade[0] = new ScalarKalmanFilter(1, 1, 0.01f, 0.0025f);
        mFiltersCascade[1] = new ScalarKalmanFilter(1, 1, 0.01f, 0.0025f);
        mFiltersCascade[2] = new ScalarKalmanFilter(1, 1, 0.01f, 0.0025f);

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
        //calling the stop method
        stopService();

        if (mWindowManager != null) {
            if (myView != null) mWindowManager.removeView(myView);
        }
        super.onDestroy();
    }

    /**
     * disabling all the sensor services
     */
    private void stopService() {
        //disabling all the sensor services
        if (accelerometerResultReceiver != null)
            stopService(new Intent(this, AccelerometerService.class));
        if (orientationResultReceiver != null)
            stopService(new Intent(this, OrientationService.class));
        if (gyroscopeResultReceiver != null)
            stopService(new Intent(this, GyroscopeService.class));
        if (gravityResultReceiver != null)
            stopService(new Intent(this, GravityService.class));
        if (pressureResultReceiver != null)
            stopService(new Intent(this, PressureService.class));
        if (lightResultReceiver != null)
            stopService(new Intent(this, LightService.class));
        if (magnitudeResultReceiver != null)
            stopService(new Intent(this, MagnitudeService.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * Writing the Data to File to ExternalStorageDirectory
     *
     * @param data data which contains the Logger values
     */
    protected boolean writeToFile(String data, String fileName) {
        //Log.i(CLASS_NAME,"data--------->"+data);
        boolean isNewFile = false;
        boolean isFinished = false;
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            //Log.i(CLASS_NAME, "sdCard-->" + sdCard.getAbsolutePath());
            File directory = new File(sdCard.getAbsolutePath() + "/TypePraxis");
            directory.mkdirs();
            File file = new File(directory, fileName);
            FileOutputStream fos;
            if (!file.exists()) {
                isNewFile = true;
            }
            try {
                fos = new FileOutputStream(file, true);
                //Log.d(CLASS_NAME, "Data is append to the File " + fileName);
            } catch (FileNotFoundException fileNotFou0ndException) {
                fos = new FileOutputStream(file);
                //Log.d(CLASS_NAME, "File " + fileName + " is created with new data");
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);

           /* if (isNewFile) {
                outputStreamWriter.write(getString(R.string.file_headerfields) + "\r\n" + data);
            } else {
                outputStreamWriter.write(data);
            }*/
            outputStreamWriter.close();
            isFinished = true;

        } catch (IOException e) {
            Log.e(CLASS_NAME, "File write failed: " + e.toString());
        }
        return isFinished;
    }

    /**
     * reading the Data from ExternalStorageDirectory to File
     *
     * @param fileName data which contains the Logger values
     */
    protected String readFromFile(String fileName) {
        String ret = "";
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e(CLASS_NAME, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(CLASS_NAME, "Can not read file: " + e.toString());
        }

        return ret;
    }

    /**
     * Smoothes the signal from accelerometer
     */
    private float filter(float measurement) {
        float f1 = mFiltersCascade[0].correct(measurement);
        float f2 = mFiltersCascade[1].correct(f1);
        float f3 = mFiltersCascade[2].correct(f2);
        return f3;
    }

    public class MyBinder extends Binder {
        BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    /**
     * SensorDataReceiver gets the data from service class to current class
     */
    private class SensorDataReceiver implements
            SensorResultReceiver.Receiver {

        public SensorDataReceiver() {
        }

        @Override
        public void newEvent(int sensorType, long timeStamp, float x, float y, float z) {

            if (sensorType == Sensor.TYPE_ACCELEROMETER) {

                final float zaxis = filter(z);
                if (Math.abs(zaxis - mLastZ) > LEG_THRSHOLD_AMPLITUDE) {
                    mInactiveMovementCount = 0;

                    if(zaxis != mLastZ) {
                        int currentMovement = LEG_MOVEMENT_WALKING;
                        if (currentMovement != mLastMovement) {
                            mLastMovement = currentMovement;
                        }
                    }
                } else {
                    if (mInactiveMovementCount > LEG_THRSHOLD_INACTIVITY) {
                        if (mLastMovement != LEG_MOVEMENT_NONE) {
                            mLastMovement = LEG_MOVEMENT_NONE;
                        }
                    } else {
                        mInactiveMovementCount++;
                    }
                }
                mLastZ = zaxis;

            } else if (sensorType == Sensor.TYPE_GYROSCOPE) {
                //Log.i("Sensor","toBeRecorded="+toBeRecorded+",x="+x+"y="+y+",z="+z);
                if (toBeRecorded) {
                    x_gyroscopeCordinate.add((double) x);
                    y_gyroscopeCordinate.add((double) y);
                    z_gyroscopeCordinate.add((double) z);
                    //Log.i("Sensor", "gyroscopeCordinate size" + z_gyroscopeCordinate.size());
                }

                if (toBeRecorded && lastSystemTime > 0 && (System.nanoTime() - lastSystemTime > 200000000)) {
                    //Log.i("Sensor", "lastSystemTime=" + lastSystemTime + ",currentTime=" +
                    // System.nanoTime() + ",Duration=" + (System.nanoTime() - lastSystemTime));
                    SaveTextFile saveTextFile = new SaveTextFile(mLastMovement);
                    saveTextFile.execute(x_gyroscopeCordinate, y_gyroscopeCordinate, z_gyroscopeCordinate);
                    lastSystemTime = 0;
                    toBeRecorded = false;
                }

            }
        }

        @Override
        public void error(String error) {
        }
    }



    /**
     * SaveTextFile class runs in the background for saving the text file
     */
    private class SaveTextFile extends AsyncTask<ArrayList<Double>, Void, Boolean> {

        private int  mMovement;

        SaveTextFile(int mMovement){
            this.mMovement = mMovement;
        }
        @Override
        protected Boolean doInBackground(ArrayList<Double>... params) {

            String letter = populateData(params[0], params[1], params[2]);
            x_gyroscopeCordinate.clear();
            y_gyroscopeCordinate.clear();
            z_gyroscopeCordinate.clear();
            String fileName = "inputrecording";

            if(mMovement == LEG_MOVEMENT_NONE) {
                fileName +="_sitting.txt";

            } else if(mMovement == LEG_MOVEMENT_WALKING) {
                fileName +="_walking.txt";
            }
            return writeToFile(letter, fileName);
        }


        /**
         * populate the data
         *
         * @param x_coordinate x coordinate values
         * @param y_coordinate y coordinate values
         * @param z_coordinate z coordinate values
         * @return string
         */
        private String populateData(ArrayList<Double> x_coordinate, ArrayList<Double> y_coordinate,
                                    ArrayList<Double> z_coordinate) {

            int orientation = getResources().getConfiguration().orientation;

            String letter = "";
            if (x_coordinate.size() == 0 || y_coordinate.size() == 0 || z_coordinate.size() == 0) {
                return "";
            }
            x_mean = getMean(x_coordinate);
            x_variance = getVariance(x_coordinate, x_mean);
            Collections.sort(x_coordinate);
            x_minValue = x_coordinate.get(0);
            x_maxValue = x_coordinate.get(x_coordinate.size() - 1);

            y_mean = getMean(y_coordinate);
            y_variance = getVariance(y_coordinate, y_mean);
            Collections.sort(y_coordinate);
            y_minValue = y_coordinate.get(0);
            y_maxValue = y_coordinate.get(y_coordinate.size() - 1);

            z_mean = getMean(z_coordinate);
            z_variance = getVariance(z_coordinate, z_mean);
            Collections.sort(z_coordinate);
            z_minValue = z_coordinate.get(0);
            z_maxValue = z_coordinate.get(z_coordinate.size() - 1);

            //TODO need to implement POJO class for the variables
            if(orientation==1) {
                letter = TemplateLandscape.getCharacter(x_variance, y_variance, z_variance,
                        x_mean, y_mean, z_mean,
                        x_minValue, x_maxValue,
                        y_minValue, y_maxValue,
                        z_minValue, z_maxValue);

            }else if(orientation==2) {
                letter = Template.getCharacter(x_variance, y_variance, z_variance,
                        x_mean, y_mean, z_mean,
                        x_minValue, x_maxValue,
                        y_minValue, y_maxValue,
                        z_minValue, z_maxValue);
            }

            Log.i("PopulateData", "Generated Letter =" + letter);
            return letter;

        }
    }
}
