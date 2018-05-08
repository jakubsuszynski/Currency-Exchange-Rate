package com.jakubsuszynski.apiclient;

import com.jakubsuszynski.RestResponse.RestResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class RestClient {

    public RestResponse getRequest(String code, String start, String end) {
        String address = "http://api.nbp.pl/api/exchangerates/rates/c/" + code + "/" + start + "/" + end + "/?format=json";
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(address);
        Response response = webTarget.request().get();

        RestResponse restResponse = response.readEntity(RestResponse.class);
        response.close();
        return restResponse;

    }
}
