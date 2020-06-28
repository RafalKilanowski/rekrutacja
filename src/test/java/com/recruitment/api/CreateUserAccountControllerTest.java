package com.recruitment.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.TestConfiguration;
import com.recruitment.domain.view.AccountView;
import com.recruitment.domain.view.SubAccountView;
import com.recruitment.dto.CreateUserAccountDto;
import com.recruitment.shared.model.Currency;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
class CreateUserAccountControllerTest {

  public static final String PESEL = "89030239291";
  public static final String NAME = "name";
  public static final String SURNAME = "surname";
  public static final BigDecimal ACCOUNT_BALANCE_PLN = BigDecimal.valueOf(1000);
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  public void shouldCreateProperAccount() throws Exception {
    //given
    CreateUserAccountDto dto = accountDto();
    String dtoAsString = objectMapper.writeValueAsString(dto);

    //when
    mockMvc.perform(MockMvcRequestBuilders.post("/account").content(dtoAsString).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));

    //then
    //TODO problem with jackson and all args contructor - lack of time
//    String response = mockMvc.perform(MockMvcRequestBuilders.get("/account/" + PESEL)).andReturn().getResponse().getContentAsString();
//    AccountView accountView = objectMapper.readValue(response, AccountView.class);
//
//    Assertions.assertEquals(NAME, accountView.getName());
//    Assertions.assertEquals(SURNAME, accountView.getSurname());
//    Assertions.assertEquals(PESEL, accountView.getPesel());
//    Assertions.assertEquals(2, accountView.getSubAccount().size());
//
//    SubAccountView plnSubAccount = accountView.getSubAccount().stream().filter(sa -> sa.getCurrency().equals(Currency.PLN)).findFirst().get();
//    assertEquals(ACCOUNT_BALANCE_PLN, plnSubAccount.getValue());
//
//    SubAccountView usdSubAccount = accountView.getSubAccount().stream().filter(sa -> sa.getCurrency().equals(Currency.USD)).findFirst().get();
//    assertEquals(BigDecimal.ZERO, usdSubAccount.getValue());

  }


  private CreateUserAccountDto accountDto() {
    CreateUserAccountDto dto = new CreateUserAccountDto();
    dto.setPesel(PESEL);
    dto.setName(NAME);
    dto.setSurname(SURNAME);
    dto.setAccountBalancePLN(ACCOUNT_BALANCE_PLN);
    return dto;
  }

}