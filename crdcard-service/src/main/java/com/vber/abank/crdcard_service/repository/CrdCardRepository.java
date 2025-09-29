package com.vber.abank.crdcard_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vber.abank.crdcard_service.model.CrdCard;

import io.micrometer.observation.annotation.Observed;

@Repository
@Observed
public interface CrdCardRepository extends JpaRepository<CrdCard, Long> {

}
