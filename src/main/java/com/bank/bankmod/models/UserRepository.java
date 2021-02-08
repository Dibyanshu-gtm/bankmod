package com.bank.bankmod.models;

import com.bank.bankmod.models.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findByUsername(String username);

    User findById(int id);

}
