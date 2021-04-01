package com.bankservice.bank.service;

import com.bankservice.bank.model.Activity;
import com.bankservice.bank.model.BankAccount;

import java.util.List;

public interface BankAccountService {

    List <BankAccount> getBankAccountsFromCardNumber(String cardNumber);

    List<Activity> getActivity(String cardNumber);

    boolean getCash(String cardNumber, Double quantity);

    boolean putCash(String cardNumber, Double quantity, Long atmId);
}
