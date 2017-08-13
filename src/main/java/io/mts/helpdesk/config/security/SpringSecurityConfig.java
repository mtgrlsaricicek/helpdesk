package io.mts.helpdesk.config.security;

import io.mts.helpdesk.component.authfailurehandler.AuthFailureHandler;
import io.mts.helpdesk.component.authsuccesshandler.SimpleAuthSuccessHandler;
import io.mts.helpdesk.component.logoutsuccess.LogoutSuccess;
import io.mts.helpdesk.component.restauthentrypoint.RestAuthEntryPoint;
import io.mts.helpdesk.hibernate.dao.userDao.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    AppUserDetailService userDetailService;

    @Autowired
    SimpleAuthSuccessHandler successHandler;

    @Autowired
    AuthFailureHandler authFailureHandler;

    @Autowired
    LogoutSuccess logoutSuccess;

    @Autowired
    RestAuthEntryPoint restAuthEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/index.html",
                        "/pages/**",
                        "/",
                        "/webjars/**")
                .permitAll();
        http
                .formLogin().loginProcessingUrl("/login").permitAll()
                .failureHandler(authFailureHandler).successHandler(successHandler);
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
                .logoutSuccessHandler(logoutSuccess)
                .deleteCookies("JSESSIONID").invalidateHttpSession(true);
        http.authorizeRequests().anyRequest().authenticated();
        http
                .exceptionHandling().authenticationEntryPoint(restAuthEntryPoint);
        http
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
