package ru.inovus.ziaevtestapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        LOGGER.debug("Getting login page, error={}" + error.toString(), error);
        //TODO: LOG to file this
        return new ModelAndView("login", "error", error);
    }
}
