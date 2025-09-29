package com.vber.abank.account_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Document(value = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Account {
  @Id
  private String id;
  private long accountNumber;
}
