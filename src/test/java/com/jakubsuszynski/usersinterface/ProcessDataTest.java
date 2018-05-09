package com.jakubsuszynski.usersinterface;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProcessDataTest {


    @Test
    public void countMean() {
        List<Double> values = Arrays.asList(5.0, 5.0, 8.0);

        assertThat(ProcessData.getMean(values), is(6.0));
    }


    @Test
    public void countMean2() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);

        assertThat(ProcessData.getMean(values), is(2.0));
    }

    @Test
    public void countStandardDeviation() {
        List<Double> values = Arrays.asList(1.0, 2.0, 3.0);

        assertThat(Math.round(ProcessData.getStandardDeviation(values)*10000.0)/10000.0, is(0.8165));
    }
}