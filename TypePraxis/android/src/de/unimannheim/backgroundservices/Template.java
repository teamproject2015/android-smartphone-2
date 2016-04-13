package de.unimannheim.backgroundservices;

/**
 * Created by Saimadhav S on 07.04.2016.
 */
public class Template {

    public static String getCharacter(double sensor_XCoordinate_variance, double sensor_YCoordinate_variance, double sensor_ZCoordinate_variance,
                               double sensor_XCoordinate_Mean, double sensor_YCoordinate_Mean, double sensor_ZCoordinate_Mean,
                               double sensor_XCoordinate_MinValue, double sensor_XCoordinate_MaxValue,
                               double sensor_YCoordinate_MinValue, double sensor_YCoordinate_MaxValue,
                               double sensor_ZCoordinate_MinValue, double sensor_ZCoordinate_MaxValue) {

        if (sensor_YCoordinate_variance <= 0.008 &&
                sensor_YCoordinate_Mean <= -0.029 &&
                sensor_ZCoordinate_Mean > 0.017 && sensor_XCoordinate_Mean <=
                0.039 && sensor_YCoordinate_variance > 0.002 && sensor_XCoordinate_variance > 0.002) {
            return "h";
        }
        if (sensor_YCoordinate_MaxValue <= 0.089
                && sensor_XCoordinate_variance > 0.007 && sensor_ZCoordinate_variance <= 0.000
                && sensor_ZCoordinate_MaxValue > 0.039 && sensor_XCoordinate_Mean <= 0.054) {
            return "v";
        }
        if (sensor_XCoordinate_MinValue > -0.021 && sensor_YCoordinate_Mean <=
                -0.055 && sensor_YCoordinate_variance <=
                0.020 && sensor_YCoordinate_MaxValue > 0.108 && sensor_YCoordinate_MaxValue <= 0.179) {
            return "i";
        }
        if (sensor_YCoordinate_Mean <=
                -0.044 && sensor_YCoordinate_MinValue > -0.314 && sensor_YCoordinate_variance > 0.005
                && sensor_XCoordinate_Mean > 0.032 && sensor_XCoordinate_MaxValue > 0.162 && sensor_YCoordinate_MaxValue > 0.126) {
            return "j";
        }
        if (sensor_YCoordinate_Mean <=
                -0.067 && sensor_XCoordinate_MinValue > -0.051 && sensor_ZCoordinate_variance <=
                0.000 && sensor_YCoordinate_MaxValue > 0.165 && sensor_YCoordinate_Mean <= -0.068) {
            return "o";
        }
        if (sensor_YCoordinate_variance <= 0.006 && sensor_XCoordinate_variance <=
                0.002 && sensor_XCoordinate_Mean <=
                0.008 && sensor_XCoordinate_Mean > 0.005 && sensor_XCoordinate_variance <= 0.001) {
            return "r";
        }
        if (sensor_YCoordinate_MinValue <=
                -0.390 && sensor_ZCoordinate_Mean > 0.022 && sensor_XCoordinate_Mean <= 0.034) {
            return "p";
        }
        if (sensor_XCoordinate_MinValue > -0.038 && sensor_YCoordinate_MaxValue <=
                0.098 && sensor_XCoordinate_Mean > 0.040 && sensor_YCoordinate_variance > 0.002 && sensor_ZCoordinate_variance > 0.000) {
            return "u";
        }
        if (sensor_YCoordinate_Mean > -0.000 && sensor_YCoordinate_variance <=
                0.007 && sensor_YCoordinate_MinValue <= -0.092 && sensor_YCoordinate_Mean > 0.023) {
            return "d";
        }
        if (sensor_ZCoordinate_MinValue <= -0.032 && sensor_YCoordinate_variance <=
                0.010 && sensor_XCoordinate_MaxValue <= 0.237 && sensor_ZCoordinate_MinValue <=
                -0.040 && sensor_YCoordinate_Mean <= -0.027) {
            return "t";
        }
        if (sensor_XCoordinate_MaxValue > 0.193 && sensor_YCoordinate_MaxValue <=
                0.158 && sensor_YCoordinate_MinValue <= -0.113 && sensor_ZCoordinate_variance <=
                0.000 && sensor_XCoordinate_MinValue <=
                -0.096 && sensor_YCoordinate_MinValue > -0.247 && sensor_YCoordinate_Mean <= -0.027) {
            return "b";
        }
        if (sensor_YCoordinate_MaxValue > 0.251 && sensor_XCoordinate_MaxValue <=
                0.141 && sensor_ZCoordinate_variance > 0.001 && sensor_XCoordinate_MinValue <=
                -0.058 && sensor_YCoordinate_Mean <= 0.056) {
            return "q";
        }
        if (sensor_YCoordinate_Mean > 0.041 && sensor_XCoordinate_Mean > 0.065 && sensor_XCoordinate_MaxValue <=
                0.236) {
            return "s";
        }
        if (sensor_YCoordinate_Mean <=
                -0.055 && sensor_XCoordinate_Mean > 0.017 && sensor_ZCoordinate_MaxValue > 0.044 && sensor_ZCoordinate_variance <=
                0.000 && sensor_XCoordinate_variance > 0.004 && sensor_XCoordinate_MaxValue <= 0.314) {
            return "k";
        }
        if (sensor_YCoordinate_MaxValue <=
                0.047 && sensor_XCoordinate_Mean > 0.034 && sensor_ZCoordinate_variance > 0.000 && sensor_ZCoordinate_Mean <=
                0.019 && sensor_XCoordinate_variance <= 0.007) {
            return "y";
        }
        if (sensor_YCoordinate_MinValue > -0.128 && sensor_YCoordinate_Mean > -0.004 && sensor_YCoordinate_variance > 0.002
                && sensor_XCoordinate_MaxValue > 0.233 && sensor_ZCoordinate_MaxValue <=
                0.047 && sensor_YCoordinate_MaxValue <= 0.197 && sensor_XCoordinate_variance <= 0.027) {
            return "x";
        }
        if (sensor_YCoordinate_Mean <= -0.052 && sensor_XCoordinate_MinValue <=
                -0.112 && sensor_YCoordinate_variance > 0.020 && sensor_YCoordinate_MinValue > -0.337 && sensor_YCoordinate_Mean <=
                -0.066) {
            return "m";
        }
        if (sensor_YCoordinate_MinValue > -0.088 && sensor_XCoordinate_Mean > 0.021 && sensor_XCoordinate_Mean <=
                0.025 && sensor_XCoordinate_variance <= 0.011) {
            return "f";
        }
        if (sensor_YCoordinate_Mean > -0.019 && sensor_XCoordinate_MinValue > -0.018 && sensor_YCoordinate_MinValue <=
                -0.120 && sensor_YCoordinate_variance <= 0.008) {
            return "e";
        }
        if (sensor_YCoordinate_Mean > 0.005 && sensor_ZCoordinate_MaxValue <=
                0.034 && sensor_XCoordinate_Mean <=
                0.030 && sensor_XCoordinate_MaxValue > 0.122 && sensor_ZCoordinate_variance <=
                0.000 && sensor_YCoordinate_MaxValue > 0.117) {
            return "z";
        }
        if (sensor_YCoordinate_Mean > 0.019 && sensor_YCoordinate_MinValue <=
                -0.247 && sensor_XCoordinate_MinValue <= -0.050 && sensor_ZCoordinate_variance <= 0.001) {
            return "a";
        }
        if (sensor_YCoordinate_Mean > -0.003 && sensor_YCoordinate_MinValue <=
                -0.190 && sensor_XCoordinate_Mean <= 0.013 && sensor_XCoordinate_Mean > 0.064 && sensor_ZCoordinate_MaxValue <= 0.046) {
            return "a";
        }
        if (sensor_YCoordinate_Mean > 0.006 && sensor_YCoordinate_MinValue <=
                -0.190 && sensor_XCoordinate_MinValue <= -0.071 && sensor_YCoordinate_variance <= 0.034) {
            return "a";
        }
        if (sensor_XCoordinate_variance <= 0.002 && sensor_XCoordinate_variance > 0.002) {
            return "a";
        }

        if (sensor_YCoordinate_Mean <= -0.043 && sensor_XCoordinate_MinValue <=
                -0.092 && sensor_ZCoordinate_Mean <=
                0.006 && sensor_XCoordinate_MaxValue > 0.148 && sensor_YCoordinate_variance <= 0.052) {
            return "n";
        }
        if (sensor_YCoordinate_variance <= 0.003 && sensor_XCoordinate_variance <=
                0.002 && sensor_YCoordinate_MinValue <=
                -0.077 && sensor_YCoordinate_Mean > -0.029 && sensor_ZCoordinate_MaxValue <=
                0.040 && sensor_XCoordinate_variance <= 0.002) {
            return "r";
        }
        if (sensor_YCoordinate_Mean > -0.000 && sensor_YCoordinate_MinValue <=
                -0.224 && sensor_XCoordinate_variance <= 0.002 && sensor_ZCoordinate_MinValue <= -0.013) {
            return "w";
        }
        if (sensor_YCoordinate_MinValue > -0.074 && sensor_XCoordinate_MinValue <=
                -0.101 && sensor_XCoordinate_MaxValue > 0.268 && sensor_ZCoordinate_MinValue <= -0.021) {
            return "c";
        }
        if (sensor_YCoordinate_MaxValue <=
                0.086 && sensor_XCoordinate_variance > 0.008 && sensor_YCoordinate_variance <=
                0.001 && sensor_XCoordinate_MinValue <= -0.083) {
            return "v";
        }
        if (sensor_YCoordinate_MinValue <= -0.330 && sensor_XCoordinate_variance <=
                0.011 && sensor_XCoordinate_Mean <= 0.016 && sensor_ZCoordinate_Mean > 0.010) {
            return "l";
        }
        if (sensor_YCoordinate_variance <= 0.003 && sensor_XCoordinate_MinValue <=
                -0.116 && sensor_XCoordinate_MinValue > -0.123 && sensor_ZCoordinate_variance > 0.000) {
            return "g";
        }
        if (sensor_YCoordinate_Mean <= -0.053 && sensor_YCoordinate_variance <=
                0.021 && sensor_ZCoordinate_MinValue > -0.010 && sensor_ZCoordinate_Mean <=
                0.019 && sensor_ZCoordinate_Mean > 0.016) {
            return "j";
        }
        if (sensor_YCoordinate_MinValue > -0.142 && sensor_XCoordinate_MinValue > -0.051 && sensor_YCoordinate_Mean <=
                -0.043 && sensor_ZCoordinate_MinValue > -0.006) {
            return "h";
        }
        if (sensor_YCoordinate_MinValue <=
                -0.294 && sensor_XCoordinate_MinValue > -0.039 && sensor_ZCoordinate_Mean > 0.013 && sensor_ZCoordinate_Mean <=
                0.017) {
            return "o";
        }
        if (sensor_YCoordinate_Mean > -0.000 && sensor_YCoordinate_MaxValue <=
                0.143 && sensor_XCoordinate_Mean > 0.008 && sensor_XCoordinate_MinValue <=
                -0.078 && sensor_ZCoordinate_variance > 0.000 && sensor_XCoordinate_Mean <= 0.060) {
            return "d";
        }
        if (sensor_YCoordinate_Mean <=
                -0.052 && sensor_XCoordinate_MaxValue > 0.175 && sensor_XCoordinate_MaxValue > 0.375 && sensor_XCoordinate_MaxValue <=
                0.410) {
            return "m";
        }
        if (sensor_YCoordinate_Mean > -0.004 && sensor_YCoordinate_Mean > 0.041 && sensor_YCoordinate_MinValue <=
                -0.158 && sensor_YCoordinate_MaxValue <= 0.265 && sensor_ZCoordinate_Mean > 0.006) {
            return "s";
        }
        if (sensor_YCoordinate_MinValue <= -0.254 && sensor_YCoordinate_variance <=
                0.029 && sensor_ZCoordinate_MaxValue > 0.040 && sensor_ZCoordinate_Mean <=
                0.024 && sensor_ZCoordinate_variance <= 0.000 && sensor_ZCoordinate_MaxValue > 0.044) {
            return "k";
        }
        if (sensor_YCoordinate_MinValue > -0.075 && sensor_ZCoordinate_MaxValue > 0.061) {
            return "f";
        }
        if (sensor_XCoordinate_variance <= 0.002 && sensor_YCoordinate_variance <=
                0.009 && sensor_XCoordinate_Mean > 0.038 && sensor_YCoordinate_Mean <= -0.025) {
            return "u";
        }
        if (sensor_YCoordinate_variance <= 0.003 && sensor_ZCoordinate_Mean <=
                0.003 && sensor_ZCoordinate_MinValue <= -0.015 && sensor_ZCoordinate_MaxValue <= 0.016) {
            return "t";
        }
        if (sensor_YCoordinate_MinValue > -0.094 && sensor_YCoordinate_MinValue <=
                -0.082 && sensor_YCoordinate_Mean <= -0.035 && sensor_XCoordinate_variance > 0.001) {
            return "g";
        }
        if (sensor_YCoordinate_variance <= 0.007 && sensor_XCoordinate_MinValue <=
                -0.097 && sensor_YCoordinate_Mean <= -0.029 && sensor_XCoordinate_MinValue > -0.102) {
            return "v";
        }
        if (sensor_YCoordinate_Mean <=
                -0.031 && sensor_XCoordinate_Mean > 0.025 && sensor_XCoordinate_MinValue > -0.017 && sensor_ZCoordinate_Mean <=
                0.018 && sensor_YCoordinate_variance > 0.013) {
            return "i";
        }
        if (sensor_XCoordinate_Mean <=
                0.011 && sensor_YCoordinate_Mean > -0.027 && sensor_XCoordinate_MinValue <=
                -0.120 && sensor_XCoordinate_MinValue > -0.147 && sensor_XCoordinate_MaxValue > 0.117 && sensor_XCoordinate_Mean <=0.005) {
            return "x";
        }
        if (sensor_YCoordinate_Mean <= -0.035 && sensor_ZCoordinate_variance <=
                0.000 && sensor_XCoordinate_MaxValue > 0.160 && sensor_ZCoordinate_Mean > 0.014 && sensor_XCoordinate_Mean > 0.029 && sensor_YCoordinate_variance > 0.005) {
            return "j";
        }
        if (sensor_YCoordinate_Mean > -0.013 && sensor_YCoordinate_MinValue <=
                -0.115 && sensor_XCoordinate_variance <= 0.004 && sensor_XCoordinate_MinValue <=
                -0.060 && sensor_XCoordinate_MinValue > -0.078) {
            return "e";
        }
        if (sensor_YCoordinate_variance <= 0.006 && sensor_XCoordinate_variance <=
                0.002 && sensor_YCoordinate_Mean > 0.001 && sensor_ZCoordinate_Mean > 0.014 && sensor_ZCoordinate_variance > 0.000) {
            return "r";
        }
        if (sensor_YCoordinate_MinValue <=
                -0.294 && sensor_XCoordinate_MinValue > -0.052 && sensor_YCoordinate_MaxValue <=
                0.252 && sensor_YCoordinate_MinValue <= -0.372 && sensor_ZCoordinate_Mean <= 0.030) {
            return "o";
        }
        if (sensor_YCoordinate_Mean <= -0.046 && sensor_XCoordinate_MinValue <=
                -0.090 && sensor_YCoordinate_MaxValue <=
                0.166 && sensor_YCoordinate_MaxValue > 0.094 && sensor_YCoordinate_variance <= 0.020) {
            return "n";
        }
        if (sensor_YCoordinate_Mean <= -0.052 && sensor_XCoordinate_MinValue <=
                -0.086 && sensor_ZCoordinate_Mean <= 0.008 && sensor_YCoordinate_MaxValue > 0.081) {
            return "m";
        }
        if (sensor_YCoordinate_MaxValue > 0.206 && sensor_YCoordinate_Mean > -0.039 && sensor_YCoordinate_Mean <=
                0.031 && sensor_XCoordinate_MaxValue <= 0.147 && sensor_YCoordinate_MaxValue > 0.272) {
            return "q";
        }
        if (sensor_XCoordinate_MaxValue <= 0.058 && sensor_YCoordinate_variance <=
                0.003 && sensor_ZCoordinate_MaxValue > 0.047) {
            return "t";
        }
        if (sensor_YCoordinate_Mean > -0.008 && sensor_YCoordinate_MinValue <=
                -0.102 && sensor_XCoordinate_variance <= 0.004 && sensor_YCoordinate_MaxValue <=
                0.121 && sensor_YCoordinate_MinValue <= -0.133) {
            return "s";
        }
        if (sensor_YCoordinate_MinValue <=
                -0.411 && sensor_ZCoordinate_MaxValue > 0.057 && sensor_XCoordinate_MaxValue <= 0.229) {
            return "p";
        }
        if (sensor_YCoordinate_MinValue > -0.095 && sensor_YCoordinate_variance <=
                0.001 && sensor_XCoordinate_MinValue <=
                -0.047 && sensor_YCoordinate_Mean > -0.010 && sensor_XCoordinate_Mean > -0.019) {
            return "f";
        }
        if (sensor_YCoordinate_variance <=
                0.008 && sensor_YCoordinate_MaxValue > 0.091 && sensor_YCoordinate_Mean <=
                -0.025 && sensor_YCoordinate_MinValue > -0.132 && sensor_XCoordinate_MinValue <= -0.030) {
            return "h";
        }
        if (sensor_XCoordinate_MinValue <= -0.128 && sensor_YCoordinate_MaxValue <=
                0.089 && sensor_XCoordinate_variance > 0.006 && sensor_YCoordinate_variance > 0.002 && sensor_ZCoordinate_MaxValue <=
                0.040 && sensor_XCoordinate_Mean <= 0.023) {
            return "v";
        }
        if (sensor_YCoordinate_Mean <= -0.060 && sensor_YCoordinate_Mean <=
                -0.108 && sensor_XCoordinate_variance > 0.012) {
            return "k";
        }
        if (sensor_YCoordinate_MaxValue <= 0.075 && sensor_ZCoordinate_MinValue <=
                -0.018 && sensor_YCoordinate_Mean <= -0.043 && sensor_XCoordinate_MinValue <= -0.052) {
            return "y";
        }
        if (sensor_YCoordinate_MaxValue <=
                0.090 && sensor_XCoordinate_variance > 0.004 && sensor_YCoordinate_Mean <=
                -0.029 && sensor_YCoordinate_Mean > -0.041 && sensor_ZCoordinate_Mean > 0.005) {
            return "b";
        }
        if (sensor_YCoordinate_MaxValue <=
                0.059 && sensor_YCoordinate_MaxValue > 0.049 && sensor_YCoordinate_MaxValue <=
                0.053 && sensor_YCoordinate_Mean > -0.025) {
            return "g";
        }
        if (sensor_YCoordinate_Mean > -0.007 && sensor_XCoordinate_Mean <=
                0.017 && sensor_YCoordinate_MinValue <=
                -0.096 && sensor_ZCoordinate_MinValue > -0.023 && sensor_XCoordinate_MinValue <=
                -0.084 && sensor_XCoordinate_Mean > -0.003) {
            return "z";
        }
        if (sensor_YCoordinate_Mean > -0.029 && sensor_ZCoordinate_variance <=
                0.000 && sensor_YCoordinate_variance > 0.002 && sensor_YCoordinate_MaxValue <= 0.068) {
            return "d";
        }
        if (sensor_YCoordinate_Mean > -0.004 && sensor_YCoordinate_MinValue <=
                -0.147 && sensor_YCoordinate_Mean <= 0.018 && sensor_YCoordinate_MaxValue <=
                0.181 && sensor_XCoordinate_variance <= 0.002) {
            return "w";
        }
        if (sensor_YCoordinate_variance <= 0.007 && sensor_YCoordinate_Mean <=
                -0.036 && sensor_XCoordinate_Mean > 0.022 && sensor_YCoordinate_MinValue > -0.169 && sensor_ZCoordinate_variance <=
                0.000) {
            return "u";
        }
        if (sensor_YCoordinate_MaxValue <= 0.078 && sensor_YCoordinate_MinValue <=
                -0.085 && sensor_XCoordinate_MaxValue <= 0.030 && sensor_XCoordinate_MaxValue > 0.023) {
            return "r";
        }
        if (sensor_YCoordinate_MinValue > -0.106 && sensor_ZCoordinate_MinValue <=
                -0.038 && sensor_ZCoordinate_MaxValue <= 0.043 && sensor_XCoordinate_MinValue <= -0.093) {
            return "t";
        }
        if (sensor_YCoordinate_MinValue <= -0.321 && sensor_XCoordinate_variance <=
                0.005 && sensor_XCoordinate_Mean > 0.011 && sensor_XCoordinate_variance > 0.002) {
            return "l";
        }
        if (sensor_YCoordinate_MinValue > -0.074 && sensor_XCoordinate_variance > 0.004 && sensor_ZCoordinate_MinValue > -0.013 && sensor_XCoordinate_variance > 0.005) {
            return "c";
        }
        if (sensor_YCoordinate_Mean > -0.012 && sensor_XCoordinate_MinValue > -0.018 && sensor_XCoordinate_MinValue <=
                -0.012 && sensor_ZCoordinate_Mean > 0.003) {
            return "e";
        }

        if (sensor_YCoordinate_Mean > 0.011 && sensor_YCoordinate_Mean > 0.041 && sensor_XCoordinate_Mean <=
                0.045 && sensor_YCoordinate_Mean <= 0.061) {
            return "s";
        }
        if (sensor_YCoordinate_variance <= 0.004 && sensor_ZCoordinate_MinValue <=
                -0.011 && sensor_ZCoordinate_MinValue > -0.012 && sensor_XCoordinate_MaxValue > 0.084) {
            return "f";
        }
        if (sensor_YCoordinate_MinValue > -0.120 && sensor_YCoordinate_Mean <=
                -0.017 && sensor_YCoordinate_variance > 0.004) {
            return "g";
        }
        if (sensor_YCoordinate_Mean <=
                -0.031 && sensor_XCoordinate_Mean > 0.026 && sensor_ZCoordinate_MinValue <=
                -0.010 && sensor_XCoordinate_MinValue > -0.070 && sensor_ZCoordinate_variance <=
                0.000 && sensor_ZCoordinate_variance > 0.000) {
            return "i";
        }
        if (sensor_YCoordinate_MaxValue <=
                0.081 && sensor_YCoordinate_variance > 0.002 && sensor_XCoordinate_MinValue <=
                -0.075 && sensor_XCoordinate_MinValue > -0.119 && sensor_ZCoordinate_MinValue > -0.021) {
            return "r";
        }
        if (sensor_YCoordinate_Mean > -0.009 && sensor_XCoordinate_Mean <=
                0.024 && sensor_ZCoordinate_MinValue > -0.011 && sensor_XCoordinate_variance > 0.003) {
            return "x";
        }
        if (sensor_YCoordinate_Mean <= -0.053 && sensor_XCoordinate_MinValue <=
                -0.064 && sensor_ZCoordinate_Mean <=
                0.014 && sensor_YCoordinate_Mean > -0.082 && sensor_YCoordinate_Mean <= -0.073) {
            return "m";
        }
        if (sensor_YCoordinate_Mean <=
                -0.054 && sensor_XCoordinate_Mean > 0.047 && sensor_YCoordinate_variance > 0.014 && sensor_XCoordinate_variance <=
                0.010 && sensor_XCoordinate_MaxValue > 0.074) {
            return "o";
        }
        if (sensor_YCoordinate_variance <=
                0.012 && sensor_XCoordinate_MaxValue > 0.178 && sensor_ZCoordinate_variance <=
                0.000 && sensor_ZCoordinate_MaxValue > 0.036) {
            return "b";
        }
        if (sensor_YCoordinate_Mean <= -0.040 && sensor_ZCoordinate_MaxValue <=
                0.033 && sensor_YCoordinate_Mean > -0.066 && sensor_XCoordinate_MaxValue > 0.057 && sensor_YCoordinate_variance <=
                0.021) {
            return "j";
        }
        if (sensor_YCoordinate_variance <=
                0.008 && sensor_YCoordinate_variance > 0.007 && sensor_YCoordinate_variance <= 0.007) {
            return "h";
        }
        if (sensor_YCoordinate_Mean > 0.015 && sensor_ZCoordinate_variance <=
                0.000 && sensor_XCoordinate_variance > 0.002) {
            return "d";
        }
        if (sensor_XCoordinate_variance > 0.023 && sensor_YCoordinate_variance <=
                0.010 && sensor_YCoordinate_Mean <= -0.021) {
            return "v";
        }
        if (sensor_YCoordinate_Mean <= -0.060 && sensor_XCoordinate_variance <=
                0.012 && sensor_XCoordinate_variance > 0.002 && sensor_YCoordinate_MinValue > -0.394 && sensor_YCoordinate_MaxValue > 0.177 && sensor_XCoordinate_Mean > 0.013) {
            return "k";
        }
        if (sensor_YCoordinate_variance <= 0.002 && sensor_ZCoordinate_MinValue <=
                -0.018 && sensor_XCoordinate_MinValue > -0.080 && sensor_XCoordinate_MinValue <= -0.061) {
            return "y";
        }
        if (sensor_YCoordinate_MaxValue > 0.199 && sensor_XCoordinate_MaxValue <=
                0.118 && sensor_XCoordinate_variance > 0.002 && sensor_YCoordinate_MinValue > -0.331) {
            return "q";
        }
        if (sensor_YCoordinate_variance > 0.012 && sensor_YCoordinate_Mean > -0.043 && sensor_ZCoordinate_Mean > 0.006 && sensor_YCoordinate_MaxValue <=
                0.197 && sensor_XCoordinate_variance <= 0.005 && sensor_XCoordinate_MinValue <= -0.014) {
            return "w";
        }
        if (sensor_XCoordinate_MaxValue <= 0.071 && sensor_ZCoordinate_MaxValue <=
                0.029 && sensor_XCoordinate_MinValue > -0.055 && sensor_ZCoordinate_Mean > 0.002 && sensor_XCoordinate_variance > 0.000) {
            return "t";
        }
        if (sensor_YCoordinate_variance <= 0.002 && sensor_YCoordinate_MinValue <=
                -0.054 && sensor_ZCoordinate_MinValue <= -0.017 && sensor_YCoordinate_MaxValue > 0.035) {
            return "f";
        }
        if (sensor_YCoordinate_MaxValue <=
                0.059 && sensor_YCoordinate_MaxValue > 0.027 && sensor_YCoordinate_MaxValue <=
                0.034 && sensor_ZCoordinate_Mean > 0.009 && sensor_ZCoordinate_variance > 0.000) {
            return "g";
        }
        if (sensor_YCoordinate_Mean > 0.001 && sensor_ZCoordinate_MinValue <=
                -0.024 && sensor_YCoordinate_Mean <= 0.029 && sensor_XCoordinate_MaxValue <= 0.198) {
            return "e";
        }
        if (sensor_XCoordinate_variance > 0.013 && sensor_ZCoordinate_MinValue > -0.015 && sensor_YCoordinate_variance <=
                0.038 && sensor_XCoordinate_Mean > -0.048) {
            return "n";
        }
        if (sensor_YCoordinate_Mean > -0.001 && sensor_YCoordinate_MinValue <=
                -0.102 && sensor_XCoordinate_MinValue > -0.007 && sensor_YCoordinate_MinValue <= -0.107) {
            return "s";
        }
        if (sensor_YCoordinate_variance <=
                0.005 && sensor_XCoordinate_Mean > 0.018 && sensor_YCoordinate_MinValue <=
                -0.077 && sensor_YCoordinate_Mean <= -0.009 && sensor_YCoordinate_Mean <= -0.033) {
            return "h";
        }
        if (sensor_YCoordinate_Mean > -0.011 && sensor_XCoordinate_Mean <=
                0.024 && sensor_YCoordinate_variance > 0.005 && sensor_YCoordinate_Mean <=
                0.051 && sensor_XCoordinate_variance > 0.001) {
            return "x";
        }
        if (sensor_YCoordinate_Mean > 0.009 && sensor_ZCoordinate_variance <=
                0.000 && sensor_YCoordinate_MinValue > -0.134) {
            return "d";
        }
        if (sensor_YCoordinate_Mean <=
                -0.052 && sensor_XCoordinate_MaxValue > 0.174 && sensor_ZCoordinate_MinValue > -0.011 && sensor_XCoordinate_Mean > -0.011) {
            return "m";
        }
        if (sensor_YCoordinate_variance > 0.032 && sensor_ZCoordinate_MinValue > -0.018 && sensor_XCoordinate_variance <=
                0.011) {
            return "l";
        }
        if (sensor_YCoordinate_MaxValue <= 0.091 && sensor_XCoordinate_MinValue <=
                -0.065 && sensor_ZCoordinate_Mean > 0.016 && sensor_YCoordinate_Mean > -0.018) {
            return "b";
        }
        if (sensor_YCoordinate_MinValue > -0.102 && sensor_XCoordinate_MinValue <=
                -0.065 && sensor_XCoordinate_variance <= 0.004 && sensor_YCoordinate_variance <=
                0.001 && sensor_YCoordinate_variance > 0.000 && sensor_ZCoordinate_variance <= 0.000) {
            return "v";
        }
        if (sensor_YCoordinate_MinValue > -0.141 && sensor_YCoordinate_variance > 0.003 && sensor_YCoordinate_Mean <=
                -0.009 && sensor_XCoordinate_Mean > 0.030 && sensor_XCoordinate_Mean <= 0.060) {
            return "h";
        }
        if (sensor_YCoordinate_MaxValue <= 0.078 && sensor_ZCoordinate_MaxValue <=
                0.028 && sensor_YCoordinate_MaxValue > 0.043 && sensor_YCoordinate_MaxValue <= 0.053) {
            return "r";
        }
        if (sensor_YCoordinate_Mean <=
                -0.031 && sensor_XCoordinate_Mean > 0.026 && sensor_ZCoordinate_MaxValue <=
                0.040 && sensor_ZCoordinate_variance <= 0.000 && sensor_XCoordinate_MinValue > -0.064) {
            return "i";
        }

        if (sensor_XCoordinate_variance <= 0.002 && sensor_YCoordinate_Mean <=
                -0.019 && sensor_XCoordinate_variance > 0.001 && sensor_XCoordinate_Mean > 0.039) {
            return "u";
        }
        if (sensor_YCoordinate_Mean <= -0.058 && sensor_XCoordinate_variance <=
                0.001 && sensor_YCoordinate_variance > 0.019) {
            return "p";
        }
        if (sensor_YCoordinate_Mean > 0.003 && sensor_XCoordinate_Mean <=
                0.039 && sensor_XCoordinate_MaxValue > 0.130 && sensor_XCoordinate_variance <= 0.006) {
            return "s";
        }
        if (sensor_XCoordinate_variance <= 0.001 && sensor_XCoordinate_MinValue <= -0.014) {
            return "y";
        }
        if (sensor_YCoordinate_MinValue > -0.103 && sensor_XCoordinate_MinValue <=
                -0.131 && sensor_XCoordinate_MinValue > -0.168) {
            return "c";
        }
        if (sensor_YCoordinate_Mean <= -0.048 && sensor_ZCoordinate_variance <=
                0.000 && sensor_XCoordinate_MaxValue > 0.163) {
            return "j";
        }
        if (sensor_YCoordinate_Mean > 0.010 && sensor_ZCoordinate_MaxValue <=
                0.035 && sensor_ZCoordinate_Mean > 0.008) {
            return "z";
        }
        if (sensor_YCoordinate_MinValue > -0.085 && sensor_YCoordinate_MinValue <=
                -0.060 && sensor_XCoordinate_Mean <=
                0.018 && sensor_XCoordinate_Mean > -0.004 && sensor_YCoordinate_MaxValue > 0.040) {
            return "g";
        }
        if (sensor_YCoordinate_MaxValue > 0.234 && sensor_XCoordinate_variance <=
                0.008 && sensor_YCoordinate_MaxValue <= 0.244) {
            return "w";
        }
        if (sensor_YCoordinate_MaxValue <= 0.090 && sensor_ZCoordinate_MinValue <=
                -0.019 && sensor_ZCoordinate_MinValue > -0.024 && sensor_XCoordinate_variance > 0.002) {
            return "b";
        }
        if (sensor_YCoordinate_variance <= 0.004 && sensor_ZCoordinate_MinValue > 0.009) {
            return "f";
        }
        if (sensor_YCoordinate_MinValue <= -0.484 && sensor_XCoordinate_Mean <= 0.042) {
            return "l";
        }
        if (sensor_YCoordinate_variance > 0.017 && sensor_ZCoordinate_Mean <=
                0.004 && sensor_YCoordinate_Mean <= 0.041 && sensor_XCoordinate_Mean <= 0.063) {
            return "q";
        }
        if (sensor_ZCoordinate_MinValue <= -0.055 && sensor_ZCoordinate_MaxValue <=
                0.051 && sensor_XCoordinate_MaxValue > 0.111) {
            return "t";
        }
        if (sensor_ZCoordinate_MinValue > -0.008 && sensor_XCoordinate_variance > 0.002 && sensor_XCoordinate_MinValue > -0.057) {
            return "e";
        }
        if (sensor_YCoordinate_MinValue <= -0.253 && sensor_ZCoordinate_variance <=
                0.000 && sensor_XCoordinate_Mean > 0.013 && sensor_YCoordinate_Mean <= -0.008) {
            return "k";
        }
        if (sensor_YCoordinate_Mean > 0.074 && sensor_YCoordinate_Mean <= 0.087) {
            return "d";
        }
        if (sensor_ZCoordinate_variance <= 0.000 && sensor_XCoordinate_MaxValue <= 0.064) {
            return "n";
        }
        if (sensor_YCoordinate_variance <= 0.004) {
            return "f";
        }
        if (sensor_XCoordinate_variance <=
                0.001 && sensor_XCoordinate_MinValue > -0.024 && sensor_XCoordinate_MinValue <= -0.005) {
            return "o";
        }
        if (sensor_YCoordinate_Mean > 0.012 && sensor_ZCoordinate_variance <=
                0.000 && sensor_ZCoordinate_variance > 0.000) {
            return "s";
        }
        if (sensor_XCoordinate_MinValue <= -0.153 && sensor_XCoordinate_Mean <=
                0.027 && sensor_XCoordinate_MinValue > -0.170) {
            return "m";
        }
        if (sensor_YCoordinate_Mean <= -0.056 && sensor_YCoordinate_variance <= 0.019) {
            return "j";
        }
        if (sensor_YCoordinate_Mean > 0.012 && sensor_YCoordinate_Mean <= 0.021) {
            return "w";
        }
        if (sensor_YCoordinate_variance <=
                0.007 && sensor_YCoordinate_variance > 0.005 && sensor_YCoordinate_Mean <= 0.065) {
            return "v";
        }

        if (sensor_XCoordinate_variance > 0.044 && sensor_XCoordinate_Mean <= 0.076) {
            return "n";
        }
        if (sensor_YCoordinate_Mean > 0.052 && sensor_YCoordinate_variance <=
                0.018 && sensor_XCoordinate_variance > 0.005) {
            return "z";
        }
        if (sensor_YCoordinate_MinValue <= -0.411 && sensor_YCoordinate_Mean <=
                -0.059 && sensor_XCoordinate_Mean > 0.010) {
            return "p";
        }
        if (sensor_YCoordinate_MinValue <= -0.310 && sensor_YCoordinate_variance <=
                0.058 && sensor_ZCoordinate_variance > 0.000) {
            return "i";
        }
        if (sensor_ZCoordinate_Mean > 0.016 && sensor_ZCoordinate_Mean <=
                0.018 && sensor_YCoordinate_variance > 0.009) {
            return "l";
        }
        if (sensor_YCoordinate_variance <=
                0.005 && sensor_XCoordinate_variance > 0.003 && sensor_XCoordinate_Mean <= 0.014) {
            return "d";
        }
        if (sensor_ZCoordinate_Mean <= 0.002 && sensor_XCoordinate_MinValue <=
                -0.065 && sensor_ZCoordinate_Mean > -0.008) {
            return "b";
        }
        if (sensor_YCoordinate_MinValue > -0.136 && sensor_XCoordinate_MinValue > -0.059 && sensor_XCoordinate_Mean <=
                0.065) {
            return "x";
        }
        if (sensor_XCoordinate_MaxValue <= 0.066 && sensor_XCoordinate_variance <= 0.002) {
            return "u";
        }
        if (sensor_YCoordinate_MinValue <= -0.296 && sensor_XCoordinate_MaxValue <=
                0.211 && sensor_ZCoordinate_MaxValue > 0.039 && sensor_XCoordinate_MinValue <= -0.076) {
            return "k";
        }
        if (sensor_YCoordinate_MinValue <= -0.339 && sensor_XCoordinate_variance <= 0.009) {
            return "o";
        }
        if (sensor_ZCoordinate_variance <= 0.000 && sensor_XCoordinate_MinValue > -0.064) {
            return "e";
        }
        if (sensor_YCoordinate_MaxValue <= 0.162 && sensor_YCoordinate_Mean > -0.028) {
            return "h";
        }
        if (sensor_YCoordinate_Mean > 0.066 && sensor_XCoordinate_Mean <= 0.023) {
            return "s";
        }
       /* if (sensor_XCoordinate_variance <= 0.003) {
            return "q";
        }*/
        if (sensor_XCoordinate_Mean <= 0.027 && sensor_XCoordinate_Mean > 0.011) {
            return "m";
        }
        if (sensor_ZCoordinate_MaxValue <= 0.032 && sensor_XCoordinate_Mean <= 0.056) {
            return "n";
        }
        if (sensor_XCoordinate_MinValue <= -0.169 && sensor_XCoordinate_Mean > 0.032) {
            return "r";
        }
        /*if (sensor_XCoordinate_MinValue > -0.035) {
            return "w";
        }*/
        if (sensor_ZCoordinate_Mean > 0.023 && sensor_XCoordinate_Mean <= 0.023) {
            return "v";
        }
        /*if (sensor_YCoordinate_MinValue <= -0.388) {
            return "j";
        }
        if (sensor_XCoordinate_MaxValue <= 0.098) {
            return "t";
        }
        if (sensor_XCoordinate_MinValue <= -0.213) {
            return "z";
        }
        if (sensor_XCoordinate_Mean <= 0.012) {
            return "g";
        }
        if (sensor_XCoordinate_variance <= 0.004) {
            return "y";
        }
       */
        return "";
    }
}
