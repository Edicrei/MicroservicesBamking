package com.bankservice.bank.repository;

import com.bankservice.bank.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository <BankAccount, Long> {

}
