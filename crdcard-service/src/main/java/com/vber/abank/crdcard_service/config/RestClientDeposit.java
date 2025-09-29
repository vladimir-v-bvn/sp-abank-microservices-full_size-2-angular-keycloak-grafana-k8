package com.vber.abank.crdcard_service.config;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

//@Slf4j
public interface RestClientDeposit {
  
  org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RestClientDeposit.class);
  
  @GetExchange("/api/deposit")
  @CircuitBreaker(name = "deposit", fallbackMethod = "fallbackMethod")
  @Retry(name = "deposit")
  
  boolean checkDepositStatus(@RequestParam("accountNumber") int accountNumber);
  default boolean fallbackMethod(int accountNumber, Throwable throwable) {
    log.info("Can't reach Deposit Service for " + accountNumber + " - {}", throwable.toString());
    return false;
  }
}
