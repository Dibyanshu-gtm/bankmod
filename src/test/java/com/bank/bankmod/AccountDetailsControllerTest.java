package com.bank.bankmod;


import com.bank.bankmod.models.UserRepository;
import com.bank.bankmod.models.data.User;
import com.bank.bankmod.security.AccountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
@SpringBootTest
public class AccountDetailsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    UserRepository userRepo;

    @InjectMocks
    AccountController accountController;

    private List<User> userList;
    @BeforeEach
    private  void setUp()
    {
        MockitoAnnotations.initMocks(this);

        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }



    @Test
    @WithMockUser(username = "dibyanshu",password = "root",roles = "USER")
    public void createAccountFormSubmitTest() throws Exception{

        mockMvc.perform(get("/accountdetails")).andExpect(status().isOk())
                .andExpect(view().name("accountdetails"));


    }

}
