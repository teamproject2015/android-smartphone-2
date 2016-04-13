/**
 * 
 */
package de.unimannheim.loggingapp.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Saimadhav S
 *
 */
public class GenerateFeaturesFile {


	private static final String CLASS_NAME = GenerateFeaturesFile.class.getName();
	private static final String HEADERS = "Session;Sensor;System_nanoTime;Key;target;KeyXCoordinate;KeyYCoordinate;timeDuration;"
			+ "Sensor_XCoordinate_Mean;Sensor_XCoordinate_variance;Sensor_XCoordinate_stdDev;Sensor_XCoordinate_MinValue;Sensor_XCoordinate_MaxValue;"
			+ "Sensor_YCoordinate_Mean;Sensor_YCoordinate_variance;Sensor_YCoordinate_stdDev;Sensor_YCoordinate_MinValue;Sensor_YCoordinate_MaxValue;"
			+ "Sensor_ZCoordinate_Mean;Sensor_ZCoordinate_variance;Sensor_ZCoordinate_stdDev;Sensor_ZCoordinate_MinValue;Sensor_ZCoordinate_MaxValue";

	StringBuffer dataBuffer = new StringBuffer();
	String firstKey = "";
	String x = "", y = "";
	double x_mean, x_variance, x_minValue, x_maxValue;
	double y_mean, y_variance, y_minValue, y_maxValue;
	double z_mean, z_variance, z_minValue, z_maxValue;
	// double nanoTimeMean,nanoTimeVariance;
	long timeduration;
	double x_stdDev, y_stdDev, z_stdDev;
    File directory;


	ArrayList<Double> x_gravityCordinate = new ArrayList<>();
	ArrayList<Double> y_gravityCordinate = new ArrayList<>();
	ArrayList<Double> z_gravityCordinate = new ArrayList<>();
	ArrayList<Long> gravityNanoTime = new ArrayList<>();

	ArrayList<Double> x_gyroscopeCordinate = new ArrayList<>();
	ArrayList<Double> y_gyroscopeCordinate = new ArrayList<>();
	ArrayList<Double> z_gyroscopeCordinate = new ArrayList<>();
	ArrayList<Long> gyroscopeNanoTime = new ArrayList<>();

	ArrayList<Double> x_magnitudeCordinate = new ArrayList<>();
	ArrayList<Double> y_magnitudeCordinate = new ArrayList<>();
	ArrayList<Double> z_magnitudeCordinate = new ArrayList<>();
	ArrayList<Long> magnitudeNanoTime = new ArrayList<>();

	ArrayList<Double> x_accelerometerCordinate = new ArrayList<>();
	ArrayList<Double> y_accelerometerCordinate = new ArrayList<>();
	ArrayList<Double> z_accelerometerCordinate = new ArrayList<>();
	ArrayList<Long> accelerometerNanoTime = new ArrayList<>();


