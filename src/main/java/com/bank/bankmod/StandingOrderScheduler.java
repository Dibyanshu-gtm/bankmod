package com.bank.bankmod;


import com.bank.bankmod.models.StandingOrderRepository;
import com.bank.bankmod.models.TransactionRepository;
import com.bank.bankmod.models.UserRepository;
import com.bank.bankmod.models.data.StandingOrder;
import com.bank.bankmod.models.data.Transaction;
import com.bank.bankmod.models.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StandingOrderScheduler {
    @Autowired
    UserRepository userRepo;

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    StandingOrderRepository standingRepo;

    @Scheduled(fixedDelay = 60000,initialDelay = 5000)
    public void demo(){


        List<StandingOrder> standingOrders= standingRepo.findAll();

        for(StandingOrder std:standingOrders)
        {

            User debitor=userRepo.findById(std.getDebitorId());
            User creditor=userRepo.findById(std.getCreditorId());

            Transaction debittransac=new Transaction();
            Transaction credittransac=new Transaction();

            debittransac.setAccountId(Integer.toString(std.getDebitorId()));
            debittransac.setAmount(std.getAmount());
            debittransac.setType("Debit");

            credittransac.setAccountId(Integer.toString(std.getCreditorId()));
            credittransac.setAmount(std.getAmount());
            credittransac.setType("Credit");

            Integer withdrawalAmount=Integer.parseInt(std.getAmount());
            Integer debitorBalance=Integer.parseInt(debitor.getBalance());
            Integer creditorBalance=Integer.parseInt(creditor.getBalance());
            if(withdrawalAmount>debitorBalance)
            {
                System.out.println("Insufficient Balance. Transaction not completed");
            }
            else
            {
                Integer updateddebitor=debitorBalance-withdrawalAmount;
                Integer updatedcreditor=creditorBalance+withdrawalAmount;

                debitor.setBalance(Integer.toString(updateddebitor));
                creditor.setBalance(Integer.toString(updatedcreditor));
                debittransac.setBalance(Integer.toString(updateddebitor));
                credittransac.setBalance(Integer.toString(updatedcreditor));
                userRepo.save(debitor);
                userRepo.save(creditor);
                transactionRepo.save(debittransac);
                transactionRepo.save(credittransac);
                System.out.println("Amount  " + std.getAmount() +" is transferred from ID: "
                        + std.getDebitorId() + " to ID: " + std.getCreditorId() + " for "+ std.getName());

            }



        }


    }
}
