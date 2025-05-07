package com.sarkhan.dtssystem.config;


import com.sarkhan.dtssystem.model.user.User;
import com.sarkhan.dtssystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminCreator implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            User user = new User();
            user.setUsername("DTSAdmin");
            user.setPassword(passwordEncoder.encode("DTSAdmin"));
            user.setVerified(false);
            userRepository.save(user);
        }


    }
}
