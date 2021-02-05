package com.bank.bankmod;


import com.bank.bankmod.models.data.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    private  void setUp()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void createAccountTest() throws Exception {
        User user =new User();
        user.setUsername("dibyanshu");
        user.setPassword("root");

        mockMvc.perform(get("/account")).andExpect(status().isOk())
                .andExpect(view().name("account"));
    }

}
