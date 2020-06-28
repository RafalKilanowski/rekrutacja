package com.recruitment.dto;

import com.recruitment.shared.model.Currency;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ExchangeCurrencyDto {

  @NotNull
  private Currency currencyFrom;

  @NotNull
  private Currency currencyTo;

  @NotNull
  @Digits(integer = 10, fraction = 2)
  @DecimalMin(value = "0.0", inclusive = false, message = "Value must be higher than 0!")
  private BigDecimal amount;

}
