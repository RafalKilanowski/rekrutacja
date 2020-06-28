package com.recruitment;

import com.recruitment.shared.external.RateExternalService;
import com.recruitment.shared.external.RateExternalView;
import com.recruitment.shared.model.Currency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.recruitment")
public class TestConfiguration {

  @Bean
  @Primary
  RateExternalService rateExternalService() {
    return new RateExternalService() {
      @Override
      public RateExternalView getBidRate(Currency currency) {
        return new RateExternalView("USD", 5.0f);
      }

      @Override
      public RateExternalView getAskRate(Currency currency) {
        return new RateExternalView("USD", 5.0f);
      }
    };
  }
}
