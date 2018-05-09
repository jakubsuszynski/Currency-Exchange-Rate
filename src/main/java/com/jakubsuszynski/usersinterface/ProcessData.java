package com.jakubsuszynski.usersinterface;

import java.util.List;

public class ProcessData {
    public static Double getMean(List<Double> values) {

        return values.stream().mapToDouble(Double::valueOf).average().getAsDouble();

    }

    public static Double getStandardDeviation(List<Double> values) {

        Double mean = getMean(values);
        Double temp = 0.0;

        for (Double value : values) {
            temp = temp + ((value - mean) * (value - mean));
        }

        return Math.sqrt((temp / values.size()));
    }
}
