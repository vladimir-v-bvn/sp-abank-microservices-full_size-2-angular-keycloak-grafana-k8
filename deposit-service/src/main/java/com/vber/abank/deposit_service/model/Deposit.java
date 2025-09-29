package com.vber.abank.deposit_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deposit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deposit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "accountnumber")
  private int accountNumber;
  @Column(name = "amount")
  private BigDecimal amount;
  @Column(name = "duedate")
  private LocalDate dueDate;
}
