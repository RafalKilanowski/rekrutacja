package com.recruitment.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.recruitment.CreateUserAccountRequestMotherObject;
import com.recruitment.TestConfiguration;
import com.recruitment.domain.request.CreateUserAccountRequest;
import com.recruitment.domain.request.ExchangeCurrencyRequest;
import com.recruitment.domain.view.AccountView;
import com.recruitment.domain.view.SubAccountView;
import com.recruitment.shared.exceptions.BadRequestException;
import com.recruitment.shared.exceptions.ResourceAlreadyExistsException;
import com.recruitment.shared.exceptions.ResourceNotExistsException;
import com.recruitment.shared.model.Currency;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
class AccountFacadeTest {

  public static final String PESEL = "93010123456";
  public static final String PESEL_1 = "91010123456";
  public static final String PESEL_2 = "90010123456";
  public static final String PESEL_3 = "94010123456";

  @Autowired
  private AccountFacade accountFacade;


  @Test
  public void shouldThrowResourceAlreadyExistsException() {
    //given
    CreateUserAccountRequest request = CreateUserAccountRequestMotherObject.aRequest(PESEL);
    accountFacade.create(request);


    //when
    assertThrows(ResourceAlreadyExistsException.class, () -> {
      accountFacade.create(request);
    });
    //then exception is thrown
  }


  @Test
  public void shouldCreateAccountWithPLNSubaccount() {
    //given
    CreateUserAccountRequest request = CreateUserAccountRequestMotherObject.aRequest(PESEL_1);

    //when
    AccountView accountView = accountFacade.create(request);

    //then
    assertEquals(accountView.getName(), request.getName());
    assertEquals(accountView.getSurname(), request.getSurname());
    assertEquals(accountView.getPesel(), request.getPesel());
    assertEquals(accountView.getSubAccount().size(), 2);

    SubAccountView plnSubAccount = accountView.getSubAccount().stream().filter(sa -> sa.getCurrency().equals(Currency.PLN)).findFirst().get();
    assertEquals(plnSubAccount.getValue(), request.getAccountBalancePLN());

    SubAccountView usdSubAccount = accountView.getSubAccount().stream().filter(sa -> sa.getCurrency().equals(Currency.USD)).findFirst().get();
    assertEquals(usdSubAccount.getValue(), BigDecimal.ZERO);
  }

  @Test
  public void shouldThrowResourceNotFoundExceptionWhenAccountForGivenPeselDoesNotExist() {
    //when
    assertThrows(ResourceNotExistsException.class, () -> {
      accountFacade.find("nonExistingPesel");
    });

    //then exception is thrown
  }

  @Test
  public void shouldThrowBadRequestExceptionWhenTryingToExchangeWithSameCurrencies() {
    //given
    ExchangeCurrencyRequest request = ExchangeCurrencyRequest.builder()
        .currencyTo(Currency.PLN)
        .currencyFrom(Currency.PLN)
        .pesel("pesel")
        .amount(BigDecimal.ONE)
        .build();

    //when
    assertThrows(BadRequestException.class, () -> {
      accountFacade.exchange(request);
    });
    //then exception is thrown
  }


  @Test
  public void shouldThrowBadRequestExceptionWhenAmountIsBiggerThanAmountInSubAccount() {
    //given
    CreateUserAccountRequest request1 = CreateUserAccountRequestMotherObject.aRequest(PESEL_2);
    accountFacade.create(request1);
    ExchangeCurrencyRequest request2 = ExchangeCurrencyRequest.builder()
        .currencyTo(Currency.USD)
        .currencyFrom(Currency.PLN)
        .pesel(PESEL_2)
        .amount(BigDecimal.valueOf(1000000000000l))
        .build();

    //when
    assertThrows(BadRequestException.class, () -> {
      accountFacade.exchange(request2);
    });
    //then exception is thrown
  }

  @Test
  public void shouldProperlyExchangeFromPLNtoUSD() {
    //given
    CreateUserAccountRequest request1 = CreateUserAccountRequestMotherObject.aRequest(PESEL_3, BigDecimal.valueOf(4000));
    accountFacade.create(request1);
    ExchangeCurrencyRequest request2 = ExchangeCurrencyRequest.builder()
        .currencyTo(Currency.USD)
        .currencyFrom(Currency.PLN)
        .pesel(PESEL_3)
        .amount(BigDecimal.valueOf(2000))
        .build();

    //when
    AccountView accountView = accountFacade.exchange(request2);

    //then
    SubAccountView plnSubAccount = accountView.getSubAccount().stream().filter(sa -> sa.getCurrency().equals(Currency.PLN)).findFirst().get();
    assertEquals(plnSubAccount.getValue().setScale(2), BigDecimal.valueOf(2000).setScale(2));

    SubAccountView usdSubAccount = accountView.getSubAccount().stream().filter(sa -> sa.getCurrency().equals(Currency.USD)).findFirst().get();
    assertEquals(usdSubAccount.getValue().setScale(2), BigDecimal.valueOf(400).setScale(2));


  }


}