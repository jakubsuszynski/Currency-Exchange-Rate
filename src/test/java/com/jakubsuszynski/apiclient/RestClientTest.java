package com.jakubsuszynski.apiclient;

import com.jakubsuszynski.restresponse.RestResponse;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
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


}