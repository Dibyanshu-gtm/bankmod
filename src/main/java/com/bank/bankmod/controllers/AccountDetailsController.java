package com.bank.bankmod.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AccountDetailsController {


    @GetMapping("/accountdetails")
    public String accountdetails(){

        return "accountdetails";
    }
}
