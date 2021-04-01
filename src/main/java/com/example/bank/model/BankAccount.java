package com.bankservice.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class BankAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double balance;
    private Double creditAvailable;
    private Double creditUsed;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne
    private Bank bank;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToMany(mappedBy = "bankAccounts")
    private List <User> users;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToMany(mappedBy = "bankAccount")
    private List <Card> cards;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToMany(mappedBy = "bankAccount")
    private List <Activity> activity;

    public BankAccount(){
        users = new ArrayList <>();
        activity = new ArrayList <>();
        cards = new ArrayList <>();
    }

    public BankAccount setBank(Bank bank){
        bank.getBankAccounts().add(this);
        this.bank = bank;
        return this;
    }

    public BankAccount setUsers(ArrayList<User> users){
        for (User user : users) {
            user.getBankAccounts().add(this);
        }
        this.users = users;
        return this;
    }
}
