package com.example.backestopenda.services;

import com.example.backestopenda.models.User;
import com.example.backestopenda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        logger.info("Finding all users");
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        logger.info("Finding an user by id");
        return userRepository.findById(id);
    }

    public void create(User user) {
        logger.info("Creating an user");
        userRepository.save(user);
    }

//    public User update(User user){
//        logger.info("Updating an user");
//
//        Optional<User> existsUser = userRepository.findById(user.getId());
//
//        return userRepository.save(user);
//    }

}
