package de.unimannheim.backgroundservices;

/**
 * Created by Saimadhav S on 07.04.2016.
 */
public class TemplateLandscape {

    public static String getCharacter(double sensor_XCoordinate_variance, double sensor_YCoordinate_variance, double sensor_ZCoordinate_variance,
                                      double sensor_XCoordinate_Mean, double sensor_YCoordinate_Mean, double sensor_ZCoordinate_Mean,
                                      double sensor_XCoordinate_MinValue, double sensor_XCoordinate_MaxValue,
                                      double sensor_YCoordinate_MinValue, double sensor_YCoordinate_MaxValue,
                                      double sensor_ZCoordinate_MinValue, double sensor_ZCoordinate_MaxValue) {


        if (sensor_ZCoordinate_MaxValue > 0.057 && sensor_XCoordinate_MinValue > -0.006 && sensor_XCoordinate_MinValue <= 0.002) {
            return "h";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.033 && sensor_XCoordinate_MaxValue <= 0.095
                && sensor_ZCoordinate_Mean <= 0.021 && sensor_XCoordinate_Mean > 0.036 && sensor_YCoordinate_MinValue > -0.118
                && sensor_XCoordinate_variance <= 0.000 && sensor_YCoordinate_Mean <= -0.056) {
            return "r";
        }
        ;
        if (sensor_XCoordinate_MaxValue > 0.117 && sensor_XCoordinate_variance > 0.006 && sensor_ZCoordinate_Mean <= 0.012 && sensor_YCoordinate_variance > 0.006) {
            return "z";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.079 && sensor_XCoordinate_variance > 0.001 && sensor_YCoordinate_variance <= 0.001 && sensor_ZCoordinate_Mean <= 0.019 && sensor_YCoordinate_MinValue > -0.056) {
            return "i";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.069 && sensor_XCoordinate_MinValue <= -0.015 && sensor_YCoordinate_Mean <= -0.002 && sensor_XCoordinate_MaxValue > 0.054 && sensor_YCoordinate_MinValue > -0.060 && sensor_ZCoordinate_Mean <= 0.021 && sensor_XCoordinate_variance > 0.001) {
            return "o";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.025 && sensor_ZCoordinate_Mean > 0.017 && sensor_YCoordinate_Mean > -0.006 && sensor_YCoordinate_MinValue <= -0.079 && sensor_XCoordinate_variance > 0.001 && sensor_XCoordinate_variance <= 0.002) {
            return "n";
        }
        ;
        if (sensor_ZCoordinate_Mean <= 0.011 && sensor_YCoordinate_Mean > -0.015 && sensor_XCoordinate_Mean > 0.026 && sensor_XCoordinate_variance <= 0.000) {
            return "p";
        }
        ;
        if (sensor_YCoordinate_variance > 0.002 && sensor_XCoordinate_MaxValue <= 0.089 && sensor_ZCoordinate_MinValue > 0.009 && sensor_YCoordinate_MaxValue <= 0.021 && sensor_XCoordinate_MinValue <= 0.015) {
            return "c";
        }
        ;
        if (sensor_YCoordinate_MinValue <= -0.112 && sensor_YCoordinate_Mean > -0.069 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_MinValue <= 0.004 && sensor_ZCoordinate_MinValue > 0.002 && sensor_XCoordinate_variance > 0.000 && sensor_XCoordinate_Mean > 0.023) {
            return "f";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.049 && sensor_ZCoordinate_Mean > 0.019 && sensor_XCoordinate_MaxValue <= 0.109 && sensor_YCoordinate_variance > 0.001 && sensor_XCoordinate_variance <= 0.000 && sensor_XCoordinate_Mean > 0.052) {
            return "d";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.032 && sensor_XCoordinate_MinValue <= -0.007 && sensor_ZCoordinate_Mean > 0.016 && sensor_YCoordinate_MaxValue <= 0.030 && sensor_XCoordinate_Mean <= 0.017 && sensor_ZCoordinate_Mean <= 0.018) {
            return "k";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.022 && sensor_YCoordinate_MaxValue > -0.015 && sensor_YCoordinate_MaxValue <= 0.006 && sensor_XCoordinate_Mean <= 0.051 && sensor_ZCoordinate_variance > 0.000) {
            return "y";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.013 && sensor_YCoordinate_MaxValue <= -0.029 && sensor_YCoordinate_MinValue > -0.129 && sensor_YCoordinate_variance > 0.000) {
            return "t";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.052 && sensor_YCoordinate_Mean <= -0.077 && sensor_ZCoordinate_Mean <= 0.021 && sensor_XCoordinate_variance <= 0.002 && sensor_XCoordinate_variance > 0.000 && sensor_YCoordinate_MinValue > -0.180) {
            return "e";
        }
        ;
        if (sensor_ZCoordinate_MinValue <= -0.015 && sensor_XCoordinate_MinValue > -0.022 && sensor_YCoordinate_variance <= 0.000) {
            return "h";
        }
        ;
        if (sensor_ZCoordinate_Mean > 0.018 && sensor_YCoordinate_MinValue <= -0.093 && sensor_YCoordinate_MaxValue > 0.039 && sensor_XCoordinate_MaxValue <= 0.072 && sensor_XCoordinate_Mean > 0.025 && sensor_YCoordinate_MinValue > -0.121 && sensor_YCoordinate_Mean <= -0.015) {
            return "v";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.027 && sensor_XCoordinate_MaxValue > 0.124 && sensor_XCoordinate_Mean <= 0.049 && sensor_XCoordinate_Mean <= 0.037) {
            return "q";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.051 && sensor_ZCoordinate_Mean > 0.016 && sensor_YCoordinate_MaxValue > 0.007 && sensor_XCoordinate_Mean <= 0.016 && sensor_YCoordinate_MaxValue > 0.055 && sensor_YCoordinate_MaxValue <= 0.085) {
            return "m";
        }
        ;
        if (sensor_YCoordinate_MaxValue <= 0.013 && sensor_XCoordinate_MinValue <= -0.038 && sensor_ZCoordinate_MaxValue <= 0.035 && sensor_XCoordinate_MaxValue > 0.089 && sensor_XCoordinate_MinValue > -0.098) {
            return "a";
        }
        ;
        if (sensor_ZCoordinate_MaxValue <= 0.014 && sensor_XCoordinate_MaxValue <= 0.021 && sensor_XCoordinate_Mean <= -0.012) {
            return "g";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.022 && sensor_ZCoordinate_MinValue > -0.003 && sensor_ZCoordinate_MinValue <= 0.006 && sensor_XCoordinate_MinValue <= -0.011 && sensor_ZCoordinate_Mean > 0.014 && sensor_ZCoordinate_MinValue <= 0.002 && sensor_XCoordinate_variance > 0.001) {
            return "j";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.006 && sensor_XCoordinate_Mean > 0.020 && sensor_ZCoordinate_MaxValue <= 0.031 && sensor_YCoordinate_MinValue <= -0.062 && sensor_ZCoordinate_Mean <= 0.017) {
            return "b";
        }
        ;
        if (sensor_YCoordinate_variance > 0.005 && sensor_ZCoordinate_MaxValue <= 0.035 && sensor_XCoordinate_MinValue <= -0.036 && sensor_XCoordinate_MaxValue <= 0.099 && sensor_XCoordinate_variance > 0.002) {
            return "x";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.033 && sensor_XCoordinate_MaxValue <= 0.095 && sensor_ZCoordinate_Mean <= 0.020 && sensor_XCoordinate_Mean > 0.027 && sensor_XCoordinate_MaxValue > 0.072 && sensor_XCoordinate_MinValue <= -0.007 && sensor_ZCoordinate_MaxValue <= 0.037 && sensor_XCoordinate_variance <= 0.002 && sensor_YCoordinate_MinValue <= -0.130) {
            return "r";
        }
        ;
        if (sensor_YCoordinate_MaxValue <= -0.021 && sensor_XCoordinate_variance > 0.002 && sensor_ZCoordinate_MaxValue <= 0.036 && sensor_ZCoordinate_MinValue > -0.000 && sensor_XCoordinate_variance > 0.002 && sensor_XCoordinate_MinValue <= 0.010) {
            return "w";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.018 && sensor_XCoordinate_MaxValue > 0.082 && sensor_YCoordinate_variance > 0.002 && sensor_XCoordinate_MaxValue <= 0.091 && sensor_XCoordinate_Mean > 0.044) {
            return "h";
        }
        ;
        if (sensor_XCoordinate_MaxValue > 0.082 && sensor_XCoordinate_variance > 0.004 && sensor_YCoordinate_MaxValue <= 0.040 && sensor_YCoordinate_Mean > -0.109 && sensor_ZCoordinate_Mean <= 0.022 && sensor_YCoordinate_variance > 0.001) {
            return "z";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.025 && sensor_ZCoordinate_MaxValue > 0.055 && sensor_YCoordinate_Mean > -0.136) {
            return "y";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.059 && sensor_ZCoordinate_Mean > 0.017 && sensor_ZCoordinate_MinValue <= 0.008 && sensor_ZCoordinate_variance <= 0.000) {
            return "l";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.012 && sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_MinValue <= -0.077 && sensor_XCoordinate_MinValue > -0.015 && sensor_YCoordinate_Mean > -0.047 && sensor_XCoordinate_Mean > 0.015 && sensor_ZCoordinate_MaxValue <= 0.044) {
            return "c";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.031 && sensor_XCoordinate_MaxValue <= 0.091 && sensor_YCoordinate_Mean <= -0.081 && sensor_YCoordinate_MaxValue > -0.018 && sensor_YCoordinate_MaxValue <= -0.002 && sensor_ZCoordinate_variance <= 0.000) {
            return "g";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.025 && sensor_YCoordinate_variance > 0.002 && sensor_XCoordinate_MaxValue <= 0.062 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_Mean > 0.007 && sensor_ZCoordinate_Mean <= 0.019) {
            return "n";
        }
        ;
        if (sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_MaxValue > 0.067 && sensor_XCoordinate_MinValue > -0.016) {
            return "u";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.031 && sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_variance <= 0.000) {
            return "f";
        }
        ;
        if (sensor_XCoordinate_MaxValue > 0.095 && sensor_XCoordinate_MaxValue <= 0.109 && sensor_YCoordinate_Mean <= -0.058 && sensor_YCoordinate_MaxValue > 0.045) {
            return "d";
        }
        ;
        if (sensor_YCoordinate_MinValue <= -0.104 && sensor_XCoordinate_Mean <= 0.048 && sensor_XCoordinate_variance > 0.002 && sensor_YCoordinate_variance > 0.004 && sensor_YCoordinate_variance <= 0.005 && sensor_XCoordinate_Mean > 0.002) {
            return "v";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.040 && sensor_ZCoordinate_variance <= 0.000 && sensor_XCoordinate_MinValue <= 0.017 && sensor_XCoordinate_MinValue > 0.008 && sensor_XCoordinate_Mean <= 0.042) {
            return "s";
        }
        ;
        if (sensor_ZCoordinate_MaxValue > 0.061 && sensor_YCoordinate_variance > 0.003 && sensor_XCoordinate_MinValue <= -0.017) {
            return "h";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.031 && sensor_ZCoordinate_Mean > 0.021 && sensor_XCoordinate_Mean <= 0.026 && sensor_XCoordinate_variance > 0.001 && sensor_XCoordinate_variance <= 0.002) {
            return "k";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.052 && sensor_XCoordinate_MaxValue <= 0.099 && sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_MaxValue <= 0.038 && sensor_XCoordinate_MaxValue > 0.047) {
            return "y";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.047 && sensor_YCoordinate_MinValue <= -0.173 && sensor_ZCoordinate_Mean <= 0.020 && sensor_XCoordinate_Mean <= 0.095 && sensor_YCoordinate_variance > 0.007 && sensor_ZCoordinate_Mean > 0.011 && sensor_XCoordinate_MinValue > -0.003) {
            return "e";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.044 && sensor_XCoordinate_variance <= 0.000 && sensor_YCoordinate_MinValue > -0.129 && sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_Mean <= 0.024) {
            return "t";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.038 && sensor_XCoordinate_MinValue > -0.007 && sensor_ZCoordinate_MinValue <= -0.004 && sensor_ZCoordinate_Mean <= 0.009 && sensor_ZCoordinate_variance > 0.000) {
            return "p";
        }
        ;
        if (sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_MinValue > -0.030 && sensor_ZCoordinate_Mean <= 0.011 && sensor_YCoordinate_MinValue <= -0.181 && sensor_XCoordinate_Mean <= 0.076) {
            return "b";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.035 && sensor_ZCoordinate_MaxValue <= 0.034 && sensor_YCoordinate_MinValue > -0.096 && sensor_ZCoordinate_MinValue <= 0.008 && sensor_XCoordinate_Mean > 0.028 && sensor_YCoordinate_variance <= 0.001) {
            return "r";
        }
        ;
        if (sensor_XCoordinate_variance <= 0.000 && sensor_YCoordinate_Mean <= -0.055 && sensor_YCoordinate_Mean > -0.074 && sensor_YCoordinate_variance > 0.001) {
            return "h";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.051 && sensor_ZCoordinate_Mean > 0.013 && sensor_YCoordinate_MaxValue > 0.003 && sensor_ZCoordinate_MaxValue <= 0.027 && sensor_XCoordinate_Mean <= 0.016) {
            return "m";
        }
        ;
        if (sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_MinValue <= 0.007 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_Mean > 0.022) {
            return "a";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.016 && sensor_ZCoordinate_MinValue > 0.002 && sensor_ZCoordinate_MinValue <= 0.006 && sensor_YCoordinate_Mean > -0.000 && sensor_YCoordinate_variance <= 0.004 && sensor_YCoordinate_Mean <= 0.021) {
            return "j";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.079 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_MaxValue > 0.051 && sensor_XCoordinate_Mean <= 0.030 && sensor_XCoordinate_MinValue > -0.048 && sensor_ZCoordinate_Mean <= 0.012) {
            return "i";
        }
        ;
        if (sensor_YCoordinate_variance <= 0.001 && sensor_XCoordinate_MinValue <= -0.011 && sensor_XCoordinate_MinValue <= -0.033 && sensor_ZCoordinate_MinValue > 0) {
            return "o";
        }
        ;
        if (sensor_YCoordinate_MinValue <= -0.190 && sensor_ZCoordinate_MaxValue <= 0.025 && sensor_XCoordinate_variance > 0.001 && sensor_XCoordinate_MinValue > -0.014 && sensor_YCoordinate_MaxValue > -0.044) {
            return "s";
        }
        ;
        if (sensor_ZCoordinate_MinValue <= -0.018 && sensor_ZCoordinate_MinValue <= -0.038 && sensor_XCoordinate_MinValue > -0.068) {
            return "u";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.073 && sensor_ZCoordinate_MaxValue > 0.040 && sensor_ZCoordinate_MaxValue <= 0.042 && sensor_XCoordinate_MinValue > 0.003) {
            return "d";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.061 && sensor_ZCoordinate_MaxValue > 0.029 && sensor_YCoordinate_MinValue > -0.113 && sensor_YCoordinate_MinValue <= -0.068 && sensor_ZCoordinate_MaxValue > 0.040 && sensor_XCoordinate_MaxValue <= 0.054 && sensor_ZCoordinate_Mean <= 0.042) {
            return "l";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.021 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_MinValue <= -0.004 && sensor_ZCoordinate_MaxValue > 0.026 && sensor_YCoordinate_variance > 0.004) {
            return "c";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.025 && sensor_XCoordinate_MaxValue <= 0.104 && sensor_XCoordinate_MaxValue > 0.081 && sensor_ZCoordinate_MinValue > 0.013 && sensor_ZCoordinate_variance > 0.000) {
            return "g";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.006 && sensor_XCoordinate_variance <= 0.001 && sensor_XCoordinate_MaxValue > 0.045 && sensor_ZCoordinate_Mean <= 0.018 && sensor_XCoordinate_MaxValue <= 0.070) {
            return "b";
        }
        ;
        if (sensor_XCoordinate_MaxValue > 0.079 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_Mean <= 0.050) {
            return "z";
        }
        ;
        if (sensor_YCoordinate_MaxValue > 0.036 && sensor_YCoordinate_MaxValue <= 0.046 && sensor_ZCoordinate_MaxValue <= 0.036 && sensor_ZCoordinate_MinValue <= -0.006 && sensor_YCoordinate_Mean <= -0.014) {
            return "f";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.056 && sensor_XCoordinate_Mean <= 0.042 && sensor_ZCoordinate_Mean > 0.018 && sensor_XCoordinate_Mean > 0.015 && sensor_YCoordinate_variance > 0.001 && sensor_XCoordinate_MinValue <= -0.002) {
            return "y";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.062 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_MinValue <= 0 && sensor_ZCoordinate_MinValue > -0.005 && sensor_YCoordinate_MaxValue <= -0.010 && sensor_XCoordinate_Mean > 0.067) {
            return "w";
        }
        ;
        if (sensor_YCoordinate_variance > 0.001 && sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_Mean > -0.027 && sensor_XCoordinate_MinValue > 0.014) {
            return "x";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.022 && sensor_XCoordinate_Mean > 0.017 && sensor_YCoordinate_MinValue <= -0.104 && sensor_ZCoordinate_MaxValue <= 0.025) {
            return "h";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.051 && sensor_ZCoordinate_MinValue > 0.008 && sensor_XCoordinate_MaxValue > 0.040 && sensor_XCoordinate_MaxValue <= 0.046 && sensor_XCoordinate_variance > 0.000 && sensor_YCoordinate_MaxValue <= 0.053) {
            return "m";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.052 && sensor_YCoordinate_MinValue <= -0.194 && sensor_XCoordinate_Mean <= 0.095 && sensor_XCoordinate_MaxValue > 0.131 && sensor_XCoordinate_Mean <= 0.081) {
            return "e";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.045 && sensor_XCoordinate_MaxValue <= 0.079 && sensor_YCoordinate_variance > 0.001 && sensor_ZCoordinate_MinValue > 0.016 && sensor_YCoordinate_Mean > -0.061) {
            return "t";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.083 && sensor_ZCoordinate_MaxValue <= 0.038 && sensor_XCoordinate_MaxValue <= 0.104) {
            return "v";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.033 && sensor_ZCoordinate_Mean > 0.017 && sensor_YCoordinate_Mean > -0.007 && sensor_YCoordinate_Mean <= -0.005 && sensor_ZCoordinate_Mean > 0.021) {
            return "k";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.027 && sensor_ZCoordinate_Mean <= 0.012 && sensor_YCoordinate_MinValue > -0.154 && sensor_XCoordinate_MinValue <= 0.006 && sensor_ZCoordinate_Mean > 0.007 && sensor_XCoordinate_MaxValue <= 0.100) {
            return "d";
        }
        ;
        if (sensor_ZCoordinate_Mean > 0.027 && sensor_XCoordinate_MinValue <= -0.018 && sensor_ZCoordinate_MaxValue > 0.052 && sensor_ZCoordinate_variance <= 0.000) {
            return "q";
        }
        ;
        if (sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_MaxValue <= 0.006 && sensor_XCoordinate_variance > 0.000 && sensor_YCoordinate_MinValue > -0.099) {
            return "a";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.075 && sensor_ZCoordinate_variance <= 0.000 && sensor_XCoordinate_MinValue <= 0.016 && sensor_XCoordinate_Mean <= 0.057) {
            return "r";
        }
        ;
        if (sensor_ZCoordinate_Mean <= 0.008 && sensor_ZCoordinate_MaxValue > 0.032 && sensor_ZCoordinate_MinValue > -0.015 && sensor_XCoordinate_Mean <= 0.050) {
            return "g";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.038 && sensor_XCoordinate_variance <= 0.000 && sensor_YCoordinate_MinValue <= -0.086 && sensor_YCoordinate_MinValue > -0.101 && sensor_XCoordinate_MaxValue > 0.035) {
            return "p";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.022 && sensor_YCoordinate_MinValue > -0.094 && sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_variance <= 0.003) {
            return "o";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.044 && sensor_YCoordinate_MinValue <= -0.205 && sensor_YCoordinate_Mean > -0.076 && sensor_XCoordinate_MinValue <= -0.002) {
            return "s";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.040 && sensor_ZCoordinate_MinValue > 0.005 && sensor_XCoordinate_Mean > 0.077 && sensor_YCoordinate_MinValue > -0.196 && sensor_YCoordinate_MaxValue > -0.052 && sensor_YCoordinate_MaxValue <= 0.006) {
            return "z";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.021 && sensor_ZCoordinate_MaxValue <= 0.030 && sensor_ZCoordinate_MaxValue > 0.029) {
            return "c";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.032 && sensor_ZCoordinate_Mean > 0.019 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_MaxValue <= 0.052 && sensor_ZCoordinate_Mean <= 0.021) {
            return "n";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.027 && sensor_XCoordinate_MinValue > -0.011 && sensor_ZCoordinate_MinValue <= 0.006 && sensor_ZCoordinate_Mean > 0.015 && sensor_ZCoordinate_MaxValue <= 0.031 && sensor_YCoordinate_MaxValue > 0.024) {
            return "l";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.045 && sensor_ZCoordinate_MaxValue <= 0.028 && sensor_ZCoordinate_Mean > 0.017 && sensor_YCoordinate_Mean <= -0.071 && sensor_ZCoordinate_Mean <= 0.022) {
            return "t";
        }
        ;
        if (sensor_ZCoordinate_Mean > 0.018 && sensor_XCoordinate_variance <= 0.000 && sensor_ZCoordinate_MinValue <= 0.016 && sensor_XCoordinate_variance <= 0.000) {
            return "v";
        }
        ;
        if (sensor_YCoordinate_variance > 0.001 && sensor_ZCoordinate_MinValue > 0.012 && sensor_ZCoordinate_MinValue <= 0.015 && sensor_YCoordinate_Mean <= -0.014 && sensor_ZCoordinate_Mean > 0.021 && sensor_YCoordinate_variance > 0.001) {
            return "x";
        }
        ;
        if (sensor_XCoordinate_variance > 0.002 && sensor_ZCoordinate_MaxValue > 0.049 && sensor_XCoordinate_Mean > 0.126) {
            return "h";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.027 && sensor_ZCoordinate_MaxValue > 0.035 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_Mean <= 0.019 && sensor_ZCoordinate_Mean > 0.018) {
            return "k";
        }
        ;
        if (sensor_YCoordinate_variance > 0.011 && sensor_ZCoordinate_MaxValue <= 0.030 && sensor_YCoordinate_MaxValue <= 0.107) {
            return "f";
        }
        ;
        if (sensor_ZCoordinate_MaxValue > 0.034 && sensor_YCoordinate_MaxValue <= -0.029 && sensor_ZCoordinate_Mean > 0.027 && sensor_YCoordinate_variance > 0.000) {
            return "b";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.022 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_variance > 0.000 && sensor_YCoordinate_MaxValue <= 0.049 && sensor_XCoordinate_Mean > 0.015 && sensor_ZCoordinate_Mean <= 0.021) {
            return "j";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.079 && sensor_XCoordinate_variance > 0.000 && sensor_YCoordinate_variance <= 0.001 && sensor_YCoordinate_variance > 0.000 && sensor_ZCoordinate_variance <= 0.000) {
            return "i";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.040 && sensor_ZCoordinate_MinValue > 0.004 && sensor_ZCoordinate_variance > 0.000 && sensor_YCoordinate_variance <= 0.002 && sensor_ZCoordinate_MinValue > 0.013) {
            return "s";
        }
        ;
        if (sensor_XCoordinate_variance > 0.010) {
            return "a";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.023 && sensor_YCoordinate_variance <= 0.001 && sensor_YCoordinate_variance > 0.001) {
            return "c";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.021 && sensor_XCoordinate_MinValue <= 0.025 && sensor_ZCoordinate_MinValue <= 0.004) {
            return "r";
        }
        ;
        if (sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_MinValue <= -0.069 && sensor_ZCoordinate_MaxValue > 0.033 && sensor_ZCoordinate_Mean <= 0.017 && sensor_XCoordinate_MinValue > -0.031 && sensor_ZCoordinate_MaxValue <= 0.038) {
            return "g";
        }
        ;
        if (sensor_ZCoordinate_Mean <= 0.016 && sensor_XCoordinate_variance <= 0.000 && sensor_YCoordinate_MaxValue > 0.011 && sensor_XCoordinate_Mean <= 0.020 && sensor_ZCoordinate_Mean > 0.009) {
            return "h";
        }
        ;
        if (sensor_XCoordinate_variance <= 0.000 && sensor_XCoordinate_Mean > 0.034 && sensor_XCoordinate_Mean <= 0.042 && sensor_XCoordinate_MaxValue <= 0.050) {
            return "y";
        }
        ;
        if (sensor_ZCoordinate_MaxValue > 0.053 && sensor_XCoordinate_MaxValue <= 0.072 && sensor_YCoordinate_variance <= 0.003 && sensor_YCoordinate_variance > 0.001) {
            return "u";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.055 && sensor_YCoordinate_MaxValue <= -0.049 && sensor_YCoordinate_MaxValue > -0.051 && sensor_XCoordinate_variance > 0.000) {
            return "w";
        }
        ;
        if (sensor_YCoordinate_MinValue <= -0.093 && sensor_XCoordinate_MaxValue <= 0.051 && sensor_ZCoordinate_MaxValue <= 0.028 && sensor_ZCoordinate_Mean > 0.018) {
            return "v";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.032 && sensor_XCoordinate_MinValue <= -0.012 && sensor_XCoordinate_MaxValue <= 0.055 && sensor_YCoordinate_Mean <= -0.026 && sensor_XCoordinate_Mean > 0.003) {
            return "k";
        }
        ;
        if (sensor_ZCoordinate_Mean > 0.024 && sensor_YCoordinate_MaxValue > 0.078 && sensor_YCoordinate_MaxValue <= 0.127 && sensor_XCoordinate_variance > 0.000) {
            return "d";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.051 && sensor_YCoordinate_variance <= 0.001 && sensor_XCoordinate_MinValue <= 0.007 && sensor_XCoordinate_MinValue <= -0.020) {
            return "q";
        }
        ;
        if (sensor_YCoordinate_variance > 0.003 && sensor_XCoordinate_MaxValue <= 0.040 && sensor_XCoordinate_Mean > 0.008) {
            return "c";
        }
        ;
        if (sensor_YCoordinate_variance <= 0.001 && sensor_XCoordinate_MinValue <= -0.011 && sensor_XCoordinate_MaxValue > 0.040 && sensor_XCoordinate_variance <= 0.001) {
            return "o";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.045 && sensor_XCoordinate_MinValue <= -0.004 && sensor_XCoordinate_MinValue > -0.015 && sensor_XCoordinate_MinValue <= -0.011 && sensor_YCoordinate_MaxValue > -0.027) {
            return "t";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.028 && sensor_ZCoordinate_Mean > 0.018 && sensor_YCoordinate_variance > 0.001 && sensor_ZCoordinate_Mean <= 0.021 && sensor_ZCoordinate_MaxValue > 0.036 && sensor_XCoordinate_Mean > 0.014) {
            return "b";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.019 && sensor_XCoordinate_variance <= 0.001 && sensor_ZCoordinate_MinValue <= -0.003 && sensor_XCoordinate_MinValue > -0.010 && sensor_XCoordinate_Mean <= 0.029 && sensor_YCoordinate_variance <= 0.004) {
            return "p";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.031 && sensor_XCoordinate_variance <= 0.001 && sensor_XCoordinate_MinValue > 0.052 && sensor_YCoordinate_variance <= 0.001) {
            return "f";
        }
        ;
        if (sensor_YCoordinate_MinValue <= -0.135 && sensor_YCoordinate_variance <= 0.005 && sensor_XCoordinate_Mean <= 0.052 && sensor_ZCoordinate_variance <= 0.000) {
            return "a";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.079 && sensor_ZCoordinate_MaxValue > 0.033 && sensor_YCoordinate_MaxValue <= 0.016 && sensor_XCoordinate_variance > 0.001) {
            return "i";
        }
        ;
        if (sensor_YCoordinate_MinValue <= -0.099 && sensor_XCoordinate_MaxValue <= 0.036 && sensor_ZCoordinate_Mean <= 0.015 && sensor_XCoordinate_Mean <= 0.014) {
            return "v";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.029 && sensor_ZCoordinate_Mean > 0.020 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_MinValue > -0.005 && sensor_YCoordinate_Mean <= -0.007) {
            return "n";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.033 && sensor_XCoordinate_MaxValue <= 0.094 && sensor_XCoordinate_MaxValue > 0.068 && sensor_YCoordinate_variance <= 0.002) {
            return "r";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.062 && sensor_XCoordinate_Mean > 0.052 && sensor_YCoordinate_MaxValue > 0.050 && sensor_XCoordinate_MaxValue > 0.169) {
            return "e";
        }
        ;
        if (sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_MinValue <= -0.012 && sensor_XCoordinate_MinValue > -0.031) {
            return "g";
        }
        ;
        if (sensor_YCoordinate_variance > 0.005 && sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_variance <= 0.008 && sensor_XCoordinate_variance > 0.000 && sensor_YCoordinate_variance <= 0.007) {
            return "x";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.059 && sensor_YCoordinate_MaxValue <= -0.065 && sensor_YCoordinate_variance > 0.002) {
            return "s";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.041 && sensor_ZCoordinate_MaxValue > 0.050 && sensor_XCoordinate_Mean > -0.002 && sensor_XCoordinate_Mean <= 0.044) {
            return "h";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.007 && sensor_ZCoordinate_MinValue <= 0.005 && sensor_ZCoordinate_variance <= 0.000 && sensor_XCoordinate_Mean > 0.032) {
            return "y";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.059 && sensor_ZCoordinate_MaxValue > 0.032 && sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_Mean > -0.034 && sensor_ZCoordinate_Mean <= 0.020) {
            return "l";
        }
        ;
        if (sensor_YCoordinate_variance > 0.007 && sensor_YCoordinate_MaxValue <= 0.090 && sensor_YCoordinate_MaxValue > 0.032 && sensor_XCoordinate_Mean > 0.033) {
            return "c";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.054 && sensor_XCoordinate_variance > 0.002 && sensor_XCoordinate_variance <= 0.003 && sensor_ZCoordinate_variance > 0.000) {
            return "w";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.063 && sensor_ZCoordinate_Mean > 0.016 && sensor_YCoordinate_variance > 0.005 && sensor_XCoordinate_Mean <= 0.018) {
            return "m";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.040 && sensor_ZCoordinate_MaxValue <= 0.032 && sensor_ZCoordinate_Mean > 0.020 && sensor_ZCoordinate_MaxValue <= 0.027) {
            return "z";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.012 && sensor_ZCoordinate_Mean > 0.014 && sensor_XCoordinate_MaxValue <= 0.080 && sensor_YCoordinate_variance > 0.002 && sensor_XCoordinate_MaxValue > 0.063 && sensor_XCoordinate_MinValue > -0.033) {
            return "j";
        }
        ;
        if (sensor_ZCoordinate_MaxValue > 0.035 && sensor_XCoordinate_Mean <= 0.027 && sensor_ZCoordinate_variance <= 0.000 && sensor_XCoordinate_variance <= 0.001 && sensor_YCoordinate_variance <= 0.005) {
            return "k";
        }
        ;
        if (sensor_YCoordinate_MaxValue > 0.027 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_variance <= 0.001) {
            return "f";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.007 && sensor_ZCoordinate_MinValue > 0.009 && sensor_ZCoordinate_variance > 0.000 && sensor_YCoordinate_MaxValue <= -0.002) {
            return "d";
        }
        ;
        if (sensor_ZCoordinate_MinValue > 0.018 && sensor_XCoordinate_MinValue <= 0.001 && sensor_ZCoordinate_Mean <= 0.029) {
            return "a";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.024 && sensor_ZCoordinate_Mean > 0.019 && sensor_YCoordinate_variance > 0.001 && sensor_XCoordinate_variance <= 0.001) {
            return "b";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.031 && sensor_ZCoordinate_MaxValue > 0.033 && sensor_YCoordinate_variance <= 0.002 && sensor_XCoordinate_MaxValue > 0.077) {
            return "i";
        }
        ;
        if (sensor_YCoordinate_MinValue <= -0.141 && sensor_XCoordinate_Mean <= 0.049 && sensor_XCoordinate_Mean > 0.043 && sensor_XCoordinate_MinValue > 0.010) {
            return "v";
        }
        ;
        if (sensor_XCoordinate_MaxValue > 0.085 && sensor_YCoordinate_variance <= 0.001) {
            return "q";
        };
        if (sensor_ZCoordinate_Mean > 0.017 && sensor_YCoordinate_Mean <= -0.072 && sensor_ZCoordinate_MaxValue <= 0.032 && sensor_XCoordinate_variance <= 0.002) {
            return "s";
        }
        ;
        if (sensor_YCoordinate_MaxValue > 0.069 && sensor_ZCoordinate_Mean <= 0.013 && sensor_XCoordinate_MinValue > -0.021 && sensor_XCoordinate_MinValue <= -0.011) {
            return "p";
        }
        ;
        if (sensor_XCoordinate_variance <= 0.000 && sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_Mean <= 0.015 && sensor_ZCoordinate_variance <= 0.000) {
            return "y";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.016 && sensor_YCoordinate_Mean <= -0.015) {
            return "h";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.038 && sensor_XCoordinate_Mean > 0.031 && sensor_XCoordinate_variance > 0.000) {
            return "u";
        }
        ;
        if (sensor_YCoordinate_MaxValue <= -0.031 && sensor_XCoordinate_MaxValue <= 0.065 && sensor_YCoordinate_variance > 0.001) {
            return "t";
        }
        ;
        if (sensor_ZCoordinate_MinValue <= -0.009 && sensor_ZCoordinate_Mean > 0.009 && sensor_XCoordinate_Mean <= 0.018 && sensor_YCoordinate_MinValue > -0.094 && sensor_YCoordinate_MinValue <= -0.064) {
            return "o";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.062 && sensor_XCoordinate_MinValue > -0.002 && sensor_ZCoordinate_MinValue > 0.000 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_variance <= 0.000) {
            return "e";
        }
        ;
        if (sensor_ZCoordinate_MinValue <= -0.005 && sensor_YCoordinate_Mean <= -0.035 && sensor_XCoordinate_MaxValue <= 0.062 && sensor_ZCoordinate_Mean > 0.005) {
            return "r";
        }
        ;
        if (sensor_YCoordinate_variance > 0.008 && sensor_YCoordinate_MaxValue <= 0.121 && sensor_YCoordinate_variance <= 0.020) {
            return "z";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.044 && sensor_YCoordinate_Mean > -0.070 && sensor_YCoordinate_MaxValue <= -0.023 && sensor_YCoordinate_Mean <= -0.058) {
            return "g";
        }
        ;
        if (sensor_ZCoordinate_MaxValue <= 0.011 && sensor_XCoordinate_MinValue > -0.039) {
            return "d";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.043 && sensor_ZCoordinate_MaxValue > 0.047 && sensor_XCoordinate_Mean <= 0.051 && sensor_XCoordinate_variance > 0.002) {
            return "x";
        }
        ;
        if (sensor_ZCoordinate_Mean > 0.018 && sensor_ZCoordinate_Mean <= 0.019 && sensor_YCoordinate_Mean <= -0.046 && sensor_YCoordinate_Mean > -0.070) {
            return "f";
        }
        ;
        if (sensor_YCoordinate_MaxValue <= -0.017 && sensor_XCoordinate_Mean <= 0.045 && sensor_ZCoordinate_variance > 0.000) {
            return "a";
        }
        ;
        if (sensor_ZCoordinate_MaxValue <= 0.026 && sensor_ZCoordinate_variance <= 0.000 && sensor_YCoordinate_variance > 0.001 && sensor_YCoordinate_variance <= 0.004) {
            return "c";
        }
        ;
        if (sensor_XCoordinate_variance <= 0.000 && sensor_ZCoordinate_Mean > 0.016 && sensor_ZCoordinate_MinValue <= 0.009 && sensor_XCoordinate_Mean <= 0.025) {
            return "m";
        }
        ;
        if (sensor_XCoordinate_variance > 0.001 && sensor_ZCoordinate_MinValue > -0.004 && sensor_XCoordinate_Mean <= 0.012) {
            return "v";
        }
        ;
        if (sensor_ZCoordinate_Mean <= 0.004 && sensor_ZCoordinate_Mean > 0.002 && sensor_YCoordinate_MaxValue <= 0.081 && sensor_YCoordinate_variance > 0.001) {
            return "h";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.011 && sensor_ZCoordinate_MinValue > -0.006 && sensor_XCoordinate_MinValue <= -0.009 && sensor_YCoordinate_MinValue > -0.070 && sensor_XCoordinate_Mean > -0.002) {
            return "n";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.054 && sensor_XCoordinate_MaxValue <= 0.067 && sensor_ZCoordinate_variance > 0.000) {
            return "q";
        }
        ;
        if (sensor_YCoordinate_MaxValue <= -0.037 && sensor_ZCoordinate_Mean <= 0.011) {
            return "w";
        }
        ;
        if (sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_MinValue > -0.011 && sensor_XCoordinate_Mean <= 0.017) {
            return "b";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.029 && sensor_ZCoordinate_MaxValue > 0.033 && sensor_YCoordinate_MaxValue <= 0.022 && sensor_XCoordinate_Mean <= 0.018) {
            return "i";
        }
        ;
        if (sensor_YCoordinate_Mean > -0.010 && sensor_ZCoordinate_MaxValue > 0.031 && sensor_YCoordinate_Mean <= 0.001 && sensor_YCoordinate_Mean > -0.005) {
            return "k";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.081 && sensor_YCoordinate_MinValue <= -0.073 && sensor_XCoordinate_Mean <= 0.025 && sensor_ZCoordinate_MaxValue > 0.026) {
            return "j";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.033 && sensor_ZCoordinate_MinValue > -0.000 && sensor_ZCoordinate_variance > 0.000 && sensor_YCoordinate_MaxValue <= -0.000 && sensor_ZCoordinate_Mean > 0.015) {
            return "s";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.066 && sensor_YCoordinate_variance <= 0.003 && sensor_XCoordinate_variance > 0.001) {
            return "x";
        }
        ;
        if (sensor_ZCoordinate_MinValue > 0.012 && sensor_XCoordinate_Mean <= 0.038 && sensor_XCoordinate_variance > 0.000 && sensor_XCoordinate_variance <= 0.000) {
            return "m";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.011 && sensor_XCoordinate_MinValue <= 0.016 && sensor_YCoordinate_MinValue <= -0.105) {
            return "b";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.030 && sensor_XCoordinate_MinValue <= -0.010 && sensor_ZCoordinate_MaxValue <= 0.038 && sensor_YCoordinate_MaxValue > 0.058) {
            return "r";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.055 && sensor_XCoordinate_variance > 0.000 && sensor_XCoordinate_variance <= 0.001 && sensor_YCoordinate_Mean > -0.042 && sensor_YCoordinate_Mean <= -0.006) {
            return "l";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.042 && sensor_ZCoordinate_MaxValue <= 0.036 && sensor_XCoordinate_MaxValue <= 0.067) {
            return "z";
        }
        ;
        if (sensor_YCoordinate_Mean <= -0.025 && sensor_YCoordinate_MaxValue > 0.018 && sensor_YCoordinate_MaxValue <= 0.024 && sensor_YCoordinate_Mean > -0.049) {
            return "g";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.007 && sensor_YCoordinate_MinValue > -0.115 && sensor_YCoordinate_Mean <= -0.029 && sensor_YCoordinate_variance > 0.001) {
            return "d";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.007 && sensor_YCoordinate_MaxValue > 0.019 && sensor_XCoordinate_variance > 0.000 && sensor_ZCoordinate_Mean <= 0.023) {
            return "y";
        }
        ;
        if (sensor_ZCoordinate_Mean > 0.021 && sensor_ZCoordinate_Mean <= 0.022) {
            return "f";
        }
        ;
        if (sensor_XCoordinate_variance <= 0.000 && sensor_XCoordinate_variance > 0.000 && sensor_XCoordinate_MaxValue <= 0.041 && sensor_XCoordinate_Mean > 0.002) {
            return "a";
        }
        ;
        if (sensor_ZCoordinate_MaxValue <= 0.026 && sensor_ZCoordinate_MaxValue > 0.026 && sensor_XCoordinate_MaxValue <= 0.046) {
            return "c";
        }
        ;
        if (sensor_XCoordinate_Mean <= 0.001 && sensor_YCoordinate_Mean <= -0.043) {
            return "u";
        }
        ;
        if (sensor_XCoordinate_variance > 0.003 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_Mean > 0.007) {
            return "q";
        }
        ;
        if (sensor_XCoordinate_MaxValue > 0.092 && sensor_YCoordinate_Mean > -0.016 && sensor_YCoordinate_Mean <= 0.008) {
            return "h";
        }
        ;
        if (sensor_XCoordinate_MinValue > 0.017 && sensor_ZCoordinate_Mean > 0.017 && sensor_XCoordinate_Mean > 0.043 && sensor_YCoordinate_Mean <= -0.041) {
            return "w";
        }
        ;
        if (sensor_YCoordinate_MinValue > -0.057 && sensor_XCoordinate_MaxValue > 0.056 && sensor_ZCoordinate_MaxValue <= 0.029 && sensor_ZCoordinate_MaxValue > 0.029) {
            return "o";
        }
        ;
        if (sensor_XCoordinate_Mean > 0.034 && sensor_YCoordinate_MaxValue > 0.054 && sensor_YCoordinate_Mean <= -0.020 && sensor_YCoordinate_variance > 0.002) {
            return "t";
        }
        ;
        if (sensor_XCoordinate_MaxValue <= 0.038 && sensor_YCoordinate_MaxValue > 0.005 && sensor_XCoordinate_MinValue <= 0.005 && sensor_XCoordinate_Mean > -0.008) {
            return "x";
        }
        ;
        if (sensor_YCoordinate_MaxValue <= 0.018 && sensor_XCoordinate_MaxValue > 0.111 && sensor_YCoordinate_MinValue <= -0.139 && sensor_XCoordinate_Mean <= 0.079) {
            return "e";
        }
        ;
        if (sensor_XCoordinate_MinValue <= -0.012 && sensor_XCoordinate_MinValue > -0.016 && sensor_XCoordinate_Mean <= 0.043) {
            return "i";
        }
        ;
        return " ";
    }
}
