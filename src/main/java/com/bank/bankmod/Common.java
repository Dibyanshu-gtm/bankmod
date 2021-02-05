package com.bank.bankmod;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class Common {

    @ModelAttribute
    public void sharedData(Model model, HttpSession session, Principal principal){


        if(principal!=null)
        {
            model.addAttribute("principal",principal.getName());
        }







    }

}
