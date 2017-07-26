package io.mts.helpdesk.controller.homecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */

@Controller
public class HomeController {

    @RequestMapping(value = {"/","/home"})
    public String home() {
        return "index";
    }
}
