package ru.inovus.ziaevtestapp.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.inovus.ziaevtestapp.domain.User;
import ru.inovus.ziaevtestapp.domain.UserCreateForm;
import ru.inovus.ziaevtestapp.domain.UserRepository;
import ru.inovus.ziaevtestapp.service.user.UserService;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepository.findOneByName(name);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("name"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }
}
