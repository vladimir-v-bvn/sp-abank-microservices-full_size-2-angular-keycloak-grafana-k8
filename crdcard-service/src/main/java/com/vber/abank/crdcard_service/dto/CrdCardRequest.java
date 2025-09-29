package com.vber.abank.crdcard_service.dto;

import java.math.BigDecimal;

public record CrdCardRequest(
    int accountNumber,
    String crdCardNumber,
    BigDecimal crdCardLimit) {}
