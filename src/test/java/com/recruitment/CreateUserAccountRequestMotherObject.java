package com.recruitment;

import com.recruitment.domain.request.CreateUserAccountRequest;
import java.math.BigDecimal;

public class CreateUserAccountRequestMotherObject {

  public static final int MILLION = 1000000;

  public static CreateUserAccountRequest aRequest(String pesel) {
    return CreateUserAccountRequest.builder()
        .pesel(pesel)
        .accountBalancePLN(BigDecimal.valueOf(MILLION))
        .name("Rafal")
        .surname("Keller")
        .build();
  }

  public static CreateUserAccountRequest aRequest(String pesel, BigDecimal initValue) {
    return CreateUserAccountRequest.builder()
        .pesel(pesel)
        .accountBalancePLN(initValue)
        .name("Rafal")
        .surname("Keller")
        .build();
  }
}
