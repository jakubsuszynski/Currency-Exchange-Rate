package com.jakubsuszynski.usersinterface;

import com.jakubsuszynski.apiclient.RestClient;
import com.jakubsuszynski.restresponse.Rates;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProcessDataTest {


    RestClient restClient;

    @Before
    public void setUp() {
        restClient = new RestClient();
    }

    @Test
    public void countMean() {
        List<BigDecimal> values = Arrays.asList(BigDecimal.valueOf(5), BigDecimal.valueOf(5), BigDecimal.valueOf(8));

        assertThat(ProcessData.getMean(values).setScale(1, RoundingMode.HALF_UP), is(BigDecimal.valueOf(6.0)));
    }


    @Test
    public void countMean2() {
        List<BigDecimal> values = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3));

        assertThat(ProcessData.getMean(values).setScale(1, RoundingMode.HALF_UP), is(BigDecimal.valueOf(2.0)));
    }

    @Test
    public void countStandardDeviation() {
        List<BigDecimal> values = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3));

        assertThat(ProcessData.getStandardDeviation(values), is(BigDecimal.valueOf(0.816497)));
    }

    @Test
    public void countMeanOnRealData() {
        List<BigDecimal> values = restClient.getCurrencyRates("EUR 2017-11-20 2017-11-24").getRates().stream()
                .map(Rates::getBid)
                .collect(Collectors.toList());

        assertThat(ProcessData.getMean(values).setScale(4, RoundingMode.HALF_UP), is(BigDecimal.valueOf(4.1815)));

    }


}