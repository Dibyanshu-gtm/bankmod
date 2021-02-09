package com.bank.bankmod.models;

import com.bank.bankmod.models.data.StandingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandingOrderRepository extends JpaRepository<StandingOrder,Integer> {

    List<StandingOrder> findAllByDebitorId(int id);
    StandingOrder findById(int id);
}
