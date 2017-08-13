package io.mts.helpdesk.component.authfailurehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mtugrulsaricicek on 09.08.2017.
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
                                    throws IOException, ServletException {
        logger.info("Failure Handler worked");
        try {
            throw new Exception("Invalid username or password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
