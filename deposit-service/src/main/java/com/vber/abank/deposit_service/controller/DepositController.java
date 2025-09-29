package com.vber.abank.deposit_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.vber.abank.deposit_service.service.DepositService;

@RestController
@RequestMapping("/api/deposit")
@RequiredArgsConstructor
public class DepositController {
  
  private final DepositService depositService;
  
  @GetMapping("/{depositId}")
  @ResponseStatus(HttpStatus.OK)
//"http://localhost:8083/api/deposit/X"
  public boolean isOverDue(@PathVariable("depositId") int depositId) {
    return depositService.isOverDue(depositId);
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
//"http://localhost:8083/api/deposit/?accountNumber=X"
//if one of the deposits is overdue, funds can be transferred to credit card
  public boolean areOverDue(@RequestParam int accountNumber) {
    return depositService.areOverDue(accountNumber);
  }
  
}