package com.jakubsuszynski.usersinterface;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProcessDataTest {


    @Test
    public void countMean(){
        List<Double> values = Arrays.asList(3.2, 5.0, 9.4, 2.6 );

        assertThat(ProcessData.getMean(values), is(5.05));
    }
}