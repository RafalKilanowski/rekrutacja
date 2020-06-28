package com.recruitment.api;

import com.recruitment.domain.AccountFacade;
import com.recruitment.domain.request.CreateUserAccountRequest;
import com.recruitment.domain.view.AccountView;
import com.recruitment.dto.CreateUserAccountDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateUserAccountController {

  private final AccountFacade facade;

  @PostMapping
  public ResponseEntity<AccountView> create(@Valid @RequestBody CreateUserAccountDto dto) {
    CreateUserAccountRequest request = from(dto);
    AccountView account = facade.create(request);
    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  private CreateUserAccountRequest from(CreateUserAccountDto dto) {
    return CreateUserAccountRequest.builder()
        .name(dto.getName())
        .surname(dto.getSurname())
        .accountBalancePLN(dto.getAccountBalancePLN())
        .pesel(dto.getPesel())
        .build();
  }
}
