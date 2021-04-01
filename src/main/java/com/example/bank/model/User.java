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
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public User() {
        bankAccounts = new ArrayList <>();
        cards = new ArrayList <>();
    }

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private List <BankAccount> bankAccounts;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List <Card> cards;

    public User setBankAccounts(List<BankAccount> bankAccounts){
        this.bankAccounts = bankAccounts;
        for (BankAccount bankAccount : bankAccounts) {
            bankAccount.getUsers().add(this);
        }
        return this;
    }
}
