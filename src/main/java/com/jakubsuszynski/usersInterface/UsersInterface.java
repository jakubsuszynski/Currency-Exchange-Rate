package com.jakubsuszynski.usersInterface;

import com.jakubsuszynski.apiclient.RestClient;
import com.jakubsuszynski.restresponse.Rates;
import com.jakubsuszynski.restresponse.RestResponse;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UsersInterface {

    private RestClient restClient = new RestClient();
    public static final String PATTERN = "[a-zA-Z]{3}\\ \\d{4}\\-\\d{2}\\-\\d{2}\\ \\d{4}\\-\\d{2}\\-\\d{2}";


    public void runApplication() {
        Scanner scanner = new Scanner(System.in);
        boolean petla = true;

        while (petla) {

            System.out.println("Podaj kod waluty, datę początkową i datę końcową w formacie CCC YYYY-MM-DD YYYY-MM-DD, np.  "
                    + "EUR 2017-11-20 2017-11-24. "
                    + "Maksymalny odstęp to 367 dni");
            String input = scanner.nextLine();


            if (!input.matches(PATTERN)) {
                System.out.println("Zły format danych.");
                continue;
            }


            Optional<RestResponse> optionalRestResponse = Optional.ofNullable(restClient.getCurrencyRates(input));

            printOutput(optionalRestResponse);

        }

    }

    private void printOutput(Optional<RestResponse> optionalRestResponse) {
        if (optionalRestResponse.isPresent()) {


            List<BigDecimal> bids = optionalRestResponse.get().getRates().stream()
                    .map(Rates::getBid)
                    .collect(Collectors.toList());

            List<BigDecimal> asks = optionalRestResponse.get().getRates().stream()
                    .map(Rates::getAsk)
                    .collect(Collectors.toList());

            System.out.println(getMean(bids));
            System.out.println(new DecimalFormat("0.0000").format(getStandardDeviation(asks)));

        } else
            System.out.println("Błędny zakres dat lub brak danych w systemie ");
    }


    private BigDecimal getMean(List<BigDecimal> values) {

        BigDecimal sum = BigDecimal.valueOf(0);
        for (BigDecimal value : values) {
            sum = sum.add(value);
        }
        return sum.divide(BigDecimal.valueOf(values.size()));

    }

    private Double getStandardDeviation(List<BigDecimal> values) {
        BigDecimal mean = getMean(values);
        BigDecimal squareStandardDeviation = BigDecimal.ZERO;

        for (BigDecimal value : values) {
            squareStandardDeviation = squareStandardDeviation.add(value.subtract(mean).multiply(value.subtract(mean)));
        }
        squareStandardDeviation = squareStandardDeviation.divide(BigDecimal.valueOf(values.size()));


        return Math.sqrt(squareStandardDeviation.doubleValue());
    }
}
