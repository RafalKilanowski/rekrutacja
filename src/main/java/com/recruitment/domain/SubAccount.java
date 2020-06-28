package com.recruitment.domain;

import com.recruitment.shared.model.Currency;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SubAccount {

  @EmbeddedId
  private SubAccountId id;

  @NotNull
  private BigDecimal value;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pesel", nullable = false)
  private Account account;

  @NotNull
  private Instant creationTime;


  public Currency getCurrency() {
    return id.getCurrency();
  }

  void add(BigDecimal v) {
    value = value.add(v).setScale(2, RoundingMode.DOWN);
  }

  void reduce(BigDecimal v) {
    value = value.subtract(v).setScale(2, RoundingMode.DOWN);
  }

}
