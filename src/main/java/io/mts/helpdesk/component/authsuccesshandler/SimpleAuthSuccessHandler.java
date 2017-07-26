package io.mts.helpdesk.component.authsuccesshandler;

import io.mts.helpdesk.util.role.Role;
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
import java.util.List;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Component
public class SimpleAuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
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
