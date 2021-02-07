package com.bank.bankmod.controllers;


import com.bank.bankmod.models.UserRepository;
import com.bank.bankmod.models.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/accountdetails")
public class AccountDetailsController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String accountdetails(User user, Model model){


        return "accountdetails";
    }

    @GetMapping("/deposit/{id}")
    public String deposit(@ModelAttribute User user){


        return "deposit";
    }

    @GetMapping("/withdraw/{id}")
    public String withdraw(@ModelAttribute User user){


        return "withdraw";
    }

    @PostMapping("/deposit/{id}")
    public String deposit(@Valid User user,  BindingResult bindingResult,@PathVariable int id, RedirectAttributes redirectAttributes, Model model){

        User userCurrent=userRepo.getOne(id);
        if(bindingResult.hasErrors()){
            return "deposit";
        }
        redirectAttributes.addFlashAttribute("message","Amount updated");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        Integer total=Integer.parseInt(user.getBalance())+Integer.parseInt(userCurrent.getBalance());

        userCurrent.setBalance(Integer.toString(total));
        userRepo.save(userCurrent);

        return "redirect:/accountdetails/deposit/"+ id;
        //return "deposit";
    }
    @PostMapping("/withdraw/{id}")
    public String withdraw(@Valid User user,  BindingResult bindingResult,@PathVariable int id, RedirectAttributes redirectAttributes, Model model){

        User userCurrent=userRepo.getOne(id);
        if(bindingResult.hasErrors()){
            return "withdraw";
        }

        redirectAttributes.addFlashAttribute("message","Amount updated");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        Integer withdrawalAmount=Integer.parseInt(user.getBalance());
        Integer currentAmount=Integer.parseInt(userCurrent.getBalance());
        if(withdrawalAmount>currentAmount)
        {
            redirectAttributes.addFlashAttribute("message","Your Account Balance is not enough for this Transaction");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
        }
        else
        {
            Integer total=Integer.parseInt(userCurrent.getBalance()) - Integer.parseInt(user.getBalance());
            userCurrent.setBalance(Integer.toString(total));
            userRepo.save(userCurrent);
        }






        return "redirect:/accountdetails/withdraw/"+ id;

    }


}
