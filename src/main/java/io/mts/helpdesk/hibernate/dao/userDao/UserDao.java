package io.mts.helpdesk.hibernate.dao.userDao;

import io.mts.helpdesk.hibernate.dao.baseDao.BaseDao;
import io.mts.helpdesk.hibernate.entity.user.User;
import io.mts.helpdesk.util.role.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

/**
 * Created by mtugrulsaricicek on 26.07.2017.
 */
@Repository
public class UserDao extends BaseDao<User> {

    @Inject
    public UserDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public Optional<User> findByUsername(String userName){
        currentSession().beginTransaction();
        Criteria criteria = currentSession().createCriteria(User.class);
        criteria.add(Restrictions.and(Restrictions.eq("email",userName),
                     Restrictions.eq("active",true)
        ));
        Optional<User> user = Optional.ofNullable(uniqueResult(criteria));
        currentSession().getTransaction().commit();
        return user;
    }

    public List<User> findByRole(Role role){
        currentSession().beginTransaction();
        Criteria criteria = currentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("role",role));
        List<User> users = criteria.list();
        currentSession().getTransaction().commit();
        return users;
    }

    public Optional<User> changePassword(String userName, String newPassword){
        Optional<User> user = findByUsername(userName);

        if(user.isPresent()){
            currentSession().beginTransaction();
            user.get().setPassword(newPassword);
            persist(user.get());
            currentSession().getTransaction().commit();
        }

        return user;
    }
}
