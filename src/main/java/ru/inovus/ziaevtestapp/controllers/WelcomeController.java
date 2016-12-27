package ru.inovus.ziaevtestapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Calendar;

import static ru.inovus.ziaevtestapp.service.user.UserServiceImpl.getUserId;
import static ru.inovus.ziaevtestapp.service.user.UserServiceImpl.getUsername;

@RequestMapping("/")
@Controller
public class WelcomeController {

    @RequestMapping("")
    public String index() {
        return "redirect:welcome";
    }

    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("username", getUsername());
        model.addAttribute("greeting", getGreetingText());
        return "index";
    }

    private StringBuilder getGreetingText() {
        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.getTime().getHours();
        if (currentHour >= 6 && currentHour < 10) {
            sb.append("ое утро ");
        } else if (currentHour >= 10 && currentHour < 18) {
            sb.append("ый день ");
        } else if (currentHour >= 18 && currentHour < 22) {
            sb.append("ый вечер ");
        } else if (currentHour >= 22 && currentHour <= 23) {
            sb.append("ой ночи ");
        } else if (currentHour >= 0 && currentHour < 6) {
            sb.append("ой ночи ");
        } else {
            sb.append("ого времени суток");
        }
        return  sb;
    }
}
