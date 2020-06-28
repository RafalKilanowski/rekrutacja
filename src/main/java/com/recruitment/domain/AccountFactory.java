package com.recruitment.domain;

import com.recruitment.domain.request.CreateUserAccountRequest;
import com.recruitment.shared.model.Currency;
import com.recruitment.shared.time.TimeService;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AccountFactory {

  private final TimeService timeService;

  @Transactional
  public Account create(CreateUserAccountRequest request) {
    Account account = buildWith(request);
    return account;
  }

  private Account buildWith(CreateUserAccountRequest request) {
    Instant currentTime = timeService.getCurrentTime();

    SubAccount subAccountPLN = SubAccount.builder()
        .id(new SubAccountId(request.getPesel(), Currency.PLN))
        .value(request.getAccountBalancePLN())
        .creationTime(currentTime)
        .build();

    SubAccount subAccountUSD = SubAccount.builder()
        .id(new SubAccountId(request.getPesel(), Currency.USD))
        .value(BigDecimal.ZERO)
        .creationTime(currentTime)
        .build();

    Account account = Account.builder()
        .name(request.getName())
        .surname(request.getSurname())
        .subAccount(Arrays.asList(subAccountPLN, subAccountUSD))
        .creationTime(currentTime)
        .pesel(request.getPesel())
        .build();

    subAccountPLN.setAccount(account);
    subAccountUSD.setAccount(account);

    return account;
  }
}
