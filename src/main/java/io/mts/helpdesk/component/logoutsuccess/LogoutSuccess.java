package io.mts.helpdesk.component.logoutsuccess;

import io.mts.helpdesk.hibernate.dao.userDao.UserDao;
import io.mts.helpdesk.hibernate.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * Created by mtugrulsaricicek on 31.07.2017.
 */

@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    @Autowired
    UserDao userDao;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        Optional<User> userOp = userDao.findByUsername(principal.getUsername());
        User user = userOp.get();
        user.setLastLogoutTime(new Date(System.currentTimeMillis()));
        userDao.update(user);

        if(authentication!=null && authentication.getDetails()!=null){
            request.getSession().invalidate();
        }

        response.setStatus(HttpServletResponse.SC_OK);

    }
}
