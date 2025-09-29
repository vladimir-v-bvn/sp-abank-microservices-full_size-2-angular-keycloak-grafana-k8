package com.vber.abank.account_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vber.abank.account_service.dto.AccountRequest;
import com.vber.abank.account_service.dto.AccountResponse;
import com.vber.abank.account_service.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
  
  private final AccountService accountService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountResponse createAccount(@RequestBody AccountRequest accountRequest) {
    return accountService.createAccount(accountRequest);
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<AccountResponse> getAllAccounts() {
    return accountService.getAllAccounts();
  }
  
}
