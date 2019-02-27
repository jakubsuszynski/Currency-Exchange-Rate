package com.jakubsuszynski.apiclient;

import com.jakubsuszynski.restresponse.RestResponse;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class RestClientTest {

    private RestClient restClient;
    private RestResponse restResponse;

    @Before
    public void setUp() {
        restClient = new RestClient();

    }

    @Test
    public void checkCurrencyRates() {
        restResponse = restClient.getCurrencyRates("eur 2017-11-20 2017-11-24");
        assertThat(restResponse.getRates().get(3).getBid(), is(BigDecimal.valueOf(4.1721)));
    }

    @Test
    public void wrongDateRange() {
        restResponse = restClient.getCurrencyRates("eur 2015-11-20 2017-21-43");
        assertNull(restResponse);
    }

    @Test
    public void wrongDateFormat() {
        restResponse = restClient.getCurrencyRates("dfsd-43");
        assertNull(restResponse);
    }

    @Test
    public void switchedDates() {
        restResponse = restClient.getCurrencyRates("USD 2015-11-20 2014-10-23");
        assertNull(restResponse);
    }


}