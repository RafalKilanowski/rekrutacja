package com.recruitment.api;

import com.recruitment.domain.AccountFacade;
import com.recruitment.domain.request.ExchangeCurrencyRequest;
import com.recruitment.domain.view.AccountView;
import com.recruitment.dto.ExchangeCurrencyDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class ExchangeCurrencyController {

  private final AccountFacade facade;

  @PostMapping("/{pesel}/exchange")
  public ResponseEntity<AccountView> exchange(@Valid @RequestBody ExchangeCurrencyDto dto, @PathVariable String pesel) {
    ExchangeCurrencyRequest request = ExchangeCurrencyRequest.builder()
        .pesel(pesel)
        .amount(dto.getAmount())
        .currencyFrom(dto.getCurrencyFrom())
        .currencyTo(dto.getCurrencyTo())
        .build();

    AccountView accountView = facade.exchange(request);
    return new ResponseEntity<>(accountView, HttpStatus.ACCEPTED);
  }


}
