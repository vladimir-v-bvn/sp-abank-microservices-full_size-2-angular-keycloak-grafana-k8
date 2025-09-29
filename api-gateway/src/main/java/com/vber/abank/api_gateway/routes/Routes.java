package com.vber.abank.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

  @Value("${account.service.url}")
  private String accountServiceUrl;
  @Value("${crdcard.service.url}")
  private String crdcardServiceUrl;
  @Value("${deposit.service.url}")
  private String depositServiceUrl;

  @SuppressWarnings("deprecation")
  @Bean
  RouterFunction<ServerResponse> accountServiceRoute() {
  return GatewayRouterFunctions.route("account_service")
      .route(RequestPredicates.path("api/account"), HandlerFunctions.http(accountServiceUrl))
      .filter(CircuitBreakerFilterFunctions.circuitBreaker("accountServiceCircuitBreaker"))
      .build();
  }
  @SuppressWarnings("deprecation")
  @Bean
  RouterFunction<ServerResponse> crdCardServiceRoute() {
  return GatewayRouterFunctions.route("crdcard_service")
      .route(RequestPredicates.path("api/crdcard"), HandlerFunctions.http(crdcardServiceUrl))
      .filter(CircuitBreakerFilterFunctions.circuitBreaker("accountServiceCircuitBreaker"))
      .build();
  }
  @SuppressWarnings("deprecation")
  @Bean
  RouterFunction<ServerResponse> depositServiceRoute() {
  return GatewayRouterFunctions.route("deposit_service")
      .route(RequestPredicates.path("api/deposit"), HandlerFunctions.http(depositServiceUrl))
      .filter(CircuitBreakerFilterFunctions.circuitBreaker("accountServiceCircuitBreaker"))
      .build();
  }

  @SuppressWarnings("deprecation")
  @Bean
  RouterFunction<ServerResponse> productServiceSwaggerRoute() {
      return GatewayRouterFunctions.route("product_service_swagger")
              .route(RequestPredicates.path("/aggregate/account-service/api-docs"), HandlerFunctions.http(accountServiceUrl))
//            .filter("/api-docs")
              .build();
  }

  @SuppressWarnings("deprecation")
  @Bean
  RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
      return GatewayRouterFunctions.route("order_service_swagger")
              .route(RequestPredicates.path("/aggregate/crdcard-service/api-docs"), HandlerFunctions.http(crdcardServiceUrl))
//            .filter(setPath("/api-docs"))
              .build();
  }

  @SuppressWarnings("deprecation")
  @Bean
  RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
      return GatewayRouterFunctions.route("inventory_service_swagger")
              .route(RequestPredicates.path("/aggregate/deposit-service/api-docs"), HandlerFunctions.http(depositServiceUrl))
//            .filter(setPath("/api-docs"))
              .build();
  }
  
}
