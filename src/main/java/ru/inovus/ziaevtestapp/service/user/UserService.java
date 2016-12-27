package ru.inovus.ziaevtestapp.service.user;

import ru.inovus.ziaevtestapp.domain.User;
import ru.inovus.ziaevtestapp.domain.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
