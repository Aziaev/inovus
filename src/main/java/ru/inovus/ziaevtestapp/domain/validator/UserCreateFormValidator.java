package ru.inovus.ziaevtestapp.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.inovus.ziaevtestapp.domain.UserCreateForm;
import ru.inovus.ziaevtestapp.service.user.UserService;

@Component
public class UserCreateFormValidator implements org.springframework.validation.Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Пароли не совпадают");
            //TODO: need this in log file?
            LOGGER.debug("Password not match for email " + form.getEmail());
        } else if (
                form.getPassword().length() < 8
                        || form.getPassword().length() > 32){
            errors.reject("password.length_error", "Пароль должен быть длиной от 8 до 32 символов");
            LOGGER.debug("Password length is incorrect for " + form.getEmail());
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "Эта почта уже используется");
            LOGGER.debug("Email " + form.getEmail() + " already in use");
        }
    }
}
