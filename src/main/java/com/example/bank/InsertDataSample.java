package com.bankservice.bank;

import com.bankservice.bank.model.*;
import com.bankservice.bank.repository.*;
import com.bankservice.bank.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Fills initial data
 */
@Slf4j
@Component
public class InsertDataSample {

    @Autowired
    BankRepository bankRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    AtmMachineRepository atmMachineRepository;
    @Autowired
    ActivityRepository activityRepository;

    @PostConstruct
    public void insertData() {
        activityRepository.deleteAll();
        cardRepository.deleteAll();
        atmMachineRepository.deleteAll();
        userRepository.deleteAll();
        bankAccountRepository.deleteAll();
        bankRepository.deleteAll();
        log.info("Clearing DB data");

        Bank bank1 = new Bank().setName("ING").setTaxesForOtherBanks(1.0);
        Bank bank2 = new Bank().setName("ABANCA").setTaxesForOtherBanks(2.0);
        List <Bank> banks = new ArrayList <>(Arrays.asList(bank1, bank2));

        BankAccount b1 = new BankAccount().setBalance(100.0).setBank(bank1).setCreditAvailable(1000.0).setCreditUsed(0.0);
        BankAccount b2 = new BankAccount().setBalance(100.0).setBank(bank1).setCreditAvailable(1000.0).setCreditUsed(0.0);
        BankAccount b3 = new BankAccount().setBalance(100.0).setBank(bank2).setCreditAvailable(500.0).setCreditUsed(0.0);
        BankAccount b4 = new BankAccount().setBalance(100.0).setBank(bank2).setCreditAvailable(500.0).setCreditUsed(0.0);
        List <BankAccount> bankAccounts = new ArrayList <>(Arrays.asList(b1, b2, b3, b4));

        log.info("loading banks: {}", banks);
        log.info("loading bankAccounts: {}", bankAccounts);

        bankRepository.saveAll(banks);
        bankAccountRepository.saveAll(bankAccounts);

        AtmMachine atm1 = new AtmMachine().setBank(bank1);
        AtmMachine atm2 = new AtmMachine().setBank(bank2);
        List <AtmMachine> atms = Arrays.asList(atm1, atm2);
        atmMachineRepository.saveAll(atms);
        log.info("loading ATMs: {}", atms);

        User u1 = new User().setName("Pepe");
        User u2 = new User().setName("Paco");
        User u3 = new User().setName("Maria");
        User u4 = new User().setName("Ana");
        List <User> users = Arrays.asList(u1, u2, u3, u4);
        userRepository.saveAll(users);

        u1.setBankAccounts(new ArrayList <>(Arrays.asList(b1, b3)));
        u2.setBankAccounts(new ArrayList <>(Arrays.asList(b2)));
        u3.setBankAccounts(new ArrayList <>(Arrays.asList(b4)));
        u4.setBankAccounts(new ArrayList <>(Arrays.asList(b1)));
        userRepository.saveAll(users);
        log.info("loading users: {}", users);

        Card c1 = new Card().setActive(false).setCardType(CardType.DEBIT).setBankAccount(b1).setUser(u1)
                .setCardNumber("1111222233334444").setPin(Utils.encryptMd5("1234")).setCardLimit(3000);
        Card c1_2 = new Card().setActive(true).setCardType(CardType.CREDIT).setBankAccount(b1).setUser(u1)
                .setCardNumber("1211222233334444").setPin(Utils.encryptMd5("1234")).setCardLimit(3000);
        Card c1_3 = new Card().setActive(true).setCardType(CardType.CREDIT).setBankAccount(b3).setUser(u1)
                .setCardNumber("1311222233334444").setPin(Utils.encryptMd5("1234")).setCardLimit(3000);
        Card c2 = new Card().setActive(true).setCardType(CardType.DEBIT).setBankAccount(b2).setUser(u2)
                .setCardNumber("2111222233334444").setPin(Utils.encryptMd5("1234")).setCardLimit(3000);
        Card c3 = new Card().setActive(true).setCardType(CardType.DEBIT).setBankAccount(b4).setUser(u3)
                .setCardNumber("3111222233334444").setPin(Utils.encryptMd5("1234")).setCardLimit(3000);
        Card c4 = new Card().setActive(true).setCardType(CardType.DEBIT).setBankAccount(b1).setUser(u4)
                .setCardNumber("4111222233334444").setPin(Utils.encryptMd5("4321")).setCardLimit(3000);
        List<Card> cards = Arrays.asList(c1, c1_2,c1_3, c2, c3, c4);
        cardRepository.saveAll(cards);
        log.info("loading cards: {}", cards);

        Activity ac1 = new Activity().setActivityType(ActivityType.INCOME).setBankAccount(b1).setDate(new Date()).setQuantity(500);
        Activity ac2 = new Activity().setActivityType(ActivityType.INCOME).setBankAccount(b1).setDate(new Date()).setQuantity(100);
        Activity ac3 = new Activity().setActivityType(ActivityType.CHARGE).setBankAccount(b1).setDate(new Date()).setQuantity(25);
        Activity ac4 = new Activity().setActivityType(ActivityType.INCOME).setBankAccount(b2).setDate(new Date()).setQuantity(100);
        Activity ac5 = new Activity().setActivityType(ActivityType.TRANSFER_OUT).setBankAccount(b3).setDate(new Date()).setQuantity(100);
        Activity ac6 = new Activity().setActivityType(ActivityType.TAXES).setBankAccount(b3).setDate(new Date()).setQuantity(2);
        List <Activity> activities = Arrays.asList(ac1, ac2, ac3, ac4, ac5, ac6);
        activityRepository.saveAll(activities);

        log.info("loading activities: {}", activities);

    }
}
