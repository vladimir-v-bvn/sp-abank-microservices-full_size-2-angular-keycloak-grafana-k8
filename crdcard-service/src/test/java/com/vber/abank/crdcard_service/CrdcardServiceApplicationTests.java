package com.vber.abank.crdcard_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
//import org.testcontainers.containers.MongoDBContainer;

import com.vber.abank.crdcard_service.stubs.DepositClientStub;

//import com.vber.abank.account_service.TestcontainersConfiguration;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class CrdcardServiceApplicationTests {

  @LocalServerPort
  private int port;
  @BeforeEach
  void setUp() {
    
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }  
  
  @Test
  void shouldCreateCrediCard() {

    DepositClientStub.stubDepositCall(20000002);
    
    String requestBody = """
        {
          "accountNumber": 20000002,
          "crdCardNumber": "1234555590126666",
          "crdCardLimit": "50000"
        }
        """;
    
    RestAssured.given()
      .contentType("application/json")
      .body(requestBody)
      .when()
      .post("/api/crdcard")
      .then()
      .log().all()
      .statusCode(201)
//    .body("id", Matchers.notNullValue())
//    .body("accountNumber", Matchers.equalTo(20000002))
      .extract()
      .body().asString()
      ;
    }
}
