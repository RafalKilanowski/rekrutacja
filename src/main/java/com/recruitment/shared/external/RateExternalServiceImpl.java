package com.recruitment.shared.external;

import com.recruitment.shared.exceptions.ServiceUnavailableException;
import com.recruitment.shared.model.Currency;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RateExternalServiceImpl implements RateExternalService {

  private static Map<String, String> params;

  static {
    params = new HashMap<>();
    params.put("format", "json");
  }

  private final String apiUrl;

  private final RestTemplate restTemplate;

  public RateExternalServiceImpl(@Value("${rates.api.url}") String apiUrl, RestTemplate restTemplate) {
    this.apiUrl = apiUrl;
    this.restTemplate = restTemplate;
  }


  @Override
  public RateExternalView getBidRate(Currency currency) {
    try {
      RateResponse rateResponse = restTemplate.getForObject(apiUrl + currency, RateResponse.class, params);
      if (rateResponse == null || rateResponse.getRates() == null || rateResponse.getRates().size() != 1) {
        //add loging -- lack of time fot that :(
        throw new ServiceUnavailableException("Rate service unavailable !");
      }
      return new RateExternalView(rateResponse.getCode(), rateResponse.getRates().get(0).getBid());
    } catch (RestClientException ex) {
      //add loging -- lack of time fot that :(
      throw new ServiceUnavailableException("Rate service unavailable !");
    }
  }

  @Override
  public RateExternalView getAskRate(Currency currency) {
    try {
      RateResponse rateResponse = restTemplate.getForObject(apiUrl + currency, RateResponse.class, params);
      if (rateResponse == null || rateResponse.getRates() == null || rateResponse.getRates().size() != 1) {
        //add loging -- lack of time fot that :(
        throw new ServiceUnavailableException("Rate service unavailable !");
      }
      return new RateExternalView(rateResponse.getCode(), rateResponse.getRates().get(0).getAsk());
    } catch (RestClientException ex) {
      //add loging -- lack of time fot that :(
      throw new ServiceUnavailableException("Rate service unavailable !");
    }
  }
}
