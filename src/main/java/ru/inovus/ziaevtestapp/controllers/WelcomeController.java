package ru.inovus.ziaevtestapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import static ru.inovus.ziaevtestapp.service.user.UserServiceImpl.getUserId;
import static ru.inovus.ziaevtestapp.service.user.UserServiceImpl.getUsername;

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String index() {
        return "redirect:welcome";
    }

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("username", getUsername());
        model.addAttribute("userId", getUserId());
        return "index";
    }
}
