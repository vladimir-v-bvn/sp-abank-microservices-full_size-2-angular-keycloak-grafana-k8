package com.vber.abank.crdcard_service.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "crdcard")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class CrdCard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "accountnumber")
  private int accountNumber;
  @Column(name = "crdcardnumber")
  private String crdCardNumber;
  @Column(name = "crdcardlimit")
  private BigDecimal crdCardLimit;
}


