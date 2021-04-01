package com.bankservice.bank.repository;

import com.bankservice.bank.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends CrudRepository <Card, Long> {
    Optional <Card> findByCardNumber(String cardNumber);
}
