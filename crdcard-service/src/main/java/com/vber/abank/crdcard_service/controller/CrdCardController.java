package com.vber.abank.crdcard_service.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vber.abank.crdcard_service.dto.CrdCardRequest;
import com.vber.abank.crdcard_service.service.CrdCardService;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/crdcard")
@RequiredArgsConstructor
@Slf4j
public class CrdCardController {
  
  private final CrdCardService crdCardService;
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
//  @CircuitBreaker(name = "deposit", fallbackMethod = "fallbackMethod")
//  @TimeLimiter(name = "deposit")
//  @Retry(name = "deposit")
  public CompletableFuture<String> createCrdCard(@RequestBody CrdCardRequest crdCardRequest) {
    log.info("Creating Credit Card...");
    return CompletableFuture.supplyAsync(() -> crdCardService.createCrdCard(crdCardRequest));
  }
  
//  public CompletableFuture<String> fallbackMethod(CrdCardRequest crdCardRequest, RuntimeException runtimeException) {
//    log.info("CrdCard can't be created - Fallback");
//    return CompletableFuture.supplyAsync(() -> "CrdCard can't be created, please please try again after a short time.");
//  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public String checkCrdCard() {
    return "CreditCard is here";
  }
  
}
