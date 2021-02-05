package com.bank.bankmod;


import com.bank.bankmod.controllers.HomeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    @DisplayName("Test to Check if HomeController is Active or not")
    void contextLoads() {
        assertThat(homeController).isNotNull();
    }

}
