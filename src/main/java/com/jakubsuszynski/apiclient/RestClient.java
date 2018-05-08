package com.jakubsuszynski.apiclient;

import com.jakubsuszynski.restresponse.RestResponse;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class RestClient {


    public RestResponse getCurrencyRates(String input) {
        String[] inputDivided = input.split(" ");
        String address = "http://api.nbp.pl/api/exchangerates/rates/c/"
                + inputDivided[0] + "/"
                + inputDivided[1] + "/"
                + inputDivided[2];

        RestResponse restResponse = makeRequest(address);
        return restResponse;

    }


    private RestResponse makeRequest(String address) {

        Response response = null;
        RestResponse restResponse = null;

        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(address);
            response = webTarget.request().header("Accept:", "application/json").get();
            restResponse = response.readEntity(RestResponse.class);

        } catch (ProcessingException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            response.close();
        }

        return restResponse;
    }

}
