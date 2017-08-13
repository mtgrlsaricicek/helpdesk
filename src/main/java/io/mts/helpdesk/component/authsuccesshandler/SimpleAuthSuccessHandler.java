package io.mts.helpdesk.component.authsuccesshandler;

import io.mts.helpdesk.hibernate.dao.userDao.UserDao;
import io.mts.helpdesk.hibernate.entity.user.User;
import io.mts.helpdesk.util.role.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Component
public class SimpleAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    UserDao userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        Optional<User> userOp = userDao.findByUsername(principal.getUsername());
        User user = userOp.get();
        user.setLastLoginTime(new Date(System.currentTimeMillis()));
        userDao.update(user);

        for (GrantedAuthority authority: authorities) {
            if(authority.getAuthority().equals(Role.ADMIN.toString())){
                redirectStrategy.sendRedirect(request,response,"/admin");
            }else if(authority.getAuthority().equals(Role.TEAM_LEAD.toString())){
                redirectStrategy.sendRedirect(request,response,"/teamLead");
            }else if(authority.getAuthority().equals(Role.TECHNICIAN.toString())){
                redirectStrategy.sendRedirect(request,response,"/technician");
            }
        }
    }
}
