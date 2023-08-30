package com.example.backestopenda.services;

import com.example.backestopenda.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Logger;

@Service
public class UserService {
    private final Logger logger = Logger.getLogger(UserService.class.getName());

    public ArrayList<User> findAll(){
        logger.info("Finding all users");
        ArrayList<User> listUser = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            User user = mockUser((long) i);
            listUser.add(user);
        }
        return listUser;
    }

    public User findById(Long id){
        logger.info("Finding an user");
        User user = new User();
        user.setId(1L);
        user.setName("Arthur");

        return user;
    }

    public User create(User user) {
        logger.info("Creating an user");
//        User user = new User();
//        return user;
        return user;
    }

    private User mockUser(Long i) {
        User user = new User();

        user.setId(i);
        user.setName("Arthur" + i);

        return user;
    }

}
