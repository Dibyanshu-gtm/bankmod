package com.bank.bankmod.models;

import com.bank.bankmod.models.data.StandingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandingOrderRepository extends JpaRepository<StandingOrder,Integer> {

}
