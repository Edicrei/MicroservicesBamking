package com.bankservice.bank.service;

import com.bankservice.bank.model.Card;

import java.util.Optional;

public interface CardService {

    boolean activateCard(String cardNumber);

    Optional<Card> findByCardnumber(String cardNumber);
}
