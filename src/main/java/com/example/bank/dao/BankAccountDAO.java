package com.bankservice.bank.dao;

import com.bankservice.bank.model.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class BankAccountDAO {

    private Long id;

    private Double balance;
    private Double creditAvailable;
    private Double creditUsed;
    private Long bankId;

    public BankAccountDAO(BankAccount bankAccount){
        this.id = bankAccount.getId();
        balance = bankAccount.getBalance();
        creditAvailable = bankAccount.getCreditAvailable();
        creditUsed = bankAccount.getCreditUsed();
        bankId = bankAccount.getBank().getId();
    }

}
