package com.vber.abank.account_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vber.abank.account_service.model.Account;

import io.micrometer.observation.annotation.Observed;

@Repository
@Observed
public interface AccountRepository extends MongoRepository<Account, String>{

}
