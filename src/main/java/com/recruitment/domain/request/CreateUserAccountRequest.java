package com.recruitment.domain.request;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CreateUserAccountRequest {

  @NonNull
  private final String name;

  @NonNull
  private final String surname;

  @NonNull
  private final String pesel;

  @NonNull
  private final BigDecimal accountBalancePLN;
}
