package com.recruitment.shared.external;

import com.recruitment.shared.model.Currency;

public interface RateExternalService {

  RateExternalView getBidRate(Currency currency);

  RateExternalView getAskRate(Currency currency);

}
