package com.recruitment.domain.view;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountView {

  private final String pesel;

  private final String name;

  private final String surname;

  private final List<SubAccountView> subAccount;
}
