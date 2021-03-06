package com.studymonkey.surveychimp.viewControllers;

import com.studymonkey.surveychimp.entity.Account;
import com.studymonkey.surveychimp.repositories.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "login")
public class LoginViewController {

    public final AccountRepository repository;

    LoginViewController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }
}
