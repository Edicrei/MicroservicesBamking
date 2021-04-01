package com.bankservice.bank.service.impl;

import com.bankservice.bank.model.*;
import com.bankservice.bank.repository.AtmMachineRepository;
import com.bankservice.bank.repository.BankAccountRepository;
import com.bankservice.bank.repository.CardRepository;
import com.bankservice.bank.repository.UserRepository;
import com.bankservice.bank.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountRepository repository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    AtmMachineRepository atmRepository;

    @Override
    public List <BankAccount> getBankAccountsFromCardNumber(String cardNumber) {
        Optional <Card> card = cardRepository.findByCardNumber(cardNumber);
        log.info(card.toString());
        if (card.isPresent()) {
            User user = card.get().getUser();
            List <BankAccount> accounts = user.getBankAccounts();
            List <BankAccount> result = new ArrayList <>();

            for (BankAccount account : accounts) {
                // filter musn't get accounts from other banks, just from the same
                if (account.getBank().getId().equals(card.get().getBankAccount().getBank().getId()))
                    result.add(account);
            }
            return result;
        }

        return new ArrayList <>();
    }

    @Override
    public List <Activity> getActivity(String cardNumber) {
        List <BankAccount> accounts = getBankAccountsFromCardNumber(cardNumber);
        List <Activity> activities = new ArrayList <>();
        for (BankAccount account : accounts) {
            activities.addAll(account.getActivity());
        }
        return activities;

    }

    @Override
    public boolean getCash(String cardNumber, Double quantity) {
        Optional <Card> card = cardRepository.findByCardNumber(cardNumber);
        if (card.isPresent()) {
            if (card.get().getBankAccount() != null) {
                BankAccount account = card.get().getBankAccount();
                double currentCash = account.getBalance();
                if (currentCash >= quantity) {
                    account.setBalance(currentCash - quantity);
                    log.info(account.toString());
                    repository.save(account);
                    return true;
                }
            }
        }
        log.warn("Can't get cash");
        return false;
    }

    @Override
    public boolean putCash(String cardNumber, Double quantity, Long atmId) {
        Optional <Card> card = cardRepository.findByCardNumber(cardNumber);
        Optional <AtmMachine> atm = atmRepository.findById(atmId);

        if (card.isPresent() && atm.isPresent()
                && atm.get().getBank().equals(card.get().getBankAccount().getBank())) { // check if ATM is form the same bank
            if (card.get().getBankAccount() != null) {
                BankAccount account = card.get().getBankAccount();
                double currentCash = account.getBalance();
                account.setBalance(currentCash + quantity);
                repository.save(account);
                log.info(account.toString());
                return true;
            }
        }
        log.warn("Can't put cash");
        return false;
    }
}
