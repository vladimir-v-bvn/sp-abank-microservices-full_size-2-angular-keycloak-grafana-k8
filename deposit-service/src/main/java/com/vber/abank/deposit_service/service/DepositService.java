package com.vber.abank.deposit_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vber.abank.deposit_service.repository.DepositRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DepositService {
  
  private final DepositRepository depositRepository;
  
  public boolean isOverDue(int depositId) {
    return depositRepository.findByIdAndOverDueJPQL(depositId).isPresent();
  }
  
  //if one of the deposits is overdue, funds can be transferred to credit card
  public boolean areOverDue(int accountNumber) {
  //Thread.sleep(100000); //Resilience4j testing
    return depositRepository
             .findAllByAccountNumber(accountNumber)
             .stream()
           //.peek(deposit -> log.info(deposit.getAccountId()))
           //.peek(deposit -> log.info(String.valueOf(deposit.getDueDate())))
             .anyMatch(deposit -> deposit.getDueDate().isBefore(LocalDate.now()));
  }
}
