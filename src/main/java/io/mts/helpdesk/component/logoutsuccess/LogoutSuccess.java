package io.mts.helpdesk.component.logoutsuccess;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mtugrulsaricicek on 31.07.2017.
 */

@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        if(authentication!=null && authentication.getDetails()!=null){
            request.getSession().invalidate();
        }

        response.setStatus(HttpServletResponse.SC_OK);

    }
}
