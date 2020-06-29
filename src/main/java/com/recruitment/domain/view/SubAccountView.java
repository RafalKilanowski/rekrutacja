package com.recruitment.domain.view;

import com.recruitment.shared.model.Currency;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubAccountView {

  private Currency currency;

  private BigDecimal value;

}
