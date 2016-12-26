package ru.inovus.ziaevtestapp.service.user;

import ru.inovus.ziaevtestapp.domain.User;
import ru.inovus.ziaevtestapp.domain.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(long id);

    Optional<User> getUserByName(String name);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
