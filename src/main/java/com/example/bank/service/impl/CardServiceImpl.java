package com.bankservice.bank.service.impl;

import com.bankservice.bank.model.Card;
import com.bankservice.bank.repository.CardRepository;
import com.bankservice.bank.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public boolean activateCard(String cardNumber) {
        Optional <Card> card = cardRepository.findByCardNumber(cardNumber);
        if(card.isPresent()) {
            Card tmpCard = card.get();
            tmpCard.setActive(true);
            cardRepository.save(tmpCard);
            log.info("Card activated: {}", tmpCard);
            return true;
        }
        return false;
    }

    @Override
    public Optional <Card> findByCardnumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }
}
