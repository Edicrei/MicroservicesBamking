package com.bankservice.bank.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Activity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ActivityType activityType;
    private double quantity;
    private Date date;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne
    private BankAccount bankAccount;

    public Activity setBankAccount(BankAccount bankAccount){
        this.bankAccount = bankAccount;
        bankAccount.getActivity().add(this);
        return this;
    }
}
