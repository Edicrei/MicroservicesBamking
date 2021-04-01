package com.bankservice.bank.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    @Enumerated
    private CardType cardType;
    private boolean active;
    private String pin; // encoded
    private double cardLimit;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private BankAccount bankAccount;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private User user;

    public Card setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        bankAccount.getCards().add(this);
        return this;
    }

    public Card setUser(User user) {
        this.user = user;
        user.getCards().add(this);
        return this;
    }
}
