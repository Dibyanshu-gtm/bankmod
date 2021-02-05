package com.bank.bankmod.security;

import com.bank.bankmod.models.UserRepository;
import com.bank.bankmod.models.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String account(User user){

        return "account";

    }

    @PostMapping
    public String account(@Valid User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors())
        {
            return "account";
        }

        if(!user.getPassword().equals(user.getConfirmPassword()))
        {
            model.addAttribute("passwordMatchProblem","passwords do not match");
            return "account";
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        return "redirect:/login";

    }


}
