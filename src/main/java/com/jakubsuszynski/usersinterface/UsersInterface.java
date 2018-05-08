package com.jakubsuszynski.usersinterface;

import com.jakubsuszynski.apiclient.RestClient;
import com.jakubsuszynski.restresponse.Rates;
import com.jakubsuszynski.restresponse.RestResponse;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class UsersInterface {


    public void runApplication() {

        RestClient restClient = new RestClient();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Dane wejściowe muszą mieć format CCC YYYY-MM-DD YYYY-MM-DD, np. "
                + "EUR 2017-11-20 2017-11-24 i zatwierdź przyciskiem Enter"
                + "\nMaksymalny odstęp to 367 dni");

        while (true) {

            System.out.println("Podaj dane wejściowe: ");
            input = scanner.nextLine();


            if (PatternMatcher.inputMatches(input)) {
                System.out.println("Zły format danych.");
                continue;
            }


            Optional<RestResponse> optionalRestResponse = Optional.ofNullable(restClient.getCurrencyRates(input));

            printOutput(optionalRestResponse);

        }

    }


    private void printOutput(Optional<RestResponse> optionalRestResponse) {

        if (optionalRestResponse.isPresent()) {

            List<Double> bids = optionalRestResponse.get().getRates().stream().map(Rates::getBid).collect(toList());
            List<Double> asks = optionalRestResponse.get().getRates().stream().map(Rates::getAsk).collect(toList());


            System.out.println("Średni kurs kupna: "
                    + new DecimalFormat("#.####").format(getMean(bids)));

            System.out.println("Odchylenie standardowe kursów sprzedaży: "
                    + new DecimalFormat("#.####").format(getStandardDeviation(asks)));

        } else

            System.out.println("Błędny zakres dat, brak danych w systemie lub błąd połączenia \n");

    }


    private Double getMean(List<Double> values) {

        return values.stream().mapToDouble(Double::valueOf).average().getAsDouble();

    }

    private Double getStandardDeviation(List<Double> values) {

        Double mean = getMean(values);
        Double temp = 0.0;

        for (Double value : values) {
            temp = temp + ((value - mean) * (value - mean));
        }

        return Math.sqrt((temp / values.size()));
    }
}
