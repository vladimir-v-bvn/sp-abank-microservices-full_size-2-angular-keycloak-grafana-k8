package com.vber.abank.crdcard_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.vber.abank.crdcard_service.config.FeignClientDeposit;
import com.vber.abank.crdcard_service.config.RestClientDeposit;
import com.vber.abank.crdcard_service.dto.CrdCardRequest;
import com.vber.abank.crdcard_service.event.CrdCardCreatedEvent;
import com.vber.abank.crdcard_service.model.CrdCard;
import com.vber.abank.crdcard_service.repository.CrdCardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrdCardService {

  private final CrdCardRepository crdCardRepository;
  private final WebClient.Builder webClientBuilder;
  private final FeignClientDeposit feignClientDeposit;
  private final RestClientDeposit restClientDeposit;
  private final KafkaTemplate<String, CrdCardCreatedEvent>  kafkaTemplate;
  
  private boolean result;

  @Value("${deposit.service.url}")
  private String depositServiceUrl;
  
  public String createCrdCard(CrdCardRequest crdCardRequest) {

    CrdCard crdCard = CrdCard.builder()
        .accountNumber(crdCardRequest.accountNumber())
        .crdCardNumber(crdCardRequest.crdCardNumber())
        .crdCardLimit(crdCardRequest.crdCardLimit())
        .build();

    result = webClientBuilder.build().get()
        .uri(depositServiceUrl + "/api/deposit",
  //    .uri("http://deposit-service/api/deposit",
  //uriBuilder ->
  //          uriBuilder.path("/deposit/{depositId}").build(crdCard.getId())
    uriBuilder ->
              uriBuilder.queryParam("accountNumber", crdCard.getAccountNumber()).build())
             .retrieve()
             .bodyToMono(Boolean.class)
             .block();

    result = feignClientDeposit.checkDepositStatus(crdCard.getAccountNumber());

    result = restClientDeposit.checkDepositStatus(crdCard.getAccountNumber());

    if (Boolean.FALSE.equals(result)) {
      throw new IllegalStateException("CrdCard is not created, funds are locked");
    }
    //if one of the deposits is overdue, funds can be transferred to credit card
    crdCardRepository.save(crdCard);

    log.info("CrdCard is created: {}", crdCard.getId());

    CrdCardCreatedEvent CrdCardCreatedEvent = new CrdCardCreatedEvent(
        crdCard.getCrdCardNumber(),
        "support@abank.com"
    );
    
    kafkaTemplate.send("crdcard-created", CrdCardCreatedEvent);

    return "CrdCard is created " + crdCard.getId();
  }
}   
