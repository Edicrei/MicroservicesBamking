package com.bankservice.bank.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AtmMachine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne
    private Bank bank;

    public AtmMachine setBank(Bank bank){
        this.bank = bank;
        bank.getAtmMachines().add(this);

        return this;
    }
}
