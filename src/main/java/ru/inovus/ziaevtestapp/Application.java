package ru.inovus.ziaevtestapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.inovus.ziaevtestapp.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}

//DONE welcome - main page redirection
//TODO: Registration
//TODO: DB connection
//TODO: login page works
//TODO: CSRF
