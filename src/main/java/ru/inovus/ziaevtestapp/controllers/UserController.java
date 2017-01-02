package ru.inovus.ziaevtestapp.controllers;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.log4j.Log4j;
import ru.inovus.ziaevtestapp.domain.UserCreateForm;
import ru.inovus.ziaevtestapp.domain.validator.UserCreateFormValidator;
import ru.inovus.ziaevtestapp.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.*;

@Log4j
@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.info("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.info("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            LOGGER.error("User.create_error | " + form.getEmail() + " " + bindingResult.toString() );
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("User.create_error | Duplicate email "  + form.getEmail(), e);
            bindingResult.reject("name.exists", "Это имя уже используется");
            return "user_create";
        }
        LOGGER.info("Success login");
        return "redirect:/sign-in";
//        TODO: autologin
    }
}
