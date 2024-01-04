package com.kirana.register.sevice;

import com.kirana.register.response.CurrencyApiResponse;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

//import org.apache.logging.log4j.Logger;

@Service
public class CurrencyConversionService {
//    private  static  final Logger logger = (Logger) LoggerFactory.getLogger(CurrencyConversionService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${currency.api.url}") // Add this property in your application.properties or application.yml
    private String apiUrl;

    double convertToINR(double amount) {
        double convertedAmount = amount;
        ResponseEntity<CurrencyApiResponse> response = null;
        try {
                response = restTemplate.getForEntity(apiUrl, CurrencyApiResponse.class);

        } catch (Exception e) {
//            logger.error("Error occured while Calling the API " + e);
        }
        if(response != null && response.getStatusCode() != HttpStatusCode.valueOf(404)) {
            Map<String, Double> ratesMap = response.getBody().rates;
            double conversionRate = ratesMap.get("INR");
            convertedAmount = conversionRate * amount;
        }
        return convertedAmount;
    }
}
