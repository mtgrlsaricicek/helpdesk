package io.mts.helpdesk.controller;

import io.mts.helpdesk.hibernate.dao.userDao.UserDao;
import io.mts.helpdesk.hibernate.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by mtugrulsaricicek on 14.08.2017.
 */
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    UserDao userDao;

    @RequestMapping("getCurrentUser")
    public User user(Principal principal){
        Optional<User> user = userDao.findByUsername(principal.getName());
        return user.get();
    }
}
