package com.bankservice.bank.repository;

import com.bankservice.bank.model.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository <Activity, Long> {
}
