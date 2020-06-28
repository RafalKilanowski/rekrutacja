package com.recruitment.domain.view;

import com.recruitment.shared.model.Currency;
import java.math.BigDecimal;
import lombok.Value;

@Value
public class SubAccountView {

  private final Currency currency;

  private final BigDecimal value;

}
