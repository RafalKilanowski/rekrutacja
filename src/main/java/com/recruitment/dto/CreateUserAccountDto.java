package com.recruitment.dto;

import com.recruitment.validation.Pesel;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserAccountDto {

  @NotNull(message = "Name must not be null!")
  private String name;

  @NotEmpty(message = "Surname must not be null!")
  private String surname;


  @Pesel(message = "Invalid pesel !")
  private String pesel;

  @DecimalMin(value = "0.0", inclusive = false, message = "Account balance must be higher than 0!")
  @Digits(integer = 10, fraction = 2)
  @NotNull(message = "Account balance must not be null!")
  private BigDecimal accountBalancePLN;
}
