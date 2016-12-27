package ru.inovus.ziaevtestapp.service.currentuser;

import ru.inovus.ziaevtestapp.domain.CurrentUser;
import ru.inovus.ziaevtestapp.domain.User;
import ru.inovus.ziaevtestapp.service.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("Authenticating user with email={}", email.replaceFirst("@.*", "@***"));
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с почтой email=%s не найден", email)));
        return new CurrentUser(user);
    }
}