package ru.inovus.ziaevtestapp.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.inovus.ziaevtestapp.domain.UserCreateForm;
import ru.inovus.ziaevtestapp.service.user.UserService;

@Component
public class UserCreateFormValidator implements org.springframework.validation.Validator {
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
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
        if (userService.getUserByName(form.getName()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }
}
