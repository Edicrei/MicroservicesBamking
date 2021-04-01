package com.bankservice.bank.repository;

import com.bankservice.bank.model.AtmMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmMachineRepository extends CrudRepository <AtmMachine, Long> {
}
