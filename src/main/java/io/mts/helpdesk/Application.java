package io.mts.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by mtugrulsaricicek on 24.07.2017.
 */

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
