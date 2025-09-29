package com.vber.abank.crdcard_service.stubs;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

import com.github.tomakehurst.wiremock.client.MappingBuilder;

public class DepositClientStub {
  public static void stubDepositCall(int accountNumber) {
    ((MappingBuilder) stubFor(get(urlEqualTo("api/deposit?accountNumber=" + accountNumber))))
      .willReturn(aResponse()
        .withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBody("true"));
    
  }
}
