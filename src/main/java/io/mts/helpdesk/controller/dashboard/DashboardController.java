package io.mts.helpdesk.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Controller
public class DashboardController {

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String admin(Model model){
        return "index";
    }
}
