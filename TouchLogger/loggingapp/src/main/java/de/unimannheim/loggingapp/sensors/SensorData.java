package de.unimannheim.loggingapp.sensors;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by D064747 on 20.11.2015.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class SensorData {
    private long timestamp;
    private float x;
    private float y;
    private float z;
}
