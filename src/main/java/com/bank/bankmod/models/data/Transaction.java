package com.bank.bankmod.models.data;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String type;


    private String amount;


    private String balance;

    @Column(name="account_id")
    private String accountId;

    @Column(name="transaction_time", updatable = false)
    @CreationTimestamp
    private LocalDateTime transactionTime;






}
