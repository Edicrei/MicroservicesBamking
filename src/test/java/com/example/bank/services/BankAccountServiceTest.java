package com.bankservice.bank.services;

import com.bankservice.bank.model.*;
import com.bankservice.bank.repository.AtmMachineRepository;
import com.bankservice.bank.repository.BankRepository;
import com.bankservice.bank.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class BankAccountServiceTest {

    @Autowired
    BankAccountService service;
    @Autowired
    AtmMachineRepository atmRepository;
    @Autowired
    BankRepository bankRepository;

    @Test
    public void testGetBankAccountsByCardNumber(){

        List <BankAccount> list = service.getBankAccountsFromCardNumber("1111222233334444");
        for (BankAccount bankAccount : list) {
            log.info(bankAccount.toString());
        }
        Assert.isTrue(!list.isEmpty(), "no bank account detected");
    }

    @Test
    public void testGetBankAccountActivity(){
        List <Activity> list = service.getActivity("1111222233334444");
        for (Activity activity : list) {
            log.info(activity.toString());
        }
        Assert.isTrue(!list.isEmpty(), "no bank account detected");
    }

    @Test
    public void testGetBankAccountNOActivity(){
        List <Activity> list = service.getActivity("abc");
        Assert.isTrue(list.isEmpty(), "no card");
    }

    @Test
    public void putCash(){
        List<Bank> banks = new ArrayList <>();
        bankRepository.findAll().forEach(bank -> {
            banks.add(bank);
        });
        List<AtmMachine> atms = new ArrayList <>();
        for (AtmMachine atmMachine : atmRepository.findAll()) {
            atms.add(atmMachine);
        }
        Bank b = banks.get(0);
        Card c = b.getBankAccounts().get(0).getCards().get(0);

        boolean result = service.putCash(c.getCardNumber(), 10.0, b.getAtmMachines().get(0).getId());
        Assert.isTrue(result, "put from atm of same bank");

        Bank b2 = banks.get(1); //change to other bank

        result = service.putCash(c.getCardNumber(), 10.0, b2.getAtmMachines().get(0).getId());
        Assert.isTrue(!result, "put from atm from different bank");
    }

}
