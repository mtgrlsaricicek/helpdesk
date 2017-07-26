package io.mts.helpdesk.component.database;

import io.mts.helpdesk.hibernate.dao.userDao.UserDao;
import io.mts.helpdesk.hibernate.entity.user.User;
import io.mts.helpdesk.util.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Component
public class InitializeInsert implements CommandLineRunner {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String initializeParam = args[0];
        if (initializeParam.equals("init")) {
            Scanner kb = new Scanner(System.in);
            System.out.println("Please insert password of default" +
                    "admin@mts.io admin");
            String defPassword = kb.next();

            Optional<User> oldUser = userDao.findByUsername("admin@mts.io");
            if (oldUser.isPresent()) {
                userDao.delete(oldUser.get());
            }

            User user = new User();
            user.setEmail("admin@mts.io");
            user.setPassword(passwordEncoder.encode(defPassword));
            user.setActive(true);
            user.setName("admin@mts.io");
            user.setSurname("admin@mts.io");
            user.setCreatedDate(new Date(System.currentTimeMillis()));
            user.setRole(Role.ADMIN);

            user = userDao.create(user);
        }
    }
}
