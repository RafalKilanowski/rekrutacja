package com.recruitment.validation;

import com.recruitment.shared.time.TimeService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PeselValidatorTest {

  @Mock
  private TimeService timeService;

  private PeselValidator validator;

  @BeforeEach
  public void setUp() {
    validator = new PeselValidator(timeService);
  }


  @Test
  public void shouldReturnFalseWhenPeselIsInvalid() {
    //given
    String invalidPesel = "95061134387";

    //when
    boolean valid = validator.isValid(invalidPesel, null);

    //then
    Assertions.assertFalse(valid);
  }

  @Test
  public void shouldReturnFalseWhenPeselIsUnder18YearsOld() {
    //given
    String invalidPesel = "06230267529";
    Instant instant = LocalDate.of(2020, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant();
    Mockito.when(timeService.getCurrentTime()).thenReturn(instant);

    //when
    boolean valid = validator.isValid(invalidPesel, null);

    //then
    Assertions.assertFalse(valid);
  }

  @Test
  public void shouldReturnTrueWhenPeselIsValidAndOver18YearsOld() {
    //given
    String invalidPesel = "06230267529";
    Instant instant = LocalDate.of(2040, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant();
    Mockito.when(timeService.getCurrentTime()).thenReturn(instant);

    //when
    boolean valid = validator.isValid(invalidPesel, null);

    //then
    Assertions.assertTrue(valid);
  }

}