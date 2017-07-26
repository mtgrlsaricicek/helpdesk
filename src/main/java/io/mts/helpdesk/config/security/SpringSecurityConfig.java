package io.mts.helpdesk.config.security;

import io.mts.helpdesk.component.authsuccesshandler.SimpleAuthSuccessHandler;
import io.mts.helpdesk.hibernate.dao.userDao.AppUserDetailService;
import io.mts.helpdesk.util.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    AppUserDetailService userDetailService;

    @Autowired
    SimpleAuthSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/","/home","/about","/webjars/**","/css/**","/js/**")
                .permitAll()
                .antMatchers("/admin/**")
                .hasAnyAuthority(Role.ADMIN.toString())
                .antMatchers("/teamLead/**")
                .hasAnyAuthority(Role.TEAM_LEAD.toString()
                            , Role.ADMIN.toString())
                .antMatchers("/technician").hasAnyAuthority(Role.TEAM_LEAD.toString()
                                                                  , Role.ADMIN.toString(),
                                                                    Role.TECHNICIAN.toString())
                .and()
                .formLogin().loginPage("/login").successHandler(successHandler)
                                                .failureForwardUrl("/loginFailed")
                                                .loginProcessingUrl("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();

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
