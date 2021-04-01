package com.bankservice.bank.repository;

import com.bankservice.bank.model.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends CrudRepository <Bank, Long> {
}
