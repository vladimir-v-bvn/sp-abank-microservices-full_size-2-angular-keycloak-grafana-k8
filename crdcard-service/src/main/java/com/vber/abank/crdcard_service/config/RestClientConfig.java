package com.vber.abank.crdcard_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
  
  @Value("${deposit.service.url}")
  private String depositServiceUrl;
  private final ObservationRegistry observationRegistry;

  @Bean
  RestClientDeposit restClientDeposit() {
    RestClient restClient = RestClient.builder()
        .baseUrl(depositServiceUrl)
        .requestFactory(getClientRequestFactory())
        .observationRegistry(observationRegistry)
        .build();
    var restClientAdapter = RestClientAdapter.create(restClient);
    var httpServiceProxyFactory = HttpServiceProxyFactory
        .builderFor(restClientAdapter)
        .build();
      return httpServiceProxyFactory.createClient(RestClientDeposit.class);
  }

  private ClientHttpRequestFactory getClientRequestFactory() {
    
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
      requestFactory.setConnectTimeout(30000);
      requestFactory.setReadTimeout(30000);
      requestFactory.setConnectionRequestTimeout(30000);
    
//    ClientHttpRequestFactorySettings clientHttpRequestFactory = new ClientHttpRequestFactorySettings.DEFAULTS
//        .withConnectTimeout(java.time.Duration.ofSeconds(3))
//        .withReadTimeout(java.time.Duration.ofSeconds(3));
    return null;
  }
}