	/**
	 * @param args
	 */
	public void writeCSVFeaturesFile(String fileName) {

		File sdCard = Environment.getExternalStorageDirectory();
		directory = new File(sdCard.getAbsolutePath() + "/TouchLogger");

		String csvFile = fileName + ".csv";
		File file = new File(directory, csvFile);

		BufferedReader br = null;
		String cvsSplitBy = ";";

		try {

			br = new BufferedReader(new FileReader(file));
			// int count = 1;
			String next = null, line = br.readLine();
			int count = 1;
			for (boolean first = true, last = (line == null); !last; first = false, line = next) {
				last = ((next = br.readLine()) == null);
				// line = br.readLine();
                if(!line.contains(";") || line.contains("Sensor") || first) {
                    continue;
                }
				String[] keylogger = line.split(cvsSplitBy);
				String key = keylogger[2];
				if (last) {
					if (!"".equals(key)) {
						populateCoordinates(keylogger);
					}

					populateData(count,x_gravityCordinate, y_gravityCordinate, z_gravityCordinate, gravityNanoTime,
							"Gravity");
					populateData(count,x_gyroscopeCordinate, y_gyroscopeCordinate, z_gyroscopeCordinate, gyroscopeNanoTime,
							"Gyroscope");
					populateData(count,x_magnitudeCordinate, y_magnitudeCordinate, z_magnitudeCordinate, magnitudeNanoTime,
							"Magnitude");
					populateData(count,x_accelerometerCordinate, y_accelerometerCordinate, z_accelerometerCordinate,
							accelerometerNanoTime, "Accelerometer");

				} else if (!"".equals(key)) {

					if ("".equals(firstKey) || (firstKey).equals(key)) {

						if ("".equals(firstKey)) {
							firstKey = key;
							x = keylogger[3];
							y = keylogger[4];
						}

						populateCoordinates(keylogger);

					} else if (!(firstKey).equals(key)) {

						populateData(count,x_gravityCordinate, y_gravityCordinate, z_gravityCordinate, gravityNanoTime,
								"Gravity");
						populateData(count,x_gyroscopeCordinate, y_gyroscopeCordinate, z_gyroscopeCordinate,
								gyroscopeNanoTime, "Gyroscope");
						populateData(count,x_magnitudeCordinate, y_magnitudeCordinate, z_magnitudeCordinate,
								magnitudeNanoTime, "Magnitude");
						populateData(count,x_accelerometerCordinate, y_accelerometerCordinate, z_accelerometerCordinate,
								accelerometerNanoTime, "Accelerometer");

						firstKey = key;
						x = keylogger[3];
						y = keylogger[4];

						x_gravityCordinate.clear();
						y_gravityCordinate.clear();
						z_gravityCordinate.clear();
						gravityNanoTime.clear();

						x_gyroscopeCordinate.clear();
						y_gyroscopeCordinate.clear();
						z_gyroscopeCordinate.clear();
						gyroscopeNanoTime.clear();

						x_magnitudeCordinate.clear();
						y_magnitudeCordinate.clear();
						z_magnitudeCordinate.clear();
						magnitudeNanoTime.clear();

						x_accelerometerCordinate.clear();
						y_accelerometerCordinate.clear();
						z_accelerometerCordinate.clear();
						accelerometerNanoTime.clear();
						count++;
						populateCoordinates(keylogger);
					}
				}
			}

			writeToFile(dataBuffer.toString(), fileName);
			br.close();
		} catch (FileNotFoundException e) {
			Log.e(CLASS_NAME,"FileNotFoundException --->" + e.getMessage());
		} catch (IOException e) {
			Log.e(CLASS_NAME, "IOException --->" + e.getMessage());
		}

		Log.i(CLASS_NAME, "File generated Successfully");
	}

	private void populateCoordinates(String[] keylogger) {
		if ("Gravity".equals(keylogger[0])) {
			x_gravityCordinate.add(Double.valueOf(keylogger[5]));
			y_gravityCordinate.add(Double.valueOf(keylogger[6]));
			z_gravityCordinate.add(Double.valueOf(keylogger[7]));
			gravityNanoTime.add(Long.valueOf(keylogger[1]));

		} else if ("Gyroscope".equals(keylogger[0])) {
			x_gyroscopeCordinate.add(Double.valueOf(keylogger[5]));
			y_gyroscopeCordinate.add(Double.valueOf(keylogger[6]));
			z_gyroscopeCordinate.add(Double.valueOf(keylogger[7]));
			gyroscopeNanoTime.add(Long.valueOf(keylogger[1]));

		} else if ("Magnitude".equals(keylogger[0])) {
			x_magnitudeCordinate.add(Double.valueOf(keylogger[5]));
			y_magnitudeCordinate.add(Double.valueOf(keylogger[6]));
			z_magnitudeCordinate.add(Double.valueOf(keylogger[7]));
			magnitudeNanoTime.add(Long.valueOf(keylogger[1]));

		} else if ("Acceleration".equals(keylogger[0])) {
			x_accelerometerCordinate.add(Double.valueOf(keylogger[5]));
			y_accelerometerCordinate.add(Double.valueOf(keylogger[6]));
			z_accelerometerCordinate.add(Double.valueOf(keylogger[7]));
			accelerometerNanoTime.add(Long.valueOf(keylogger[1]));

		}
	}

