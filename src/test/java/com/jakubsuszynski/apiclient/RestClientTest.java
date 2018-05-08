package com.jakubsuszynski.apiclient;

import com.jakubsuszynski.RestResponse.RestResponse;
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
    public void check() {
        restResponse = restClient.getRequest("eur", "2017-11-20", "2017-11-20");
        assertThat(restResponse.getRates().get(0).getBid(), is(BigDecimal.valueOf(4.1943)));
    }

}