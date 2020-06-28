package com.recruitment.shared.external;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
class RateResponse {
  private String table;
  private String currency;
  private String code;
  private List<Table> rates;
}