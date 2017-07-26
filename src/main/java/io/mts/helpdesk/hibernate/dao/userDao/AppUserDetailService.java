package io.mts.helpdesk.hibernate.dao.userDao;

import io.mts.helpdesk.hibernate.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOp = userDao.findByUsername(username);
        if(!userOp.isPresent()){
            throw new UsernameNotFoundException("User Not Found");
        }
        User user = userOp.get();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(), Arrays.asList(grantedAuthority)
        );
        return userDetails;
    }
}
