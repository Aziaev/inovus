package ru.inovus.ziaevtestapp.service.currentuser;

import ru.inovus.ziaevtestapp.domain.CurrentUser;
import ru.inovus.ziaevtestapp.domain.User;
import ru.inovus.ziaevtestapp.service.user.UserService;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getUserByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", name)));
        return new CurrentUser(user);
    }
}