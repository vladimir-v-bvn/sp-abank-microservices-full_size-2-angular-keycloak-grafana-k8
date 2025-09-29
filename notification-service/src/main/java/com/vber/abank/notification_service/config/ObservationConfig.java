package com.vber.abank.notification_service.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
@RequiredArgsConstructor
public class ObservationConfig {

  @SuppressWarnings("rawtypes")
  private final ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;

  @PostConstruct
  public void setObservationForKafkaTemplate() {
      concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
  }

  @Bean
  ObservedAspect observedAspect(ObservationRegistry registry) {
      return new ObservedAspect(registry);
  }
}
