package com.recruitment.shared.time;

import java.time.Clock;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
class TimeServiceImpl implements TimeService {

  @Override
  public Instant getCurrentTime() {
    return Instant.now(Clock.systemUTC());
  }

}