	private void populateData(int count,ArrayList<Double> x_coordinate, ArrayList<Double> y_coordinate,
			ArrayList<Double> z_coordinate, ArrayList<Long> nanoTime, String name) {

        if (x_coordinate.size()==0 || y_coordinate.size()==0 || z_coordinate.size()==0){
            return;
        }
		x_mean = getMean(x_coordinate);
		x_variance = getVariance(x_coordinate, x_mean);
		x_stdDev = getStdDev(x_variance);
		Collections.sort(x_coordinate);
		x_minValue = x_coordinate.get(0);
		x_maxValue = x_coordinate.get(x_coordinate.size() - 1);

		y_mean = getMean(y_coordinate);
		y_variance = getVariance(y_coordinate, y_mean);
		y_stdDev = getStdDev(y_variance);
		Collections.sort(y_coordinate);
		y_minValue = y_coordinate.get(0);
		y_maxValue = y_coordinate.get(y_coordinate.size() - 1);

		z_mean = getMean(z_coordinate);
		z_variance = getVariance(z_coordinate, z_mean);
		z_stdDev = getStdDev(z_variance);
		Collections.sort(z_coordinate);
		z_minValue = z_coordinate.get(0);
		z_maxValue = z_coordinate.get(z_coordinate.size() - 1);

		Collections.sort(nanoTime);
		timeduration = nanoTime.get(nanoTime.size() - 1) - nanoTime.get(0);

		dataBuffer.append(count).append(";").append(name).append(";").append(System.nanoTime()).append(";").append(firstKey).append(";").append(";").append(x)
				.append(";").append(y).append(";").append(timeduration).append(";").append(x_mean).append(";")
				.append(x_variance).append(";").append(x_stdDev).append(";").append(x_minValue).append(";")
				.append(x_maxValue).append(";").append(y_mean).append(";").append(y_variance).append(";")
				.append(y_stdDev).append(";").append(y_minValue).append(";").append(y_maxValue).append(";")
				.append(z_mean).append(";").append(z_variance).append(";").append(z_stdDev).append(";")
				.append(z_minValue).append(";").append(z_maxValue).append(";").append("\r\n");

	}

	/**
	 * Writing the Data to File to ExternalStorageDirectory
	 *
	 * @param data
	 *            data which contains the Logger values
	 */
	private boolean writeToFile(String data, String fileName) {
		// Log.i(CLASS_NAME,"data--------->"+data);
		// boolean isNewFile = false;
		boolean isFinished = false;
		try {

			File file = new File(directory,fileName + "_withfeatures.csv");
			FileOutputStream fos;
			if (file.exists()) {
				file.delete();
			}
			try {
				fos = new FileOutputStream(file, true);
				// System.out.println(CLASS_NAME, "Data is append to the File "
				// + fileName);
			} catch (FileNotFoundException fileNotFoundException) {
				fos = new FileOutputStream(file);
				// System.out.println(CLASS_NAME, "File " + fileName + " is
				// created with new data");
			}
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
			outputStreamWriter.write(HEADERS + "\r\n" + data);
			outputStreamWriter.close();
			isFinished = true;

		} catch (IOException e) {
			System.out.println("File write failed: " + e.toString());
		}
		return isFinished;
	}

	/**
	 * get the means for the given Arraylist
	 *
	 * @param data arraylist
	 * @return
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
	 * @return
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

	private static double getStdDev(double variance) {
		return Math.sqrt(variance);
	}

}
