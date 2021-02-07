package com.bank.bankmod;

import com.bank.bankmod.models.UserRepository;
import com.bank.bankmod.models.data.User;
import com.bank.bankmod.security.UserRepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class Common {

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    public void sharedData(Model model, HttpSession session, Principal principal){


        if(principal!=null)
        {
            User user=userRepo.findByUsername(principal.getName());
            model.addAttribute("principal",principal.getName());
            model.addAttribute("loggedinuser",user);
        }







    }

}
