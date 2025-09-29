package com.vber.abank.account_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vber.abank.account_service.dto.AccountRequest;
import com.vber.abank.account_service.dto.AccountResponse;
import com.vber.abank.account_service.model.Account;
import com.vber.abank.account_service.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
  
  private final AccountRepository accountRepository;
  
  public AccountResponse createAccount(AccountRequest accountRequest) {
    Account account = Account.builder()
      .accountNumber(accountRequest.accountNumber())
      .build();
    accountRepository.save(account);
    log.info("Account {} created", account.getId());
    return new AccountResponse(
        account.getId(),
        account.getAccountNumber()
    );
  }
  
  public List<AccountResponse> getAllAccounts() {
    return accountRepository.findAll()
      .stream()
      .map(account -> new AccountResponse(
        account.getId(),
        account.getAccountNumber()
      ))
      .toList();
  }
}