package com.vber.abank.account_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountServiceApplicationTests {
  
  @ServiceConnection
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

  @LocalServerPort
  private int port;
  @BeforeEach
  void setUp() {
    
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }  
  
  static {
    mongoDBContainer.start();
//  System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
  }
  
  @Test
	void shouldCreateAccount() {
	  String requestBody = """
        {
          "accountNumber": 20000002
        }
        """;
    RestAssured.given()
      .contentType("application/json")
      .body(requestBody)
      .when()
      .post("/api/account")
      .then()
      .body("id", Matchers.notNullValue())
      .body("accountNumber", Matchers.equalTo(20000002)  )
      ;
    }
}
