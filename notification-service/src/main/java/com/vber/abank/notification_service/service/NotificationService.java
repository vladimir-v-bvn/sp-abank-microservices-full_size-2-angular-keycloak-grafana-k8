package com.vber.abank.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.vber.abank.crdcard_service.event.CrdCardCreatedEvent;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Configuration
@EnableKafka
public class NotificationService {

  private final JavaMailSender javaMailSender;

  @Bean
  ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "crdcardCreated");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
    props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, KafkaAvroDeserializer.class);
    props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
    props.put("schema.registry.url", "http://localhost:8085");
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    return new DefaultKafkaConsumerFactory<>(props);
  }
  @Bean
  ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @KafkaListener(topics = "crdcard-created", groupId = "crdcardCreated")
  public void listen(CrdCardCreatedEvent crdCardCreatedEvent){
      log.info("Got a message from crdcard-created topic {}", crdCardCreatedEvent);
      sendEmail(crdCardCreatedEvent);
  }

  private void sendEmail(CrdCardCreatedEvent crdCardCreatedEvent) {
    log.info("Sending email...");

    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("support@abank.com");
      messageHelper.setTo(crdCardCreatedEvent.get("email").toString());
      messageHelper.setSubject(String.format("Your credit card number %s is created", crdCardCreatedEvent.getCrdCardNumber()));
      messageHelper.setText(String.format("""
                      Hi,

                      Your credit card number %s is now created.

                      Best Regards
                      Abank Team
                      """,
              crdCardCreatedEvent.getCrdCardNumber()));
    };
    try {
      log.info(messagePreparator.toString());
      log.info(javaMailSender.toString());
    //javaMailSender.send(messagePreparator);
      log.info("Credit card created notifcation email was sent!");
    } catch (MailException e) {
      log.error("Exception occurred when sending mail", e);
      throw new RuntimeException("Exception occurred when sending mail", e);
    }
  }

}
