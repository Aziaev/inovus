package ru.inovus.ziaevtestapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.inovus.ziaevtestapp.domain.UserRepository;

@Controller

public class WelcomeController {
    //TODO: Something to show

    @Autowired
    private UserRepository user;

    @RequestMapping("/")
    public String welcomePage(Model model) {
        return "index";
    }
}
