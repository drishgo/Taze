package com.example.Taze.service;

import com.example.Taze.model.User;
import com.example.Taze.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {
        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        // Find user by email
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            System.out.println("USER FOUND");
            return optionalUser.get(); // Return user if password matches
        }
        System.out.println("NO SUCH USER");
        return null; // Return null if user is not found or password doesn't match
    }

    public Optional<User> getUserById(Long Id) {
        return userRepository.findById(Id);
    }
}
