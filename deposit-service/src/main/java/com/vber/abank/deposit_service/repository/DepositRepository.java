package com.vber.abank.deposit_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vber.abank.deposit_service.model.Deposit;

import io.micrometer.observation.annotation.Observed;

import java.util.List;
import java.util.Optional;

@Repository
@Observed
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

  List<Deposit> findAllByAccountNumber(int accountNumber);
  
  @Query("SELECT d FROM Deposit d WHERE d.id = :id AND d.dueDate < current_date")
  Optional<Deposit> findByIdAndOverDueJPQL(@Param("id") int id);
}
