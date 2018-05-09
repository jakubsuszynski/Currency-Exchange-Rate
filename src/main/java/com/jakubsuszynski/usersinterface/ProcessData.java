package com.jakubsuszynski.usersinterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ProcessData {


    public static BigDecimal getMean(List<BigDecimal> values) {

        BigDecimal sum = BigDecimal.valueOf(0);
        for (BigDecimal value : values) {
            sum = sum.add(value);
        }
        return sum.divide(BigDecimal.valueOf(values.size()), 6, RoundingMode.HALF_UP);

    }

    public static BigDecimal getStandardDeviation(List<BigDecimal> values) {
        BigDecimal mean = getMean(values);
        BigDecimal squareStandardDeviation = BigDecimal.ZERO;

        for (BigDecimal value : values) {
            squareStandardDeviation = squareStandardDeviation.add(value.subtract(mean).multiply(value.subtract(mean)));
        }


        squareStandardDeviation = squareStandardDeviation.divide(BigDecimal.valueOf(values.size()), 10, RoundingMode.HALF_UP);


        return sqrt(squareStandardDeviation).setScale(6, RoundingMode.HALF_UP);
    }

    public static BigDecimal sqrt(BigDecimal value) { //loosing precision to 32 digits
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

}

