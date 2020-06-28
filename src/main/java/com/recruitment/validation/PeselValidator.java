package com.recruitment.validation;

import com.recruitment.shared.time.TimeService;
import java.time.LocalDate;
import java.time.ZoneOffset;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PeselValidator implements ConstraintValidator<Pesel, String> {

  private static final String ONLY_NUMBER_REGEX = "[0-9]+";
  private static final int PESEL_SIZE = 11;
  private static final int AGE_18 = 18;
  private static final int MONTHS_ABOVE_2000 = 20;
  private static final int YEAR_2000 = 2000;

  private final TimeService timeService;

  @Override
  public void initialize(Pesel constraintAnnotation) {

  }

  @Override
  public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {
    if (pesel == null || !pesel.matches(ONLY_NUMBER_REGEX)) {
      return false;
    }
    if (pesel.length() != PESEL_SIZE) {
      return false;
    }

    return assertPeselIsValid(pesel) && assertPeselOfAge18(pesel);
  }

  private boolean assertPeselOfAge18(String pesel) {
    int year = Integer.parseInt(pesel.substring(0, 2));
    int month = Integer.parseInt(pesel.substring(2, 4));
    int day = Integer.parseInt(pesel.substring(4, 6));
    if (month < MONTHS_ABOVE_2000) {
      return true;
    }
    year = year + YEAR_2000;
    month = month - MONTHS_ABOVE_2000;
    LocalDate birthDate = LocalDate.of(year, month, day);
    LocalDate currentDate = LocalDate.ofInstant(timeService.getCurrentTime(), ZoneOffset.UTC);
    return birthDate.plusYears(AGE_18).isBefore(currentDate) || birthDate.plusYears(AGE_18).isEqual(currentDate);
  }

  private boolean assertPeselIsValid(String pesel) {
    int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
    int j = 0;
    int sum = 0;
    int control = 0;

    int csum = Integer.parseInt(pesel.substring(pesel.length() - 1));
    for (int i = 0; i < pesel.length() - 1; i++) {
      char c = pesel.charAt(i);
      j = Integer.valueOf(String.valueOf(c));
      sum += j * weights[i];
    }
    control = 10 - (sum % 10);
    if (control == 10) {
      control = 0;
    }
    return (control == csum);
  }
}
