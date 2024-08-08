package com.zach.NewStudentManagement.service;

import com.zach.NewStudentManagement.model.User;
import com.zach.NewStudentManagement.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userService {
    @Autowired
    private userRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }
}
