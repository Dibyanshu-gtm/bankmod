package com.bank.bankmod;


import com.bank.bankmod.models.data.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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



        mockMvc.perform(get("/account")).andExpect(status().isOk())
                .andExpect(view().name("account"));
    }

    @Test
    public void createAccountFormSubmitTest() throws Exception{
        String username= "Harry1";
        String password=  "potter";
        String cpassword="potter";
        String val="0";

        mockMvc.perform(
                post("/account")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username",username)
                .param("password",password)
                .param("confirmPassword",cpassword)
                 .param("balance",val)
                .sessionAttr("user",new User())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"));


    }

}
