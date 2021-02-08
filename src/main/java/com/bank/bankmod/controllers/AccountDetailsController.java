package com.bank.bankmod.controllers;


import com.bank.bankmod.models.StandingOrderRepository;
import com.bank.bankmod.models.TransactionRepository;
import com.bank.bankmod.models.UserRepository;
import com.bank.bankmod.models.data.StandingOrder;
import com.bank.bankmod.models.data.Transaction;
import com.bank.bankmod.models.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/accountdetails")
public class AccountDetailsController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private StandingOrderRepository standingRepo;

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
        Transaction transac=new Transaction();
        if(bindingResult.hasErrors()){
            return "deposit";
        }
        redirectAttributes.addFlashAttribute("message","Amount updated");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        transac.setType("Credit");
        transac.setAmount(user.getBalance());


        Integer total=Integer.parseInt(user.getBalance())+Integer.parseInt(userCurrent.getBalance());

        transac.setBalance(Integer.toString(total));
        transac.setAccountId(Integer.toString(id));


        userCurrent.setBalance(Integer.toString(total));
        userRepo.save(userCurrent);
        transactionRepo.save(transac);
        return "redirect:/accountdetails/deposit/"+ id;
        //return "deposit";
    }
    @PostMapping("/withdraw/{id}")
    public String withdraw(@Valid User user,
                           BindingResult bindingResult,@PathVariable int id,
                           RedirectAttributes redirectAttributes, Model model){

        User userCurrent=userRepo.getOne(id);
        Transaction transac=new Transaction();
        if(bindingResult.hasErrors()){
            return "withdraw";
        }

        transac.setType("Debit");
        transac.setAmount(user.getBalance());

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

            transac.setBalance(Integer.toString(total));
            transac.setAccountId(Integer.toString(id));


            userCurrent.setBalance(Integer.toString(total));
            transactionRepo.save(transac);
            userRepo.save(userCurrent);
        }

        return "redirect:/accountdetails/withdraw/"+ id;

    }

    @GetMapping("/operations/{id}")
    public String operations(@PathVariable int id,Model model){


        User user=userRepo.getOne(id);
        String accountId= Integer.toString(user.getId());
        List<Transaction> transactions=transactionRepo.findAllByAccountId(accountId);
        model.addAttribute("transactions",transactions);

        return "operations";
    }

    @GetMapping("/standingorders/{id}")
    public String standingorders(Model model)
    {
        List<StandingOrder> standingorders=standingRepo.findAll();
        model.addAttribute("standingorders",standingorders);

        return "standingorders" ;

    }

    @GetMapping("/standingorders-add/{id}")
    public String standingordersadd(@ModelAttribute StandingOrder standingOrder)
    {


        return "standingorders-add" ;

    }

    @PostMapping("/standingorders-add/{id}")
    public String standingordersadd(@Valid StandingOrder standingOrder, BindingResult bindingResult,
                                    @PathVariable int id,
                                    RedirectAttributes redirectAttributes, Model model)
    {
        if(bindingResult.hasErrors()){
            return "standingorders-add";
        }

        StandingOrder sord=new StandingOrder();
        User userCredit=userRepo.findById(standingOrder.getCreditorId());
        User userCurrent=userRepo.getOne(id);


        redirectAttributes.addFlashAttribute("message","Standing Order added");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        Integer withdrawalAmount=Integer.parseInt(standingOrder.getAmount());
        Integer currentAmount=Integer.parseInt(userCurrent.getBalance());



        if(userCredit==null)
        {
            redirectAttributes.addFlashAttribute("message","Account ID given doesn't exist ");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");


        }
        else if(withdrawalAmount>currentAmount)
        {
            redirectAttributes.addFlashAttribute("message","Insufficient Balance to Carry out the Standing order ");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");


        }
        else
        {
            //standingOrder.setDebitorId(id);
            sord.setDebitorId(id);
            sord.setCreditorId(standingOrder.getCreditorId());
            sord.setName(standingOrder.getName());
            sord.setAmount(standingOrder.getAmount());
            standingRepo.save(sord);

        }


        return "redirect:/accountdetails/standingorders-add/"+ id;

    }


}
