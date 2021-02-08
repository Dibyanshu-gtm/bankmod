package com.bank.bankmod.models.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="standingorders")
@Data
public class StandingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    @Min(value = 1,message = "Enter valid Account ID")
    @Column(name = "creditor_id")
    private int creditorId;

    @Column(name = "debitor_id")
    private int debitorId;

   @Size(min = 1,message = "Enter valid amount")
    private String amount;






}
