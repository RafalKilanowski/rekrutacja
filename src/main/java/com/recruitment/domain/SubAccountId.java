package com.recruitment.domain;

import com.recruitment.shared.model.Currency;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SubAccountId implements Serializable {

  @Column(name = "sub_pesel")
  private String pesel;

  @Column(name = "sub_currency")
  private Currency currency;
}
