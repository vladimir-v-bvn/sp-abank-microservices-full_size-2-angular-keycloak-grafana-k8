package com.vber.abank.crdcard_service.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "deposit", url = "http://localhost:8083")
//@FeignClient(name = "deposit", url = "${deposit.url}")
public interface FeignClientDeposit {

  @GetMapping("/api/deposit")
  boolean checkDepositStatus(@RequestParam("accountNumber") int accountNumber);
  }
