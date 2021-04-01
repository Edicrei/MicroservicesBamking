package com.bankservice.bank.unit;

import com.bankservice.bank.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Slf4j
public class BankTest {

    @Test
    public void testBank() {
        Bank bank = new Bank().setId(1l).setName("ING").setTaxesForOtherBanks(2.0);
        Assert.isTrue(bank.getId().equals(1l), "bank id not assigned");
        Assert.isTrue(bank.getName().equals("ING"), "bank name not assigned");
        Assert.isTrue(bank.getAtmMachines().isEmpty(), "atms empty");
        Assert.isTrue(bank.getTaxesForOtherBanks().equals(2.0), "taxes for other banks not loaded");
        log.info("Bank model OK");
    }

    @Test
    public void testBankWithAtms() {
        Bank bank = new Bank().setId(1l).setName("ING");

        AtmMachine atm = new AtmMachine().setId(1l).setBank(bank);

        log.info(bank.toString());
        log.info(atm.toString());

        Assert.isTrue(bank.getId().equals(1l), "bank id not assigned");
        Assert.isTrue(bank.getName().equals("ING"), "bank name not assigned");
        Assert.isTrue(!bank.getAtmMachines().isEmpty(), "atms empty");
        Assert.isTrue(bank.getAtmMachines().contains(atm), "atm not contained by bank");

        Assert.isTrue(atm.getBank().equals(bank), "bank not linked to ATM");
        log.info("Bank with ATMs OK");
    }

    @Test
    public void testBankAccount() {
        Bank bank = new Bank().setId(1l).setName("ING");
        BankAccount account = new BankAccount().setId(1l).setBank(bank).setBalance(100.0);

        User user = new User().setId(1l).setName("Fulano de tal").setBankAccounts(new ArrayList <>(Arrays.asList(account)));

        log.info(bank.toString());
        log.info(account.toString());

        Assert.isTrue(account.getId().equals(1l), "account id not loaded");
        Assert.isTrue(account.getBalance().equals(100.0), "account balance not loaded");
        Assert.isTrue(account.getBank().equals(bank), "bank not linked");
        Assert.isTrue(account.getBank().equals(bank), "bank not linked");
        Assert.isTrue(account.getUsers().contains(user), "user not linked");

        Assert.isTrue(account.getBank().equals(bank), "account not linked with bank");
        Assert.isTrue(user.getBankAccounts().contains(account), "account not linked with user");

        Activity act = new Activity().setBankAccount(account)
                .setId(1l).setActivityType(ActivityType.INCOME).setDate(new Date()).setQuantity(100.0);

        log.info(act.toString());
        log.info(account.toString());
        Assert.isTrue(account.getActivity().contains(act), "activity not linked with account");

    }
}
