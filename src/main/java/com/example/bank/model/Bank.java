package com.bankservice.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Bank {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double taxesForOtherBanks;

    public Bank(){
        atmMachines = new ArrayList <>();
        bankAccounts = new ArrayList <>();
    }


    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "bank")
    private List <AtmMachine> atmMachines;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "bank")
    private List <BankAccount> bankAccounts;

}
