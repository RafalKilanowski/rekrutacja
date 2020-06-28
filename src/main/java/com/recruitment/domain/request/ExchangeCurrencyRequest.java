package com.recruitment.domain.request;

import com.recruitment.shared.model.Currency;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ExchangeCurrencyRequest {

  @NonNull
  private final String pesel;

  @NonNull
  private Currency currencyFrom;

  @NonNull
  private Currency currencyTo;

  @NonNull
  private BigDecimal amount;

}
