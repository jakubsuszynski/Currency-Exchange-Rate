package com.jakubsuszynski.apiclient;

import com.jakubsuszynski.restresponse.RestResponse;
import com.jakubsuszynski.usersinterface.PatternMatcher;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class RestClient {


    public RestResponse getCurrencyRates(String input) {
        if (!PatternMatcher.inputMatches(input)) {
            return null;
        }
        String[] inputDivided = input.split(" ");
        String address = "http://api.nbp.pl/api/exchangerates/rates/c/"
                + inputDivided[0] + "/"
                + inputDivided[1] + "/"
                + inputDivided[2];

        return makeRequest(address);

    }


    private RestResponse makeRequest(String address) {


        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(address);
            Response response = webTarget.request().header("Accept:", "application/json").get();
            RestResponse restResponse = response.readEntity(RestResponse.class);
            response.close();
            return restResponse;

        } catch (ProcessingException e) {
            return null;
        }

    }

}
